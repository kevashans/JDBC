package org.DAOs;

import junit.framework.TestCase;
import org.Exceptions.DaoException;
import org.junit.Test;

public class MySqlReportDaoTest extends TestCase {
    ReportDaoInterface reportDao = new MySqlReportDao();
    @Test
    public void testfindAllReport(){
        boolean expRes = true;
        boolean actRes = false;
        try {
            if (reportDao.findAllReports().size()>0){
                actRes=true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }

    public void testfindReportByPlayerID(){
        boolean expRes = true;
        boolean actRes = false;
        try {
            if (reportDao.findReportByPlayerID("PL02").size()>0){
                actRes=true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }

    public void testfindAllReportJson(){
        boolean expRes = true;
        boolean actRes = false;
        try {
            if (reportDao.findAllReportsJson().length()>0){
                actRes=true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }

}