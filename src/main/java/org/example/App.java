package org.example;        // Feb 2022

import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.DTOs.Player;
import org.Exceptions.DaoException;

import java.sql.*;
import java.util.Scanner;

/**
 * Connecting to a MySQL Database Server.
 * This program simply attempts to connect to a database - but does nothing else.
 */

public class App
{
    public static void main(String[] args) {
        PlayerDaoInterface IUserDao = new MySqlPlayerDao();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("select feature :");
        System.out.println("1. find all\n2. find player by id\n3. delete player by id\n4. add player ");
        int input1 = keyboard.nextInt();
        if (input1 ==1){
            try {
                System.out.println(IUserDao.findAllPlayers());
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }else if(input1 ==2){
            System.out.println("please enter ID: ");
            String inputID = keyboard.next();
            try {
                System.out.println(IUserDao.findplayerByID(inputID));
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }else if(input1 ==3){
            System.out.println("please enter ID: ");
            String inputID = keyboard.next();
            try {
              IUserDao.deleteplayerByID(inputID);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }else if(input1 ==4){
            System.out.println("please enter ID: ");
            String playerId;
            String playerName;
            String playerBirthDate;
            String position;
            int draftYear;
            System.out.println("enter ID: ");
            playerId = keyboard.next();
            System.out.println("enter name: ");
            playerName = keyboard.next();
            System.out.println("enter DOB: ");
            playerBirthDate = keyboard.next();
            System.out.println("enter position");
            position = keyboard.next();
            System.out.println("enter draft year");
            draftYear = keyboard.nextInt();

            Date date=Date.valueOf(playerBirthDate);

            Player inputPlayer = new Player(playerId,playerName,date,position,draftYear);
            try {
                IUserDao.insertPlayer(inputPlayer);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }


    }
}

