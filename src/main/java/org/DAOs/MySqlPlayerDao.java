package org.DAOs;

import org.DTOs.Player;
import org.Exceptions.DaoException;

import java.util.List;

public class MySqlPlayerDao implements  PlayerDaoInterface{
    @Override
    public List<Player> findAllPlayers() throws DaoException {
        return null;
    }
}
