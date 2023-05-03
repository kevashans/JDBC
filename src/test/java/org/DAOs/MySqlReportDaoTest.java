package org.DAOs;

import junit.framework.TestCase;
import org.Exceptions.DaoException;
import org.junit.Test;

public class MySqlReportDaoTest extends TestCase {
    ReportDaoInterface reportDao = new MySqlReportDao();

    @Test
    public void testfindAllReport(){
        String actRes;
        try {
//            reportDao.initializeID();
            actRes = String.valueOf(reportDao.findAllReports());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }
@Test
    public void testfindReportByPlayerID(){
        String actRes;
        try {
            reportDao.initializeID();
            actRes = String.valueOf(reportDao.findReportByPlayerID("PL02"));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }
    @Test
    public void testfindReportByPlayerName(){
        String actRes;
        try {
            reportDao.initializeID();
            actRes = String.valueOf(reportDao.findReportByPlayerName("lebron"));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }
    @Test
    public void testfindReportByScoutID(){
        String actRes;
        try {
            reportDao.initializeID();
            actRes = String.valueOf(reportDao.findReportByScoutID("SC02"));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }
    @Test

    public void testfindAllReportJson(){
        String actRes;
        try {
//            reportDao.initializeID();
            actRes = String.valueOf(reportDao.findAllReportsJson());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }

}