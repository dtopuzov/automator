package org.openset.automator.os;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class File {
    /**
     * Create folder (if it do not exists).
     *
     * @param folderPath path to folder.
     */
    public static void createFolder(String folderPath) {
        java.io.File directory = new java.io.File(folderPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Write string in file.
     *
     * @param text     text to be written in file.
     * @param filePath path to file.
     * @throws FileNotFoundException when fail to find file.
     */
    public static void writeStringToFile(String text, String filePath) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(new FileOutputStream(filePath))) {
            out.print(text);
        }
    }

    /**
     * Extract zip file.
     *
     * @param zipFile       path to zip file.
     * @param extractFolder folder where zip will be extracted.
     */
    public static void extractZip(String zipFile, String extractFolder) {
        try {
            int buffer = 2048;
            java.io.File file = new java.io.File(zipFile);
            ZipFile zip = new ZipFile(file);

            new java.io.File(extractFolder).mkdir();
            Enumeration<? extends ZipEntry> zipFileEntries = zip.entries();

            while (zipFileEntries.hasMoreElements()) {
                ZipEntry entry = zipFileEntries.nextElement();
                String currentEntry = entry.getName();

                java.io.File destFile = new java.io.File(extractFolder, currentEntry);
                java.io.File destinationParent = destFile.getParentFile();
                destinationParent.mkdirs();

                if (!entry.isDirectory()) {
                    BufferedInputStream is = new BufferedInputStream(zip
                            .getInputStream(entry));
                    int currentByte;
                    byte[] data = new byte[buffer];
                    FileOutputStream fos = new FileOutputStream(destFile);
                    BufferedOutputStream dest = new BufferedOutputStream(fos,
                            buffer);

                    while ((currentByte = is.read(data, 0, buffer)) != -1) {
                        dest.write(data, 0, currentByte);
                    }
                    dest.flush();
                    dest.close();
                    is.close();
                }
            }
        } catch (IOException e) {
            throw new ExtractException(String.format("Failed to extract %s", zipFile), e);
        }
    }

    /**
     * Find file in path.
     *
     * @param basePath root folder where search starts.
     * @param fileName file name.
     * @return absolute path for first file found.
     * @throws IOException when root does not exists or failed to find file.
     */
    public static String find(String basePath, String fileName) throws IOException {
        Path hit = Files.walk(Paths.get(basePath))
                .filter(file -> file.getFileName().toString().equalsIgnoreCase(fileName))
                .findFirst().orElse(null);
        if (hit != null) {
            return hit.toAbsolutePath().toString();
        } else {
            String message = String.format("Failed to find file '%s' at '%s' folder.", fileName, basePath);
            throw new FileNotFoundException(message);
        }
    }
}
