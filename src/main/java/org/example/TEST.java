package org.example;
import org.DAOs.*;
import org.DTOs.Report;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;
import org.server.CommandFactory;

import java.util.Scanner;

public class TEST {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter Player ID");
        String playerID = keyboard.nextLine();
        System.out.println("Please enter Scout ID");
        String scoutID = keyboard.nextLine();

        System.out.println("Please enter year:");
        int inputYear = Integer.valueOf(keyboard.nextLine());
        System.out.println("Please insert negative reports");
        String negativespos = keyboard.nextLine();
        System.out.println("Please insert positive reports");
        String positives = keyboard.nextLine();

    }



}
