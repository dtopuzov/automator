package org.openset.automator.os;

public class ProcessInfo {
    private Long pid;
    private String output;

    public ProcessInfo(Long pid, String output) {
        this.pid = pid;
        this.output = output;
    }

    public Long getPid() {
        return pid;
    }

    public String getOutput() {
        return output;
    }
}
