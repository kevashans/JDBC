package org.example;        // Feb 2022

import org.Comparator.CompDraftYear;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.DTOs.Player;
import org.Exceptions.DaoException;

import java.sql.*;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Connecting to a MySQL Database Server.
 * This program simply attempts to connect to a database - but does nothing else.
 */

public class App {
    public static void main(String[] args) {

        PlayerDaoInterface userDao = new MySqlPlayerDao();
        //initialize cache
        try {
            userDao.findAllPlayers();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Select feature:");
            System.out.println("1. Find all\n2. Find player by ID\n3. Delete player by ID\n4. Add player\n5. Sort by DraftYear\n6. Exit");
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
                        String position;
                        int draftYear;

                        System.out.println("Enter name: ");
                        playerName = keyboard.next();
                        System.out.println("Enter DOB (YYYY-MM-DD): ");
                        playerBirthDate = keyboard.next();
                        System.out.println("Enter position: ");
                        position = keyboard.next();
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
}
