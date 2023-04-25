package org.core;

import com.google.gson.JsonObject;

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

    public JsonObject writeJSON()
    {
        JsonObject jo = new JsonObject();
        jo.addProperty("messageType", this.command);
        jo.addProperty("payload", this.obj);
        return jo;
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
