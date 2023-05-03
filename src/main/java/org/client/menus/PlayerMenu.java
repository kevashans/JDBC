package org.client.menus;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.DTOs.Player;
import org.client.Client;
import org.core.Packet;
import org.enums.*;
import org.json.JSONException;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PlayerMenu extends Menu {
    private final Scanner keyboard;
    private final PlayerDaoInterface playerDao;
    private final Gson gsonParser;

    public PlayerMenu(Scanner socketReader, PrintWriter socketWriter) {
        super(socketReader, socketWriter);
        this.keyboard = new Scanner(System.in);
        this.playerDao = new MySqlPlayerDao();
        this.gsonParser = new Gson();
        this.playerDao.updateId();
    }

    public static void playerFormatArray(List<Player>playerList){
        System.out.format("| %-8s | %-20s | %-12s | %-15s | %-24s |%n",
                "PlayerID", "Name", "DOB", "Position", "Draft year");

        for (int i = 0; i < playerList.size(); i++) {
            Player p = playerList.get(i);
            System.out.format("| %-8s | %-20s | %-12s | %-15s | %-24s |%n",
                    p.getId(), p.getPlayer_name(), p.getDOB(), p.getPosition(), p.getPlayer_draft_year());
        }
    }
    public static void playerFormat(Player p){
        System.out.format("| %-8s | %-20s | %-12s | %-15s | %-24s |%n",
                "PlayerID", "Name", "DOB", "Position", "Draft year");

        System.out.format("| %-8s | %-20s | %-12s | %-15s | %-24s |%n",
                p.getId(), p.getPlayer_name(), p.getDOB(), p.getPosition(), p.getPlayer_draft_year());

    }

    public static void printPlayerOption() {
        System.out.println("1. Find all\n2. Find player by ID\n3. Delete player by ID\n4. Add player\n5. Sort by DraftYear\n6. Exit");
    }

    public void setUpPlayerMenu() {
        boolean exit = false;
        int input = 0;



        while (!exit) {
            Packet outgoingPacket = new Packet("", "");
            Packet responsePacket = new Packet("", "");
            String inputID;
            List<Player> playerArray;
            Type list;
            String playerName;
            String playerBirthDate = null;
            String position = null;
            int draftYear;
            Date date = null;


            try {
                System.out.println("Select feature:");
                printPlayerOption();
                ////check if user enters anything other than string
                String inputStr = keyboard.nextLine();
                if (!inputStr.matches("\\d+")) {
                    System.out.println("Error! Invalid input. Try again.");
                    continue;
                }
                input = Integer.parseInt(inputStr);
                switch (input) {

                    case 1:
                        ////insert command into the packet
                        outgoingPacket.setCommand(PlayerCommands.FIND_ALL_PLAYERS.toString());
                        System.out.println(outgoingPacket.writeJSON());
                        ////send to server the packet in json string form
                        outputCommand(outgoingPacket.writeJSON());

                        list = new TypeToken<List<Player>>() {
                        }.getType();
                        ////get data from server and insert it to responsePacket
                        getResult(responsePacket);

                        ////transform result into a java object
                        playerArray = gsonParser.fromJson(responsePacket.getObj(), list);
                        playerFormatArray(playerArray);
                        break;

                    case 2:
                        System.out.println("Please enter ID: ");
                        inputID = keyboard.nextLine();
                        outgoingPacket.setCommand(PlayerCommands.FIND_PLAYER_BY_ID + inputID.toUpperCase());
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);
                        Player p = gsonParser.fromJson(responsePacket.getObj(), Player.class);
                        playerFormat(p);
                        break;

                    case 3:
                        System.out.println("Please enter ID: ");
                        inputID = keyboard.nextLine();
                        outgoingPacket.setCommand(PlayerCommands.DELETE_PLAYER_BY_ID + inputID.toUpperCase());
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);

                        if(Integer.valueOf(responsePacket.getObj()) == 1){
                        System.out.println("DELETED");
                        }else{
                            System.err.println(inputID + " not found");
                        }

                        break;

                    case 4:

                        System.out.println("Enter name: ");
                        playerName = keyboard.nextLine();

                        boolean validDate = false;
///checl for valid date
                        while (!validDate) {
                            try {
                                System.out.println("Enter DOB (YYYY-MM-DD): ");
                                playerBirthDate = keyboard.nextLine();
                                 date = Date.valueOf(playerBirthDate);
                                validDate = true;
                            } catch (IllegalArgumentException e) {
                                System.err.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
                            }
                        }
////check if position is according to enum
                        while (!isInEnum(position, Positions.class)) {
                            System.out.println("Enter position: ");
                            position = keyboard.nextLine();
                        }
                        System.out.println("Enter draft year: ");
                        draftYear = 0;
                        while (draftYear <= 0) {
                            System.out.println("Please enter a positive year:");
                            try {
                                draftYear = Integer.parseInt(keyboard.nextLine());
                                if (draftYear <= 0) {
                                    System.out.println("Year must be a positive integer.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Year must be a positive integer.");
                            }
                        }


                        Player inputPlayer = new Player(playerName, date, position, draftYear);
                        ////turn new player object into json string
                        String newPlayerJson = gsonParser.toJson(inputPlayer);
                        ////set the command an object for the packet to be sent to the server
                        outgoingPacket.setCommand(PlayerCommands.INSERT_PLAYER.toString());
                        outgoingPacket.setObj(newPlayerJson);
                        ////transfer packet to server
                        outputCommand(outgoingPacket.writeJSON());

                        ////get result and insert it to responsePacket
                        getResult(responsePacket);

                        if(Integer.valueOf(responsePacket.getObj()) == 1){
                            System.out.println("Inserted");
                        }else{
                            System.err.println("Insert fail");
                        }

                        print();
                        break;

                    case 5:
                        outgoingPacket.setCommand(String.valueOf(PlayerCommands.FIND_PLAYER_USING_FILTER));
                        outputCommand(outgoingPacket.writeJSON());
                        list = new TypeToken<List<Player>>() {
                        }.getType();
                        getResult(responsePacket);

                        playerArray = gsonParser.fromJson(responsePacket.getObj(), list);
                        System.out.println(playerArray);
                        playerFormatArray(playerArray);
                        break;

                    case 6:
                        print();
                        Client.start();
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }

            } catch (JsonSyntaxException e) {
                System.err.println("No data found");
            }catch (JSONException e) {
                System.err.println("No data found");
            }catch (InputMismatchException e) {
                System.out.println("Error! Invalid integer. Try again.");
            }
        }
    }
}
