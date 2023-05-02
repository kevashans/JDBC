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
        boolean expRes = true;
        boolean actRes = false;
        try {
            if (scoutDao.findAllScouts().size()>0){
                actRes=true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }

    public void testFindScoutByID() {
        boolean expRes = true;
        boolean actRes = false;

        try {
            scoutDao.initializeID();
            if (scoutDao.findScoutByID("SC02").toString().length()>0){
                actRes=true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }

    public void testDeleteScoutByID() {
    }

    public void testInsertScout() {
    }

    public void testFindScoutUsingFilter() {
        boolean expRes = true;
        boolean actRes = false;
        try {
            if (scoutDao.findScoutUsingFilter(new CompDOB()).size()>0){
                actRes=true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);

    }

    public void testFindAllScoutsJson() {
        boolean expRes = true;
        boolean actRes = false;
        try {
            if (scoutDao.findAllScoutsJson().length()>0){
                actRes=true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);

    }

    public void testFindScoutByIdJson() {
        boolean expRes = true;
        boolean actRes = false;
        try {
            scoutDao.initializeID();
            if (scoutDao.findScoutByIdJson("SC02").length()>0){
                actRes=true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);

    }
}