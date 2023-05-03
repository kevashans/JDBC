package org.server.scoutCommands;


import org.DAOs.MySqlScoutDao;
import org.DAOs.ScoutDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class FindScoutByID implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {
        String existingScout = null;
        String ScoutID = incomingPacket.getCommand().substring(16);
        ScoutDaoInterface scoutDao= new MySqlScoutDao();

        try
        {
            scoutDao.initializeID();
            existingScout = scoutDao.findScoutByIdJson(ScoutID);
        } catch (DaoException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return new Packet(incomingPacket.getCommand(), existingScout);
    }
}
