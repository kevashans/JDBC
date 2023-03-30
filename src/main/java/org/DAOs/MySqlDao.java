package org.DAOs;

import org.Exceptions.DaoException;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class MySqlDao {

    public Connection getConnection() throws DaoException
    {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/ca6";
        String username = "root";
        String password = "";
        Connection connection = null;

        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Failed to find driver class " + e.getMessage());
            System.exit(1);
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed " + e.getMessage());
            System.exit(2);
        }
        return connection;
    }
    public void freeConnection(Connection connection) throws DaoException
    {
        try
        {
            if (connection != null)
            {
                connection.close();
                connection = null;
            }
        }
        catch (SQLException e)
        {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }

    public void closeResources(Connection connection, PreparedStatement ps, ResultSet resultSet) throws DaoException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                freeConnection(connection);
            }
        } catch (SQLException e) {
            throw new DaoException("Error occurred while closing resources: " + e.getMessage());
        }
    }
    public void closeResourcesNoResultSet(Connection connection, PreparedStatement ps) throws DaoException {
        try {

            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                freeConnection(connection);
            }
        } catch (SQLException e) {
            throw new DaoException("Error occurred while closing resources: " + e.getMessage());
        }
    }
    public static ArrayList<Integer> readIds(){
        File inputFile = new File("idTracker.txt");
        ArrayList<Integer> IDs = new ArrayList<>();
        Scanner file;
        int playerID = 0;
        try {
            file = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (file.hasNext()) {
            playerID = file.nextInt();
        }
        IDs.add(0,playerID);
        return IDs;
    }


}
