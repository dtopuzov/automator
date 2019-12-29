package org.openset.automator.os;

import java.io.*;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Process {
    public static ProcessInfo start(String[] command, int timeout, boolean wait) throws IOException, InterruptedException, TimeoutException {
        java.lang.Process p = new ProcessBuilder(command).start();
        if (wait) {
            p.waitFor(timeout, TimeUnit.SECONDS);
        }
        InputStream error = p.getErrorStream();
        InputStream input = p.getInputStream();
        if (wait) {
            if (p.isAlive()) {
                p.destroyForcibly();
                String e = String.format("Process '%s' timeout after %s seconds.", String.join(" ", command), timeout);
                throw new TimeoutException(e);
            }
            String output = read(input) + "\n" + read(error);
            return new ProcessInfo(null, output);
        } else {
            return new ProcessInfo(p.pid(), null);
        }
    }

    public static ProcessInfo start(String[] command, boolean wait) throws IOException, InterruptedException, TimeoutException {
        return start(command, 30, wait);
    }

    public static ProcessInfo start(String[] command, int timeout) throws IOException, InterruptedException, TimeoutException {
        return start(command, timeout, true);
    }

    public static ProcessInfo start(String[] command) throws IOException, InterruptedException, TimeoutException {
        return start(command, 30, true);
    }

    public static void stop(String command, String commandline) {
        if (commandline != null) {
            Stream<ProcessHandle> processes = ProcessHandle.allProcesses()
                    .filter(ph -> ph.info().command().isPresent())
                    .filter(p -> p.info().command().get()
                            .substring(p.info().command().get().lastIndexOf(java.io.File.separator) + 1)
                            .trim()
                            .equalsIgnoreCase(command))
                    .filter(pr -> getCommandLine(pr).get().contains(commandline));
            processes.forEach(pr -> pr.destroyForcibly());
        } else {
            Stream<ProcessHandle> processes = ProcessHandle.allProcesses()
                    .filter(ph -> ph.info().command().isPresent())
                    .filter(p -> p.info().command().get()
                            .substring(p.info().command().get().lastIndexOf(java.io.File.separator) + 1)
                            .trim()
                            .equalsIgnoreCase(command));
            processes.forEach(pr -> pr.destroyForcibly());
        }
    }

    public static void stop(String command) {
        stop(command, null);
    }

    public static boolean isRunning(String command, String commandline) {
        Stream<ProcessHandle> processes = ProcessHandle.allProcesses()
                .filter(ph -> ph.info().command().isPresent())
                .filter(p -> p.info().command().get()
                        .substring(p.info().command().get().lastIndexOf(java.io.File.separator) + 1)
                        .trim()
                        .equalsIgnoreCase(command));

        if (commandline != null) {
            return processes.anyMatch(pr -> getCommandLine(pr).get().contains(commandline));
        } else {
            return processes.count() > 0;
        }
    }

    public static boolean isRunning(String command) {
        return isRunning(command, null);
    }

    private static String read(InputStream input) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }

    /**
     * Returns the full command-line of the process.
     * <p>
     * This is a workaround for
     * <a href="https://stackoverflow.com/a/46768046/14731">https://stackoverflow.com/a/46768046/14731</a>
     *
     * @param processHandle a process handle
     * @return the command-line of the process
     * @throws UncheckedIOException if an I/O error occurs
     */
    private static Optional<String> getCommandLine(ProcessHandle processHandle) throws UncheckedIOException {
        if (!isWindows()) {
            return processHandle.info().commandLine();
        }
        long desiredProcessid = processHandle.pid();
        try {
            java.lang.Process process = new ProcessBuilder("wmic", "process", "where", "ProcessID=" + desiredProcessid, "get",
                    "commandline", "/format:list").
                    redirectErrorStream(true).
                    start();
            try (InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
                 BufferedReader reader = new BufferedReader(inputStreamReader)) {
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        return Optional.empty();
                    }
                    if (!line.startsWith("CommandLine=")) {
                        continue;
                    }
                    // System.out.println(Optional.of(line.substring("CommandLine=".length())).get());
                    return Optional.of(line.substring("CommandLine=".length()));
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }
}
