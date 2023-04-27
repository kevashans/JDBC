package org.client.menus;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.Comparator.CompDraftYear;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.DTOs.Player;
import org.Exceptions.DaoException;
import org.client.Client;
import org.core.Packet;
import org.enums;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.SQLException;
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

    public static void printPlayerOption() {
        System.out.println("1. Find all\n2. Find player by ID\n3. Delete player by ID\n4. Add player\n5. Sort by DraftYear\n6. Exit");
    }

    public void setUpPlayerMenu() {
        boolean exit = false;
        System.out.println("Select feature:");
        printPlayerOption();

        int input = keyboard.nextInt();
        String inputID;
        List<Player> playerArray;
        Type list;

        Packet outgoingPacket = new Packet("", "");
        Packet responsePacket = new Packet("", "");

try {
    switch (input) {
        case 1:
            outgoingPacket.setCommand("FIND_ALL_PLAYERS");
            System.out.println(outgoingPacket.writeJSON());

            outputCommand(outgoingPacket.writeJSON());

            list = new TypeToken<List<Player>>() {
            }.getType();
            getResult(responsePacket);

            playerArray = gsonParser.fromJson(responsePacket.getObj(), list);
            System.out.println(playerArray);

            break;

        case 2:
            System.out.println("Please enter ID: ");
            inputID = keyboard.next();
            outgoingPacket.setCommand("FIND_PLAYER_BY_ID " + inputID.toUpperCase());
            outputCommand(outgoingPacket.writeJSON());
            getResult(responsePacket);
            Player p = gsonParser.fromJson(responsePacket.getObj(), Player.class);
            System.out.println(p);

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
            break;

        case 6:
            print();
            Client.start();
            break;

        default:
            System.out.println("Invalid input. Please try again.");
            break;
    }

} catch (JsonSyntaxException e) {
    throw new RuntimeException(e);
}
    }
}
