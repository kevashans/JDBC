package org.client.menus;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.Comparator.CompDOB;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.MySqlScoutDao;
import org.DAOs.ScoutDaoInterface;
import org.DTOs.Player;
import org.DTOs.Scout;
import org.Exceptions.DaoException;
import org.client.Client;
import org.core.Packet;
import org.json.JSONException;

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

    public void setUpScoutMenu() {
        Packet outgoingPacket = new Packet("", "");
        Packet responsePacket = new Packet("", "");
        boolean exit = false;
        List<Scout> scoutArray;
        Type list = new TypeToken<List<Scout>>() {
        }.getType();

        while (exit == false) {
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
                        outgoingPacket.setCommand("FIND_ALL_SCOUTS");
                        System.out.println(outgoingPacket.writeJSON());

                        outputCommand(outgoingPacket.writeJSON());


                        getResult(responsePacket);

                        scoutArray = gsonParser.fromJson(responsePacket.getObj(), list);
                        System.out.println(scoutArray);
                        break;

                    case 2:
                        System.out.println("Please enter ID");
                        String idJson = keyboard.next();
                        outgoingPacket.setCommand("FIND_SCOUT_BY_ID " + idJson.toUpperCase());
                        outputCommand(outgoingPacket.writeJSON());
                        getResult(responsePacket);
                        Scout p = gsonParser.fromJson(responsePacket.getObj(), Scout.class);
                        System.out.println(p);
                        break;

                    case 3:
                        System.out.println("Please enter ID:");
                        String deleteid = keyboard.next();
                        scoutDao.deleteScoutByID(deleteid);
                        break;
                    case 4:
                        System.out.println("Please enter name:");
                        String inputName = keyboard.next();
                        System.out.println("Please enter birth date (YYYY-MM-DD):");
                        String inputDOB = keyboard.next();

                        Date date = Date.valueOf(inputDOB);

                        Scout s = new Scout(inputName, date);
                        scoutDao.insertScout(s);
                        break;
                    case 5:
                        outgoingPacket.setCommand("FIND_SCOUT_USING_FILTER");
                        System.out.println(outgoingPacket.writeJSON());

                        outputCommand(outgoingPacket.writeJSON());


                        getResult(responsePacket);

                        scoutArray = gsonParser.fromJson(responsePacket.getObj(), list);
                        System.out.println(scoutArray);
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
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
