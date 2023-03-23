package org.example;        // Feb 2022

import java.sql.*;
/**
 * Connecting to a MySQL Database Server.
 * This program simply attempts to connect to a database - but does nothing else.
 */

public class App
{
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
    public void start() {

        System.out.println("\nSample 1 - Connecting to MySQL Database called \"test\" using MySQL JDBC Driver");

        String url = "jdbc:mysql://localhost/";
        String dbName = "ca6";
        String userName = "root";   // default
        String password = "";       // default

         try ( Connection conn =
                       DriverManager.getConnection(url + dbName, userName, password) )
        {
            System.out.println("Your program has successfully connected to the MySql Database Server.");
            System.out.println("... you could query the database using the SQL commands you learned in DBMS...");
            System.out.println("... but for now, we will simply close the connection.");

            System.out.println("Your program has disconnected from the database");
        }
        catch (SQLException ex)
        {
            System.out.println("Failed to connect to database - check MySQL is running and that you are using the correct database details");
            ex.printStackTrace();
        }
    }
}

