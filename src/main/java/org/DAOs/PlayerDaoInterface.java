package org.DAOs;

import org.DTOs.Player;
import org.Exceptions.DaoException;

import java.util.List;

public interface PlayerDaoInterface {
    public List<Player> findAllPlayers() throws DaoException;
}
