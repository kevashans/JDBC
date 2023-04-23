package org.server.scoutCommands;

import org.DAOs.MySqlPlayerDao;
import org.DAOs.MySqlScoutDao;
import org.DAOs.PlayerDaoInterface;
import org.DAOs.ScoutDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class FindAllScouts implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {
        String existingScout = null;
        ScoutDaoInterface scoutDAO= new MySqlScoutDao();

        try
        {
            scoutDAO.initializeID();
            existingScout = scoutDAO.findAllScoutsJson();
        } catch (DaoException e)
        {
            throw new RuntimeException(e);
        }
        return new Packet(incomingPacket.getCommand(), existingScout);
    }
}
