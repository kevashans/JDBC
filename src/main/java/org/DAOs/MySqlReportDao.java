package org.DAOs;

import com.google.gson.Gson;
import org.DTOs.Player;
import org.DTOs.Report;
import org.Exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MySqlReportDao extends MySqlDao implements ReportDaoInterface {

    private final HashSet<String> reportIds = new HashSet<>();
    private final HashSet<String> playerIds = new HashSet<>();
    private final HashSet<String> scoutIds = new HashSet<>();

    public HashSet<String> getReportIds() {
        return reportIds;
    }

    public HashSet<String> getPlayerIds() {
        return playerIds;
    }

    public HashSet<String> getScoutIds() {
        return scoutIds;
    }

    public void initializeID() throws DaoException {
        List<Report> reports = findAllReports();
        for (Report report : reports) {
            String idPair = report.getScoutID() + ":" + report.getPlayerID();
            this.reportIds.add(idPair);
            this.playerIds.add(report.getPlayerID());
            this.scoutIds.add(report.getScoutID());
        }
    }

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

        if (this.reportIds.contains(scoutID + ":" + playerID)) {
            try {
                //Get connection object using the methods in the super class (MySqlDao.java)...
                connection = this.getConnection();

                String query = "SELECT * FROM reports WHERE playerID LIKE ? and scoutID LIKE ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, playerID);
                ps.setString(2, scoutID);

                //Using a PreparedStatement to execute SQL...

                resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    String playerId = resultSet.getString("playerID");
                    String scoutId = resultSet.getString("scoutID");
                    int season = resultSet.getInt("season");
                    String positives = resultSet.getString("positives");
                    String negatives = resultSet.getString("negatives");
                    p = new Report(playerId, scoutId, season, positives, negatives);

//                usersList.add(u);
                }
                if (p == null) {
                    throw new DaoException("Report with player ID " + playerID + " and scout ID " + scoutID + " not found.");
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            } finally {
                closeResources(connection, ps, resultSet);
            }
        } else {
            throw new DaoException("Report with player ID " + playerID + " and scout ID " + scoutID + " not found.");

        }

        return p;

    }

    @Override
    public List<Report> findReportByPlayerID(String playerID) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Report p = null;
        List<Report> reportsList = new ArrayList<>();

        if (this.playerIds.contains(playerID)) {
            try {
                //Get connection object using the methods in the super class (MySqlDao.java)...
                connection = this.getConnection();

                String query = "SELECT * FROM reports WHERE playerID LIKE ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, playerID);


                //Using a PreparedStatement to execute SQL...

                resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    String playerId = resultSet.getString("playerID");
                    String scoutId = resultSet.getString("scoutID");
                    int season = resultSet.getInt("season");
                    String positives = resultSet.getString("positives");
                    String negatives = resultSet.getString("negatives");
                    p = new Report(playerId, scoutId, season, positives, negatives);

                    reportsList.add(p);
                }
                if (p == null) {
                    throw new DaoException("Report with player ID " + playerID + " not found.");
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            } finally {
                closeResources(connection, ps, resultSet);
            }
        } else {
            throw new DaoException("Report with player ID " + playerID + " not found.");

        }

        return reportsList;
    }

    @Override
    public List<Report> findReportByScoutID(String scoutID) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Report p = null;
        List<Report> reportsList = new ArrayList<>();

        if (this.scoutIds.contains(scoutID)) {
            try {
                //Get connection object using the methods in the super class (MySqlDao.java)...
                connection = this.getConnection();

                String query = "SELECT * FROM reports WHERE scoutID LIKE ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, scoutID);


                //Using a PreparedStatement to execute SQL...

                resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    String playerId = resultSet.getString("playerID");
                    String scoutId = resultSet.getString("scoutID");
                    int season = resultSet.getInt("season");
                    String positives = resultSet.getString("positives");
                    String negatives = resultSet.getString("negatives");
                    p = new Report(playerId, scoutId, season, positives, negatives);

                    reportsList.add(p);
                }
                if (p == null) {
                    throw new DaoException("Report with player ID " + scoutID + " not found.");
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            } finally {
                closeResources(connection, ps, resultSet);
            }
        } else {
            throw new DaoException("Report with player ID " + scoutID + " not found.");

        }

        return reportsList;
    }

    @Override
    public List<Report> findReportByPlayerName(String name) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Report p = null;
        List<Report> reportsList = new ArrayList<>();


        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM `player`,reports WHERE player.playerID=reports.playerID and player_name like ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");


            //Using a PreparedStatement to execute SQL...

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String playerId = resultSet.getString("playerID");
                String scoutId = resultSet.getString("scoutID");
                int season = resultSet.getInt("season");
                String positives = resultSet.getString("positives");
                String negatives = resultSet.getString("negatives");
                p = new Report(playerId, scoutId, season, positives, negatives);

                reportsList.add(p);
            }
            if (p == null) {
                throw new DaoException("Report with the name " + name + " not found.");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            closeResources(connection, ps, resultSet);
        }

        return reportsList;
    }

    @Override
    public List<Report> findReportBySeason(int year) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Report p = null;
        List<Report> reportsList = new ArrayList<>();


        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM `player`,reports WHERE player.playerID=reports.playerID and season= ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, year );


            //Using a PreparedStatement to execute SQL...

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String playerId = resultSet.getString("playerID");
                String scoutId = resultSet.getString("scoutID");
                int season = resultSet.getInt("season");
                String positives = resultSet.getString("positives");
                String negatives = resultSet.getString("negatives");
                p = new Report(playerId, scoutId, season, positives, negatives);

                reportsList.add(p);
            }
            if (p == null) {
                throw new DaoException("Report with the season " +year+ " not found.");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            closeResources(connection, ps, resultSet);
        }

        return reportsList;
    }


    @Override
    public void deleteReportByID(String playerID, String scoutID) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
//        ResultSet resultSet = null;
        int resultSet = 0;
        List<Player> usersList = new ArrayList<>();


        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "delete FROM reports WHERE playerID LIKE ? and scoutID LIKE ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, playerID);
            ps.setString(2, scoutID);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeUpdate();
            System.out.println(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            closeResourcesNoResultSet(connection, ps);
        }
    }

    @Override
    public int insertReport(Report reportData) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        int resultSet = 0;

        try {
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
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            closeResourcesNoResultSet(connection, ps);
        }
        return resultSet;

    }

    @Override
    public String findAllReportsJson() throws DaoException {
        String json;
        Gson gsonParser = new Gson();
        List<Report> reports = findAllReports();
        json = gsonParser.toJson(reports);
        return json;

    }

    @Override
    public String findReportByIdJson(String playerID, String scoutID) throws DaoException {
        Report p = findReportByID(playerID, scoutID);
        Gson gsonParser = new Gson();
        String json = gsonParser.toJson(p);
        return json;
    }

    @Override
    public String findReportByPlayerIDJson(String playerID) throws DaoException {
        List<Report> p = findReportByPlayerID(playerID);
        Gson gsonParser = new Gson();
        String json = gsonParser.toJson(p);
        return json;
    }

    @Override
    public String findReportByPlayerNameJson(String name) throws DaoException {
        List<Report> p = findReportByPlayerName(name);
        Gson gsonParser = new Gson();
        String json = gsonParser.toJson(p);
        return json;

    }

    @Override
    public String findReportByScoutIDJson(String scoutID) throws DaoException {
        List<Report> p = findReportByScoutID(scoutID);
        Gson gsonParser = new Gson();
        String json = gsonParser.toJson(p);
        return json;
    }

    @Override
    public String findReportBySeasonJson(int season) throws DaoException {
        List<Report> p = findReportBySeason(season);
        Gson gsonParser = new Gson();
        String json = gsonParser.toJson(p);
        return json;
    }
}
