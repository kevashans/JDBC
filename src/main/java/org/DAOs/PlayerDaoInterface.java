package org.DAOs;

import org.DTOs.Player;
import org.Exceptions.DaoException;

import java.util.Comparator;
import java.util.List;

public interface PlayerDaoInterface {
    public List<Player> findAllPlayers() throws DaoException;
    public Player findplayerByID( String playerID) throws DaoException;
    public void  deleteplayerByID( String playerID) throws DaoException;
    public void  insertPlayer(Player playerData) throws DaoException;
    public List<Player> findPlayerUsingFilter( Comparator<Player> comparator ) throws DaoException;
}
