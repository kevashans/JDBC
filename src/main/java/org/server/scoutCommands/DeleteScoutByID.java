package org.server.scoutCommands;

import org.DAOs.MySqlPlayerDao;
import org.DAOs.MySqlScoutDao;
import org.DAOs.PlayerDaoInterface;
import org.DAOs.ScoutDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class DeleteScoutByID implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {
        String ScoutID = incomingPacket.getCommand().substring(20);
        ScoutDaoInterface scoutDAO = new MySqlScoutDao();
        try
        {
            scoutDAO.initializeID();
            scoutDAO.deleteScoutByID(ScoutID);
        }catch (DaoException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return incomingPacket;
    }
}
