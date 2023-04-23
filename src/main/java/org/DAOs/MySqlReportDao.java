package org.DAOs;

import com.google.gson.Gson;
import org.DTOs.Player;
import org.DTOs.Report;
import org.DTOs.Scout;
import org.Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlReportDao extends MySqlDao implements ReportDaoInterface{

    @Override
    public List<Report> findAllReports() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Report> reportsList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM reports";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String playerId = resultSet.getString("playerID");
                String scoutId = resultSet.getString("scoutID");
                int season = resultSet.getInt("season");
                String positives = resultSet.getString("positives");
                String negatives = resultSet.getString("negatives");
                Report u = new Report(playerId, scoutId, season, positives, negatives);
                reportsList.add(u);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            closeResources(connection, ps, resultSet);
        }
        return reportsList;
    }

    @Override
    public Report findReportByID(String playerID, String scoutID) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Report p = null;

            try
            {
                //Get connection object using the methods in the super class (MySqlDao.java)...
                connection = this.getConnection();

                String query = "SELECT * FROM reports WHERE playerID LIKE ? and scoutID LIKE ?";
                ps = connection.prepareStatement(query);
                ps.setString( 1,playerID );
                ps.setString( 2,scoutID );

                //Using a PreparedStatement to execute SQL...

                resultSet = ps.executeQuery();
                while (resultSet.next())
                {
                    String playerId = resultSet.getString("playerID");
                    String scoutId = resultSet.getString("scoutID");
                    int season = resultSet.getInt("season");
                    String positives = resultSet.getString("positives");
                    String negatives = resultSet.getString("negatives");
                    p = new Report(playerId, scoutId, season, positives, negatives);

//                usersList.add(u);
                }
                if (p == null) {
                    throw new DaoException("Report with player ID " + playerID + " and scout ID "+ scoutID+" not found.");
                }
            } catch (SQLException e)
            {
                throw new DaoException(e.getMessage());
            } finally
            {
                closeResources(connection, ps, resultSet);
            }

        return p;

    }

    @Override
    public void deleteReportByID(String playerID, String scoutID) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
//        ResultSet resultSet = null;
        int resultSet =0;
        List<Player> usersList = new ArrayList<>();


        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "delete FROM reports WHERE playerID LIKE ? and scoutID LIKE ?";
            ps = connection.prepareStatement(query);
            ps.setString( 1, playerID );
            ps.setString( 1, scoutID );

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
    }

    @Override
    public void insertReport(Report reportData) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        int resultSet =0;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "insert into reports values(?,?,?,?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, reportData.getPlayerID());
            ps.setString(2, reportData.getScoutID());
            ps.setInt(3, reportData.getSeason());
            ps.setString(4, reportData.getPositives());
            ps.setString(5, reportData.getNegatives());

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
    public String findAllReportsJson() throws DaoException {
        String json;
        Gson gsonParser = new Gson();
        List<Report>reports = findAllReports();
        json = gsonParser.toJson(reports);
        return json;

    }

    @Override
    public String findReportByIdJson(String playerID, String scoutID) throws DaoException {
        Report p = findReportByID(playerID,scoutID);
        Gson gsonParser = new Gson();
        String json = gsonParser.toJson(p);
        return json;
    }
}
