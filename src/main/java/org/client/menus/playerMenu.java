package org.client.menus;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.Comparator.CompDraftYear;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.DTOs.Player;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.enums;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class playerMenu extends Menu {
    public playerMenu(Scanner socketReader, PrintWriter socketWriter) {
        super(socketReader, socketWriter);
    }

    public static void printPlayerOption(){
        System.out.println("1. Find all\n2. Find player by ID\n3. Delete player by ID\n4. Add player\n5. Sort by DraftYear\n6. Find all(JSON)\n7. Find by ID (JSON)\n8. Exit");

    }

    public void setUpPlayerMenu() {
        boolean exit = false;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Select feature:");
        PlayerDaoInterface userDao= new MySqlPlayerDao();
        userDao.updateId();

        printPlayerOption();
        int input = keyboard.nextInt();

        try {
            switch (input) {
                case 1:
                    System.out.println(userDao.findAllPlayers());
                    break;

                case 2:
                    System.out.println("Please enter ID: ");
                    String inputID = keyboard.next();
                    System.out.println(userDao.findPlayerByID(inputID));
                    break;

                case 3:
                    System.out.println("Please enter ID: ");
                    inputID = keyboard.next();
                    userDao.deletePlayerByID(inputID);
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
                    Gson gsonParser = new Gson();
                    String newTrainJson = gsonParser.toJson(inputPlayer);

                    Packet incomingPacket = new Packet(null,null);
                    incomingPacket.setCommand("TEST");
                    incomingPacket.setObj(newTrainJson);
                    System.out.println(incomingPacket.writeJSON());
                    userDao.insertPlayer(inputPlayer);
                    print();
                    break;

                case 5:
//                        System.out.println("enter draft year");
                    System.out.println(userDao.findPlayerUsingFilter(new CompDraftYear()));
                    break;

                case 6:
                    outputCommand("FIND_ALL_PLAYERS");
                    Type list = new TypeToken<List<Player>>(){}.getType();
                    List<Player> playerArray = new Gson().fromJson(getResult(), list);
                    System.out.println(playerArray);

                    break;

                case 7:
                    System.out.println("Please enter ID: ");
                    inputID = keyboard.next();
                    outputCommand("FIND_PLAYER_BY_ID " + inputID.toUpperCase());
                    Player p = new Gson().fromJson(getResult(), Player.class);
                    System.out.println(p);
                case 8:
                    print();
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } catch (DaoException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error executing SQL statement: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.getMessage());
            System.out.println(e);
        }

    }
}
