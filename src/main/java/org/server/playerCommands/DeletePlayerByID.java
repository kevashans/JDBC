package org.server.playerCommands;

import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class DeletePlayerByID implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {

        String PlayerID = incomingPacket.getCommand().substring(20);
        PlayerDaoInterface playerDAO = new MySqlPlayerDao();
        try
        {
             playerDAO.initializeID();
             playerDAO.deletePlayerByID(PlayerID);
        }catch (DaoException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return incomingPacket;
    }
}
