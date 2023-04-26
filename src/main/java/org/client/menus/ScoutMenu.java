package org.client.menus;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.Comparator.CompDOB;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.MySqlScoutDao;
import org.DAOs.ScoutDaoInterface;
import org.DTOs.Player;
import org.DTOs.Scout;
import org.Exceptions.DaoException;
import org.core.Packet;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ScoutMenu extends Menu {
    public ScoutMenu(Scanner socketReader, PrintWriter socketWriter) {
        super(socketReader, socketWriter);
    }

    public void setUpScoutMenu() {
        Scanner keyboard = new Scanner(System.in);
        ScoutDaoInterface scoutDao = new MySqlScoutDao();
        Packet outgoingPacket = new Packet("", "");
        Packet responsePacket = new Packet("", "");

        System.out.println("select feature");

        System.out.println("1. Find all scouts\n2. Find scout by ID\n3. Delete scout by ID\n4. Insert scout\n5. Sort by DOB\n6. Find all(JSON)\n7. Find by ID (JSON)\n8. Exit");
        int input1 = keyboard.nextInt();
        try {
            switch (input1) {
                case 1:
                    System.out.println(scoutDao.findAllScouts());
                    break;
                case 2:
                    System.out.println("Please enter ID");
                    String id = keyboard.next();
                    System.out.println(scoutDao.findScoutByID(id));
                    break;
                case 3:
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

                    Scout s = new Scout(inputName, date);
                    scoutDao.insertScout(s);

                case 5:
                    System.out.println(scoutDao.findScoutUsingFilter(new CompDOB()));
                    break;

                case 6:
                    outgoingPacket.setCommand("FIND_ALL_SCOUTS");
                    System.out.println(outgoingPacket.writeJSON());

                    outputCommand(outgoingPacket.writeJSON());

                    Type list = new TypeToken<List<Scout>>() {
                    }.getType();
                    getResult(responsePacket);

                    List<Scout> scoutArray = new Gson().fromJson(responsePacket.getObj(), list);
                    System.out.println(scoutArray);
                    break;

                case 7:
                    System.out.println("Please enter ID");
                    String idJson = keyboard.next();
                    outgoingPacket.setCommand("FIND_SCOUT_BY_ID " + idJson.toUpperCase());
                    outputCommand(outgoingPacket.writeJSON());
                    getResult(responsePacket);
                    Scout p = new Gson().fromJson(responsePacket.getObj(), Scout.class);
                    System.out.println(p);
                    break;


            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
