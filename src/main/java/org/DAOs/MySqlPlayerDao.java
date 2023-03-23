package org.DAOs;

import org.DTOs.Player;
import org.Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPlayerDao extends MySqlDao implements  PlayerDaoInterface {

    @Override
    public List<Player> findAllPlayers() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Player> usersList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM player";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String playerId = resultSet.getString("playerID");
                String playerName = resultSet.getString("player_name");
                Date DOB = resultSet.getDate("player_birth_date");
                String position = resultSet.getString("position");
                int draftYear = resultSet.getInt("player_draft_year");
                Player u = new Player(playerId, playerName, DOB, position, draftYear);
                usersList.add(u);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllPlayeresultSet() " + e.getMessage());
        } finally {
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
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return usersList;     // may be empty
    }
}

//    public List<> findAllUsersLastNameContains( String subString ) throws DaoException {
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet resultSet = null;
//        List<User> usersList = new ArrayList<>();
//
//        try
//        {
//            //Get connection object using the methods in the super class (MySqlDao.java)...
//            connection = this.getConnection();
//
//            String query = "SELECT * FROM USER WHERE LAST_NAME LIKE ?";
//            ps = connection.prepareStatement(query);
//            ps.setString( 1, "%" + subString + "%" );
//
//            //Using a PreparedStatement to execute SQL...
//            resultSet = ps.executeQuery();
//            while (resultSet.next())
//            {
//                int userId = resultSet.getInt("USER_ID");
//                String username = resultSet.getString("USERNAME");
//                String password = resultSet.getString("PASSWORD");
//                String lastname = resultSet.getString("LAST_NAME");
//                String firstname = resultSet.getString("FIRST_NAME");
//                User u = new User(userId, firstname, lastname, username, password);
//                usersList.add(u);
//            }
//        } catch (SQLException e)
//        {
//            throw new DaoException("findAllUseresultSet() " + e.getMessage());
//        } finally
//        {
//            try
//            {
//                if (resultSet != null)
//                {
//                    resultSet.close();
//                }
//                if (ps != null)
//                {
//                    ps.close();
//                }
//                if (connection != null)
//                {
//                    freeConnection(connection);
//                }
//            } catch (SQLException e)
//            {
//                throw new DaoException("findAllUsers() " + e.getMessage());
//            }
//        }
//        return usersList;
//
//    }

