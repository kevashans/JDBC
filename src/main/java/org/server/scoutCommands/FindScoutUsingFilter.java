package org.server.scoutCommands;

import org.Comparator.CompDOB;
import org.DAOs.MySqlScoutDao;
import org.DAOs.ScoutDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class FindScoutUsingFilter implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {
        String existingScout = null;
        ScoutDaoInterface scoutDao= new MySqlScoutDao();

        try
        {
            scoutDao.initializeID();
            existingScout = scoutDao.findScoutUsingFilter(new CompDOB()).toString();
        } catch (DaoException e)
        {
            throw new RuntimeException(e);
        }
        return new Packet(incomingPacket.getCommand(), existingScout);
    }
}
