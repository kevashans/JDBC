package org.core;

public class Packet {
    String command;
    String obj;

    public Packet(String command, String obj) {
        this.command = command;
        this.obj = obj;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "PacketClass{" +
                "command='" + command + '\'' +
                ", obj=" + obj +
                '}';
    }
}
