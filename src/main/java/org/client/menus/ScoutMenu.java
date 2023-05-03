package org.client.menus;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.DAOs.MySqlScoutDao;
import org.DTOs.Scout;
import org.client.Client;
import org.core.Packet;
import org.json.JSONException;
import org.enums.*;


import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ScoutMenu extends Menu {
    private final Scanner keyboard;
    private final MySqlScoutDao scoutDao;
    private final Gson gsonParser;

    public ScoutMenu(Scanner socketReader, PrintWriter socketWriter) {
        super(socketReader, socketWriter);
        this.keyboard = new Scanner(System.in);
        this.scoutDao = new MySqlScoutDao();
        this.gsonParser = new Gson();
    }
    public static void scoutFormatArray(List<Scout>ScoutList){
        System.out.format("| %-8s | %-20s | %-12s |%n",
                "ScoutID", "Name", "DOB");

        for (int i = 0; i < ScoutList.size(); i++) {
            Scout p = ScoutList.get(i);
            System.out.format("| %-8s | %-20s | %-12s |%n",
                    p.getId(), p.getScout_name(), p.getDOB());
        }
    }
    public static void scoutFormat(Scout p){
        System.out.format("| %-8s | %-20s | %-12s |%n",
                "ScoutID", "Name", "DOB");


        System.out.format("| %-8s | %-20s | %-12s |%n",
                p.getId(), p.getScout_name(), p.getDOB());

    }

    public void setUpScoutMenu() {

        boolean exit = false;


        while (exit == false) {
            Packet outgoingPacket = new Packet("", "");
            Packet responsePacket = new Packet("", "");
            List<Scout> scoutArray;
            String inputDOB = null;
            Date date = null;
            Type list = new TypeToken<List<Scout>>() {
            }.getType();

            System.out.println("select feature");
            System.out.println("1. Find all scouts\n2. Find scout by ID\n3. Delete scout by ID\n4. Insert scout\n5. Sort by DOB\n6. Exit");
            int input1 = 0;

            String inputStr = keyboard.next();
            if (!inputStr.matches("\\d+")) {
                System.out.println("Error! Invalid input. Try again.");
                continue;
            }
            input1 = Integer.parseInt(inputStr);

            try {
                switch (input1) {
                    case 1:
                        outgoingPacket.setCommand(String.valueOf(ScoutCommands.FIND_ALL_SCOUTS));

                        outputCommand(outgoingPacket.writeJSON());


                        getResult(responsePacket);

                        scoutArray = gsonParser.fromJson(responsePacket.getObj(), list);
                        scoutFormatArray(scoutArray);
                        break;

                    case 2:
                        System.out.println("Please enter ID");
                        String idJson = keyboard.next();
                        outgoingPacket.setCommand(ScoutCommands.FIND_SCOUT_BY_ID + idJson.toUpperCase());
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);
                        Scout p = gsonParser.fromJson(responsePacket.getObj(), Scout.class);
                        scoutFormat(p);
                        break;

                    case 3:
                        System.out.println("Please enter ID:");
                        String deleteid = keyboard.next().toUpperCase();
                        outgoingPacket.setCommand(ScoutCommands.DELETE_SCOUT_BY_ID+ deleteid.toUpperCase());
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);

                        if(Integer.valueOf(responsePacket.getObj()) == 1){
                            System.out.println("DELETED");
                        }else{
                            System.err.println(deleteid + " not found");
                        }
                        break;
                    case 4:
                        System.out.println("Please enter name:");
                        String inputName = keyboard.next();
                        boolean validDate = false;
                        
                        while (!validDate) {
                            try {
                                System.out.println("Enter DOB (YYYY-MM-DD): ");
                                inputDOB = keyboard.next();
                                date = Date.valueOf(inputDOB);
                                validDate = true;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
                            }
                        }



                        Scout s = new Scout(inputName, date);
                        String newScoutJson = gsonParser.toJson(s);
                        outgoingPacket.setCommand(ScoutCommands.INSERT_SCOUT.toString());
                        outgoingPacket.setObj(newScoutJson);
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);

                        if(Integer.valueOf(responsePacket.getObj()) == 1){
                            System.out.println("Inserted");
                        }else{
                            System.err.println("Insert fail");
                        }
                        break;
                    case 5:
                        outgoingPacket.setCommand(String.valueOf(ScoutCommands.FIND_SCOUT_USING_FILTER));

                        outputCommand(outgoingPacket.writeJSON());


                        getResult(responsePacket);

                        scoutArray = gsonParser.fromJson(responsePacket.getObj(), list);
                        scoutFormatArray(scoutArray);
                        break;

                    case 6:
                        Client.start();
                        break;
                    default:
                        System.out.println("invalid input please try again");
                        break;


                }
            } catch (JsonSyntaxException e) {
                System.err.println("No data found");
            } catch (JSONException e) {
                System.err.println("No data found");
            }
        }
    }
}
