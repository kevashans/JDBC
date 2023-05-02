package org.DAOs;

import junit.framework.TestCase;
import org.Comparator.CompDraftYear;
import org.Exceptions.DaoException;

public class MySqlPlayerDaoTest extends TestCase {
PlayerDaoInterface playerDAO = new MySqlPlayerDao();

    public void testInitializeID() {
        String actRes;
        try {
            playerDAO.initializeID();
            actRes = playerDAO.findPlayerByID("PL02").toString();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }

    public void testFindAllPlayers() {
        String actRes;
        try {
            playerDAO.initializeID();
            actRes = playerDAO.findAllPlayers().toString();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }

    public void testFindPlayerByID() {
        String actRes;
        try {
            playerDAO.initializeID();
            actRes = playerDAO.findPlayerByID("PL02").toString();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }


    public void testInsertPlayer() {
    }

    public void testFindPlayerUsingFilter() {
        String actRes;
        try {
            playerDAO.initializeID();
            actRes = playerDAO.findPlayerUsingFilter(new CompDraftYear()).toString();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }


    public void testFindAllPlayersJson() {
        String actRes;
        try {
            playerDAO.initializeID();
            actRes = playerDAO.findAllPlayersJson();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }


    public void testFindAllPlayersJsonFilter() {
        String actRes;
        try {
            playerDAO.initializeID();
            actRes = playerDAO.findAllPlayersJsonFilter();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }


    public void testFindPlayerByIdJson() {
        String actRes;
        try {
            playerDAO.initializeID();
            actRes = playerDAO.findPlayerByIdJson("PL02");
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        assertFalse(actRes.isEmpty());
    }
}