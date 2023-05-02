package org.example;
import org.DAOs.*;
import org.DTOs.Report;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;
import org.server.CommandFactory;

public class TEST {
    public static void main(String[] args) {

    ReportDaoInterface reportDao = new MySqlReportDao();
        try {
            reportDao.initializeID();
            System.out.println(reportDao.findReportByScoutID("SC05"));
            System.out.println(reportDao.findReportByPlayerID("PL03"));
            System.out.println(reportDao.findReportBySeason(2022));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }




}
