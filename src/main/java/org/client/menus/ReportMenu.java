package org.client.menus;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.Exceptions.DaoException;
import org.enums.*;
import org.DAOs.MySqlReportDao;

import org.DTOs.Report;

import org.client.Client;
import org.core.Packet;
import org.json.JSONException;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReportMenu extends Menu {
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
        System.out.println("1. Find all\n2. Find report by ID\n3. Find report by player ID\n4. Find report by player name\n5. Find report by scout ID\n6. Find report by year ID\n7. add report\n9. Exit");
    }
    public static void reportFormat(Report r){
        System.out.format("| %-8s | %-20s | %-12s | %-80s | %-80s |%n",
                "PlayerID", "ScoutID", "Season", "Positives", "Negatives");

        System.out.format("| %-8s | %-20s | %-12s | %-80s | %-80s |%n",
                r.getPlayerID(), r.getScoutID(), r.getSeason(), r.getPositives(), r.getNegatives());

    }

    public static void reportFormatArray(List<Report>reports){
        System.out.format("| %-8s | %-20s | %-12s | %-80s | %-80s |%n",
                "PlayerID", "ScoutID", "Season", "Positives", "Negatives");

        for (int i = 0; i < reports.size(); i++) {
            Report r = reports.get(i);
            System.out.format("| %-8s | %-20s | %-12s | %-80s | %-80s |%n",
                    r.getPlayerID(), r.getScoutID(), r.getSeason(), r.getPositives(), r.getNegatives());
        }
    }


    public void setUpReportMenu() {

       
        boolean exit = false;


        while (!exit) {
            String playerID = null;
            String scoutID = null;
            int inputYear;
            String positives;
            String negatives;
            Type list = new TypeToken<List<Report>>() {
            }.getType();
            List<Report> reportArray;
            Packet outgoingPacket = new Packet("", "");
            Packet responsePacket = new Packet("", "");

            System.out.println("select feature");
            printReportOptions();


            String inputStr = keyboard.nextLine();
            if (!inputStr.matches("\\d+")) {
                System.out.println("Error! Invalid input. Try again.");
                continue;
            }

            int input1 = Integer.parseInt(inputStr);


            try {
                reportDao.initializeID();
                switch (input1) {
                    case 1:
                        outgoingPacket.setCommand(ReportCommands.FIND_ALL_REPORTS.toString());
                        System.out.println(outgoingPacket.writeJSON());

                        outputCommand(outgoingPacket.writeJSON());


                        getResult(responsePacket);

                        reportArray = gsonParser.fromJson(responsePacket.getObj(), list);
                        reportFormatArray(reportArray);
                        break;

                    case 2:
                        System.out.println("Please enter player ID");
                        playerID = keyboard.next().toUpperCase();
                        System.out.println("Please enter scout ID");
                        scoutID = keyboard.next().toUpperCase();
                        outgoingPacket.setCommand(ReportCommands.FIND_REPORT_BY_ID + playerID + "," + scoutID);
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);
                        Report p = gsonParser.fromJson(responsePacket.getObj(), Report.class);
                        reportFormat(p);
                        break;

                    case 3:
                        System.out.println("Please enter player ID:");
                        playerID = keyboard.next().toUpperCase();
                        outgoingPacket.setCommand(ReportCommands.FIND_REPORT_BY_PLAYER_ID + playerID);
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);

                        reportArray = gsonParser.fromJson(responsePacket.getObj(), list);
//                        System.out.println(reportArray);
                        reportFormatArray(reportArray);
                        break;
                    case 4:
                        System.out.println("Please enter name:");
                        String inputName = keyboard.next();

                        outgoingPacket.setCommand(ReportCommands.FIND_REPORT_BY_PLAYER_NAME + inputName);
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);

                        reportArray = gsonParser.fromJson(responsePacket.getObj(), list);
//                        System.out.println(reportArray);
                        reportFormatArray(reportArray);
                        break;
                    case 5:
                        System.out.println("Please enter Scout ID:");
                        scoutID = keyboard.next().toUpperCase();
                        outgoingPacket.setCommand(ReportCommands.FIND_REPORT_BY_SCOUT_ID + scoutID);
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);

                        reportArray = gsonParser.fromJson(responsePacket.getObj(), list);
//                        System.out.println(reportArray);
                        reportFormatArray(reportArray);
                        break;
                    case 6:
                        inputYear = 0;
                        while (inputYear <= 0) {
                            System.out.println("Please enter a positive year:");
                            try {
                                inputYear = Integer.parseInt(keyboard.nextLine());
                                if (inputYear <= 0) {
                                    System.out.println("Year must be a positive integer.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Year must be a positive integer.");
                            }
                        }

                        outgoingPacket.setCommand(ReportCommands.FIND_REPORT_BY_SEASON.toString()+ inputYear);
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);

                        reportArray = gsonParser.fromJson(responsePacket.getObj(), list);
//                        System.out.println(reportArray);
                        reportFormatArray(reportArray);
                        break;
                    case 7:
                        while(reportDao.getPlayerIds().contains(playerID)==false){
                            System.out.println("Please enter existing Player ID");
                            playerID = keyboard.nextLine().toUpperCase();
                        }
                        while(!reportDao.getScoutIds().contains(scoutID)){
                            System.out.println("Please enter existing Scout ID");
                            scoutID = keyboard.nextLine().toUpperCase();
                        }
                        System.out.println("Please enter year:");

                         inputYear = 0;
                        while (inputYear <= 0) {
                            System.out.println("Please enter a positive year:");
                            try {
                                inputYear = Integer.parseInt(keyboard.nextLine());
                                if (inputYear <= 0) {
                                    System.out.println("Year must be a positive integer.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Year must be a positive integer.");
                            }
                        }

                        System.out.println("Please insert positive reports");
                        positives = keyboard.nextLine();

                        System.out.println("Please insert negative reports");
                        negatives = keyboard.nextLine();

                        Report r = new Report(playerID,scoutID,inputYear,positives,negatives);
                        String rJson = gsonParser.toJson(r);

                        outgoingPacket.setCommand(ReportCommands.INSERT_REPORT.toString());
                        outgoingPacket.setObj(rJson);
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);

                        if(Integer.valueOf(responsePacket.getObj()) == 1){
                            System.out.println("Inserted");
                        }else{
                            System.err.println("Insert fail");
                        }
                        break;
                    case 9:
                        Client.start();
                        exit=true;
                        break;
                    default:
                        System.out.println("invalid input please try again");
                        break;

                }
            } catch (JsonSyntaxException e) {
                System.err.println("No data found " + e.getMessage());
            } catch (JSONException e) {
                System.err.println("No data found " + e.getMessage());
            }catch (NoSuchElementException e) {
                System.err.println("No data found");
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
