package org.client.menus;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.DTOs.Player;
import org.core.Packet;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Menu {
    private final PrintWriter socketWriter;
    private final Scanner socketReader;


    protected Menu(Scanner socketReader, PrintWriter socketWriter) {
        this.socketWriter = socketWriter;
        this.socketReader = socketReader;
    }

    public PrintWriter getSocketWriter() {
        return socketWriter;
    }

    public Scanner getSocketReader() {
        return socketReader;
    }

    public void outputCommand(JSONObject jo)
    {
        getSocketWriter().println(jo);
        getSocketWriter().flush();
    }
    public void getResult(Packet responsePacket)
    {
        System.out.println(getSocketReader().nextLine());
        responsePacket.readJson(new JSONObject(getSocketReader().nextLine()));
    }
    public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if(e.name().equals(value)) { return true; }
        }
        return false;
    }
    public static void print(){
        PrintWriter printer = null;
        try {
            printer = new PrintWriter(new File("idTracker.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        printer.print(Player.getIdCount());
        printer.close();
    }


}
