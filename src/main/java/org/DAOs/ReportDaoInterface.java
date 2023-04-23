package org.DAOs;

import org.DTOs.Player;
import org.DTOs.Report;
import org.DTOs.Scout;
import org.Exceptions.DaoException;

import java.util.List;

public interface ReportDaoInterface {
    public List<Report> findAllReports() throws DaoException;
    public Report findReportByID(String playerID, String scoutID) throws DaoException;

    public void  deleteReportByID( String playerID, String scoutID) throws DaoException;
    public void  insertReport(Report reportData) throws DaoException;
    public String findAllReportsJson() throws DaoException;
    public String  findReportByIdJson(String playerID, String scoutID) throws DaoException;
}
