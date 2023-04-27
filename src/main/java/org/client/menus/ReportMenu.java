package org.client.menus;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.Comparator.CompDOB;
import org.DAOs.MySqlReportDao;
import org.DAOs.MySqlScoutDao;
import org.DTOs.Report;
import org.DTOs.Scout;
import org.Exceptions.DaoException;
import org.client.Client;
import org.core.Packet;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ReportMenu extends Menu{
    private final Scanner keyboard;
    private final MySqlReportDao reportDao;
    private final Gson gsonParser;

    public ReportMenu(Scanner socketReader, PrintWriter socketWriter) {
        super(socketReader, socketWriter);
        this.keyboard = new Scanner(System.in);
        this.reportDao = new MySqlReportDao();
        this.gsonParser = new Gson();
    }
    public static void printReportOptions() {
        System.out.println("1. Find all\n2. Find report by ID\n3. Find report by player ID\n4. Find report by player name\n5. Find report by scout ID\n6. Exit");
    }
    public void setUpReportMenu() {
        Packet outgoingPacket = new Packet("", "");
        Packet responsePacket = new Packet("", "");

        String playerID;
        String scoutID;
        Type list = new TypeToken<List<Report>>() {
        }.getType();
        List<Report> reportArray;
        System.out.println("select feature");
        int input1 = keyboard.nextInt();
        try{
        switch (input1) {
            case 1:
                outgoingPacket.setCommand("FIND_ALL_REPORTS");
                System.out.println(outgoingPacket.writeJSON());

                outputCommand(outgoingPacket.writeJSON());


                getResult(responsePacket);

                reportArray = gsonParser.fromJson(responsePacket.getObj(), list);
                System.out.println(reportArray);
                break;

            case 2:
                System.out.println("Please enter player ID");
                playerID = keyboard.next();
                System.out.println("Please enter scout ID");
                scoutID = keyboard.next();
                outgoingPacket.setCommand("FIND_REPORT_BY_ID " + playerID + "," + scoutID);
                outputCommand(outgoingPacket.writeJSON());
                getResult(responsePacket);
                Report p = gsonParser.fromJson(responsePacket.getObj(), Report.class);
                System.out.println(p);
                break;

            case 3:
                System.out.println("Please enter player ID:");
                playerID = keyboard.next();
                outgoingPacket.setCommand("FIND_REPORT_BY_PLAYER_ID " + playerID);
                outputCommand(outgoingPacket.writeJSON());
                getResult(responsePacket);

                reportArray = gsonParser.fromJson(responsePacket.getObj(), list);
                System.out.println(reportArray);
                break;
            case 4:
                System.out.println("Please enter name:");
                String inputName = keyboard.next();

                outgoingPacket.setCommand("FIND_REPORT_BY_PLAYER_NAME " + inputName);
                outputCommand(outgoingPacket.writeJSON());
                getResult(responsePacket);

                reportArray = gsonParser.fromJson(responsePacket.getObj(), list);
                System.out.println(reportArray);
                break;
            case 5:
                System.out.println("Please enter Scout ID:");
                scoutID = keyboard.next();
                outgoingPacket.setCommand("FIND_REPORT_BY_SCOUT_ID" + scoutID);
                outputCommand(outgoingPacket.writeJSON());
                getResult(responsePacket);

                reportArray = gsonParser.fromJson(responsePacket.getObj(), list);
                System.out.println(reportArray);
                break;

            case 6:
                Client.start();
                break;

        }
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
