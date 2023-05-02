package org.DAOs;

import junit.framework.TestCase;
import org.Comparator.CompDraftYear;
import org.Exceptions.DaoException;

public class MySqlPlayerDaoTest extends TestCase {
PlayerDaoInterface playerDAO = new MySqlPlayerDao();
    public void testUpdateId() {
        boolean expRes = true;
        boolean actRes = false;

        try {
            playerDAO.initializeID();
            if (playerDAO.findPlayerByID("PL02").toString().length()>0){
                actRes = true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }

    public void testInitializeID() {
    }

    public void testFindAllPlayers() {
        boolean expRes = true;
        boolean actRes = false;

        try {
            playerDAO.initializeID();
            if (playerDAO.findAllPlayers().size()>0){
                actRes = true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }

    public void testFindPlayerByID() {
        boolean expRes = true;
        boolean actRes = false;

        try {
            playerDAO.initializeID();
            if (playerDAO.findPlayerByID("PL02").toString().length()>0){
                actRes = true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }


    public void testDeletePlayerByID() {

    }

    public void testInsertPlayer() {
    }

    public void testFindPlayerUsingFilter() {
        boolean expRes = true;
        boolean actRes = false;

        try {
            playerDAO.initializeID();
            if (playerDAO.findPlayerUsingFilter(new CompDraftYear()).size()>0){
                actRes = true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }


    public void testFindAllPlayersJson() {
        boolean expRes = true;
        boolean actRes = false;

        try {
            playerDAO.initializeID();
            if (playerDAO.findAllPlayersJson().length()>0){
                actRes = true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }


    public void testFindAllPlayersJsonFilter() {
        boolean expRes = true;
        boolean actRes = false;

        try {
            playerDAO.initializeID();
            if (playerDAO.findAllPlayersJsonFilter().length()>0){
                actRes = true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);
    }


    public void testFindPlayerByIdJson() {
        boolean expRes = true;
        boolean actRes = false;

        try {
            playerDAO.initializeID();
            if (playerDAO.findPlayerByIdJson("PL02").length()>0){
                actRes = true;
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expRes,actRes);

    }
}