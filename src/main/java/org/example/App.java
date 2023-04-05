package org.example;        // Feb 2022

//import com.sun.tools.javac.code.Attribute;
import org.Comparator.CompDraftYear;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.MySqlScoutDao;
import org.DAOs.PlayerDaoInterface;
import org.DAOs.ScoutDaoInterface;
import org.DTOs.Player;
import org.DTOs.Scout;
import org.Exceptions.DaoException;
import org.enums;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Connecting to a MySQL Database Server.
 * This program simply attempts to connect to a database - but does nothing else.
 */

public class App {

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



    public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if(e.name().equals(value)) { return true; }
        }
        return false;
    }
    public static void main(String[] args) {

        PlayerDaoInterface userDao = new MySqlPlayerDao();
        ScoutDaoInterface scoutDao = new MySqlScoutDao();
        //initialize cache
        try {
            userDao.initializeID();
            scoutDao.initializeID();
            userDao.updateId();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Select Table: \n1.Player\n2.Scout");
            int input0 = keyboard.nextInt();

            if(input0==1) {
                System.out.println("Select feature:");
                System.out.println("1. Find all\n2. Find player by ID\n3. Delete player by ID\n4. Add player\n5. Sort by DraftYear\n6. Find all(JSON)\n7. Find by ID (JSON)\n8. Exit");
                int input = keyboard.nextInt();

                try {
                    switch (input) {
                        case 1:
                            System.out.println(userDao.findAllPlayers());
                            break;

                        case 2:
                            System.out.println("Please enter ID: ");
                            String inputID = keyboard.next();
                            System.out.println(userDao.findplayerByID(inputID));
                            break;

                        case 3:
                            System.out.println("Please enter ID: ");
                            inputID = keyboard.next();
                            userDao.deleteplayerByID(inputID);
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
                            userDao.insertPlayer(inputPlayer);
                            break;

                        case 5:
//                        System.out.println("enter draft year");
                            System.out.println(userDao.findPlayerUsingFilter(new CompDraftYear()));
                            break;

                        case 6:
                            System.out.println(userDao.findAllPlayersJson());
                            break;

                        case 7:
                            System.out.println("Please enter ID: ");
                            inputID = keyboard.next();
                            System.out.println(userDao.findplayerByID(inputID));
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
            }else if(input0 == 2){
                System.out.println("select feature");
                System.out.println("1. Find all scouts\n2. Find scout by ID\n3. Delete scout by ID\n4. Insert scout");
                int input1 = keyboard.nextInt();
                try{
                    switch (input1) {
                        case 1:
                            System.out.println( scoutDao.findAllScouts());
                            break;
                        case 2:
                            System.out.println("Please enter ID");
                            String id = keyboard.next();
                            System.out.println(scoutDao.findScoutByID(id));
                            break;
                        case 3 :
                            System.out.println("Please enter ID:");
                            String deleteid = keyboard.next();
                            scoutDao.deleteScoutByID(deleteid);
                        case 4:
//                            System.out.println("Please enter ID:");
//                            String inputID = keyboard.next();
                            System.out.println("Please enter name:");
                            String inputName = keyboard.next();
                            System.out.println("Please enter birth date (YYYY-MM-DD):");
                            String inputDOB = keyboard.next();

                            Date date = Date.valueOf(inputDOB);

                            Scout s = new Scout(inputName,date);
                            scoutDao.insertScout(s);



                    }
                }catch (DaoException e) {
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
    }
}
