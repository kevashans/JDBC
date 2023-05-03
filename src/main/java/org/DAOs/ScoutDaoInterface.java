package org.DAOs;

import org.DTOs.Player;
import org.DTOs.Scout;
import org.Exceptions.DaoException;

import java.util.Comparator;
import java.util.List;

public interface ScoutDaoInterface {
    public List<Scout> findAllScouts() throws DaoException;
    public void initializeID()throws DaoException;
    public Scout findScoutByID( String playerID) throws DaoException;
    public int  deleteScoutByID( String playerID) throws DaoException;
    public int insertScout(Scout playerData) throws DaoException;
    public List<Scout> findScoutUsingFilter( Comparator<Scout> comparator ) throws DaoException;
    public String findAllScoutsJson() throws DaoException;
    public String  findScoutByIdJson(String id) throws DaoException;
    public String  findScoutUsingFilterJson() throws DaoException;


}
