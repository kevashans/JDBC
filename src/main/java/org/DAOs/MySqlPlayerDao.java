package org.DAOs;

import org.DTOs.Player;
import org.Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPlayerDao extends MySqlDao implements  PlayerDaoInterface {
    private static int idCount =6;
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

    @Override
    public Player findplayerByID(String playerID) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
//        List<Player> usersList = new ArrayList<>();
        Player p = new Player();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM player WHERE playerID LIKE ?";
            ps = connection.prepareStatement(query);
            ps.setString( 1, "%" + playerID + "%" );

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                String playerId = resultSet.getString("playerID");
                String playerName = resultSet.getString("player_name");
                Date DOB = resultSet.getDate("player_birth_date");
                String position = resultSet.getString("position");
                int draftYear = resultSet.getInt("player_draft_year");
                p = new Player(playerId, playerName, DOB, position, draftYear);
//                usersList.add(u);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllUseresultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return p;

    }

    @Override
    public void deleteplayerByID(String playerID) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        int resultSet =0;
        List<Player> usersList = new ArrayList<>();


        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "delete FROM player WHERE playerID LIKE ?";
            ps = connection.prepareStatement(query);
            ps.setString( 1, "%" + playerID + "%" );

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeUpdate();
            System.out.println(resultSet);
        } catch (SQLException e)
        {
            throw new DaoException("findAllUseresultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
//        return usersList;
    }

    @Override
    public void insertPlayer(Player playerData) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        int resultSet =0;
        List<Player> usersList = new ArrayList<>();


        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "insert into player values(?,?,?,?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, playerData.getId());
            ps.setString(2, playerData.getPlayer_name());
            ps.setString(3, playerData.getDOB());
            ps.setString(4, playerData.getPosition());
            ps.setString(5, String.valueOf(playerData.getPlayer_draft_year()));

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeUpdate();
            System.out.println(resultSet);
        } catch (SQLException e)
        {
            throw new DaoException("findAllUseresultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
    }
}



