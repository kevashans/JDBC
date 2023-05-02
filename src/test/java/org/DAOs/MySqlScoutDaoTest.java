package org.DAOs;

import junit.framework.TestCase;
import org.Comparator.CompDOB;
import org.Exceptions.DaoException;

public class MySqlScoutDaoTest extends TestCase {

    ScoutDaoInterface scoutDao = new MySqlScoutDao();
    public void testInitializeID() {
        boolean expRes = true;
        boolean actRes = false;

        try {
            scoutDao.initializeID();
            if (scoutDao.findScoutByID("SC02").toString().length()>0){
                actRes = true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }

    public void testFindAllScouts() {
        String actRes;
        try {
//            reportDao.initializeID();
            actRes = String.valueOf(scoutDao.findAllScouts());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }

    public void testFindScoutByID() {
        String actRes;
        try {
            scoutDao.initializeID();
            actRes = String.valueOf(scoutDao.findScoutByID("SC02"));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }

    public void testDeleteScoutByID() {
    }

    public void testInsertScout() {
    }

    public void testFindScoutUsingFilter() {
        String actRes;
        try {
//            reportDao.initializeID();
            actRes = String.valueOf(scoutDao.findScoutUsingFilter(new CompDOB()));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());

    }

    public void testFindAllScoutsJson() {
        String actRes;
        try {
//            reportDao.initializeID();
            actRes = String.valueOf(scoutDao.findAllScoutsJson());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());

    }

    public void testFindScoutByIdJson() {
        String actRes;
        try {
            scoutDao.initializeID();
            actRes = String.valueOf(scoutDao.findScoutByIdJson("SC02"));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());

    }
}