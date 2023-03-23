package org.example;        // Feb 2022

import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
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
        System.out.println("1. find all");
        int input1 = keyboard.nextInt();
        if (input1 ==1){
            try {
                System.out.println(IUserDao.findAllPlayers());
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
        }


    }
}

