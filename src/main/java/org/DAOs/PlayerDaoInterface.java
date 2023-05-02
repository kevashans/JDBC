package org.DAOs;

import org.DTOs.Player;
import org.Exceptions.DaoException;

import java.util.Comparator;
import java.util.List;

public interface PlayerDaoInterface {
    public List<Player> findAllPlayers() throws DaoException;
    public void initializeID()throws DaoException;
    public Player findPlayerByID(String playerID) throws DaoException;
    public void deletePlayerByID(String playerID) throws DaoException;
    public void  insertPlayer(Player playerData) throws DaoException;
    public List<Player> findPlayerUsingFilter( Comparator<Player> comparator ) throws DaoException;
    public String findAllPlayersJson() throws DaoException;
    public String  findPlayerByIdJson(String id) throws DaoException;
    public String findAllPlayersJsonFilter() throws DaoException;

    void updateId();
}
