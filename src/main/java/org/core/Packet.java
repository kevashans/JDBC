package org.core;

import com.google.gson.JsonObject;
import org.json.JSONObject;

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

    public JSONObject writeJSON()
    {
        JSONObject jo = new JSONObject();
        jo.put("command", this.command);
        jo.put("payload", this.obj);
        return jo;
    }

    public void readJson(JSONObject jo){
        this.setCommand(String.valueOf(jo.get("command")));
        this.setObj(String.valueOf(jo.get("payload")));
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
