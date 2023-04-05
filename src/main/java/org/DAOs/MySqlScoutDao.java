package org.DAOs;

import org.DTOs.Player;
import org.DTOs.Scout;
import org.Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import com.google.gson.Gson;



public class MySqlScoutDao extends MySqlDao implements  ScoutDaoInterface {
    private HashSet<String> ids = new HashSet<>();




    public void initializeID() throws DaoException {
        List<Scout> scouts = findAllScouts();
        for(int i=0;i<scouts.size();i++){
            this.ids.add(scouts.get(i).getId());
        }
    }
    @Override
    public List<Scout> findAllScouts() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Scout> usersList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM scout";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String scoutID = resultSet.getString("scoutID");
                String scoutName = resultSet.getString("scout_name");
                Date DOB = resultSet.getDate("scout_birth_date");
                Scout s = new Scout(scoutID,scoutName,DOB);
                usersList.add(s);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            closeResources(connection, ps, resultSet);
        }
        return usersList;     // may be empty
    }

    @Override
    public Scout findScoutByID(String scoutID) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
//        List<Player> usersList = new ArrayList<>();
        Scout s = null;
        if (this.ids.contains(scoutID)){
            try
            {
                //Get connection object using the methods in the super class (MySqlDao.java)...
                connection = this.getConnection();

                String query = "SELECT * FROM scout WHERE scoutID LIKE ?";
                ps = connection.prepareStatement(query);
                ps.setString( 1,   scoutID );

                //Using a PreparedStatement to execute SQL...

                resultSet = ps.executeQuery();
                while (resultSet.next())
                {
                    String scoutId = resultSet.getString("scoutID");
                    String scoutName = resultSet.getString("scout_name");
                    Date DOB = resultSet.getDate("scout_birth_date");
                     s = new Scout(scoutId,scoutName,DOB);

                }
                if (s == null) {
                    throw new DaoException("Scout with ID " + scoutID + " not found.");
                }
            } catch (SQLException e)
            {
                throw new DaoException(e.getMessage());
            } finally
            {
                closeResources(connection, ps, resultSet);
            }
        }else{
            throw new DaoException("Scout with ID " + scoutID + " not found.");
        }
        return s;

    }

    @Override
    public void deleteScoutByID(String scoutID) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
//        ResultSet resultSet = null;
        int resultSet =0;
//        List<Scout> usersList = new ArrayList<>();


        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "delete FROM scout WHERE scoutID LIKE ?";
            ps = connection.prepareStatement(query);
            ps.setString( 1, "%" + scoutID + "%" );

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
    public void insertScout(Scout playerData) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        int resultSet =0;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "insert into scout(scout_name,scout_birth_date) values(?,?)";
            ps = connection.prepareStatement(query);
//            ps.setString(1, playerData.getId());
            ps.setString(1, playerData.getScout_name());
            ps.setString(2, playerData.getDOB());
//            ps.setString(4, playerData.getPosition());
//            ps.setString(5, String.valueOf(playerData.getPlayer_draft_year()));

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
    public List<Scout> findScoutUsingFilter(Comparator<Scout> playerAgeComparator) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Scout> scoutList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM scout";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String scoutID = resultSet.getString("scoutID");
                String scoutName = resultSet.getString("scout_name");
                Date DOB = resultSet.getDate("scout_birth_date");
                Scout s = new Scout(scoutID,scoutName,DOB);
                scoutList.add(s);
            }
        } catch (SQLException e) {
            throw new DaoException( e.getMessage());
        } finally {
            closeResources(connection, ps, resultSet);
        }
        scoutList.sort(playerAgeComparator); // sort the player list based on the given comparator

        return scoutList;
    }

    @Override
    public String findAllScoutsJson() throws DaoException {
        String json;
        Gson gsonParser = new Gson();
        List<Scout>scouts = findAllScouts();
        json = gsonParser.toJson(scouts);
        return json;

    }

    @Override
    public String findScoutByIdJson(String id) throws DaoException {
        Scout p = findScoutByID(id);
        Gson gsonParser = new Gson();
        String json = gsonParser.toJson(p);
        return json;
    }


}



