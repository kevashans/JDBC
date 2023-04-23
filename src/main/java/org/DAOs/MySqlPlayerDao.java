package org.DAOs;

import org.DTOs.Player;
import org.Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import com.google.gson.Gson;



public class MySqlPlayerDao extends MySqlDao implements  PlayerDaoInterface {
    private HashSet<String> ids = new HashSet<>();


    public void updateId(){
        ArrayList<Integer> ids = readIds();
        if (ids.get(0)!= null){
            Player.setIdCount(ids.get(0));
        }
    }

    public void initializeID() throws DaoException {
        List<Player> players = findAllPlayers();
        for(int i=0;i<players.size();i++){
            this.ids.add(players.get(i).getId());
        }
    }
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
            throw new DaoException(e.getMessage());
        } finally {
            closeResources(connection, ps, resultSet);
        }
        return usersList;     // may be empty
    }

    @Override
    public Player findplayerByID(String playerID) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
//        List<Player> usersList = new ArrayList<>();
        Player p = null;
        if (this.ids.contains(playerID)){
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM player WHERE playerID LIKE ?";
            ps = connection.prepareStatement(query);
            ps.setString( 1,playerID );

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
            if (p == null) {
                throw new DaoException("Player with ID " + playerID + " not found.");
            }
        } catch (SQLException e)
        {
            throw new DaoException(e.getMessage());
        } finally
        {
            closeResources(connection, ps, resultSet);
        }
        }else{
            throw new DaoException("Player with ID " + playerID + " not found.");
        }
        return p;

    }

    @Override
    public void deleteplayerByID(String playerID) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
//        ResultSet resultSet = null;
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
            throw new DaoException( e.getMessage());
        } finally
        {
            closeResourcesNoResultSet(connection, ps);
        }
//        return usersList;
    }

    @Override
    public void insertPlayer(Player playerData) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        int resultSet =0;

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
            throw new DaoException(e.getMessage());
        } finally
        {
            closeResourcesNoResultSet(connection, ps);
        }

    }

    @Override
    public List<Player> findPlayerUsingFilter(Comparator<Player> playerAgeComparator) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Player> playerList = new ArrayList<>();

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
                Player player = new Player(playerId, playerName, DOB, position, draftYear);
                playerList.add(player);
            }
        } catch (SQLException e) {
            throw new DaoException( e.getMessage());
        } finally {
            closeResources(connection, ps, resultSet);
        }

        playerList.sort(playerAgeComparator); // sort the player list based on the given comparator

        return playerList;
    }

    @Override
    public String findAllPlayersJson() throws DaoException {
        String json;
        Gson gsonParser = new Gson();
        List<Player>players = findAllPlayers();
        json = gsonParser.toJson(players);
        return json;

    }

    @Override
    public String findPlayerByIdJson(String id) throws DaoException {
        Player p = findplayerByID(id);
        Gson gsonParser = new Gson();
        String json = gsonParser.toJson(p);
        return json;
    }


}



