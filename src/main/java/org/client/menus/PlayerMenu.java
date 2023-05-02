package org.client.menus;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.Comparator.CompDraftYear;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.DTOs.Player;
import org.DTOs.Scout;
import org.Exceptions.DaoException;
import org.client.Client;
import org.core.Packet;
import org.enums;
import org.json.JSONException;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PlayerMenu extends Menu {
    private final Scanner keyboard;
    private final PlayerDaoInterface userDao;
    private final Gson gsonParser;

    public PlayerMenu(Scanner socketReader, PrintWriter socketWriter) {
        super(socketReader, socketWriter);
        this.keyboard = new Scanner(System.in);
        this.userDao = new MySqlPlayerDao();
        this.gsonParser = new Gson();
        this.userDao.updateId();
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
        String inputID;
        List<Player> playerArray;
        Type list;

        Packet outgoingPacket = new Packet("", "");
        Packet responsePacket = new Packet("", "");

        while (!exit) {

            try {
                System.out.println("Select feature:");
                printPlayerOption();
                ////check if user enters anything other than string
                String inputStr = keyboard.next();
                if (!inputStr.matches("\\d+")) {
                    System.out.println("Error! Invalid input. Try again.");
                    continue;
                }
                input = Integer.parseInt(inputStr);
                switch (input) {

                    case 1:
                        outgoingPacket.setCommand("FIND_ALL_PLAYERS");
                        System.out.println(outgoingPacket.writeJSON());

                        outputCommand(outgoingPacket.writeJSON());

                        list = new TypeToken<List<Player>>() {
                        }.getType();
                        getResult(responsePacket);

                        playerArray = gsonParser.fromJson(responsePacket.getObj(), list);
                        playerFormatArray(playerArray);
                        break;

                    case 2:
                        System.out.println("Please enter ID: ");
                        inputID = keyboard.next();
                        outgoingPacket.setCommand("FIND_PLAYER_BY_ID " + inputID.toUpperCase());
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);
                        Player p = gsonParser.fromJson(responsePacket.getObj(), Player.class);
                        playerFormat(p);
                        break;

                    case 3:
                        System.out.println("Please enter ID: ");
                        inputID = keyboard.next();
                        outgoingPacket.setCommand("DELETE_PLAYER_BY_ID " + inputID.toUpperCase());
                        outputCommand(outgoingPacket.writeJSON());
                        break;

                    case 4:

                        String playerName;
                        String playerBirthDate;
                        String position = null;
                        int draftYear;

                        System.out.println("Enter name: ");
                        playerName = keyboard.next();
                        System.out.println("Enter DOB (YYYY-MM-DD): ");
                        playerBirthDate = keyboard.next();

                        while (!isInEnum(position, enums.positions.class)) {
                            System.out.println("Enter position: ");
                            position = keyboard.next();
                        }
                        System.out.println("Enter draft year: ");
                        draftYear = keyboard.nextInt();

                        Date date = Date.valueOf(playerBirthDate);

                        Player inputPlayer = new Player(playerName, date, position, draftYear);
                        String newPlayerJson = gsonParser.toJson(inputPlayer);
                        outgoingPacket.setCommand("INSERT_PLAYER");
                        outgoingPacket.setObj(newPlayerJson);
                        outputCommand(outgoingPacket.writeJSON());

                        print();
                        break;

                    case 5:
                        outgoingPacket.setCommand("FIND_PLAYER_USING_FILTER");
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
