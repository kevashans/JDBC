package org.server.playerCommands;

import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class FindPLayerByID implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket)
    {
        String existingPlayer = null;
        String PlayerID = incomingPacket.getCommand().substring(17);
        PlayerDaoInterface playerDAO= new MySqlPlayerDao();

        try
        {
            playerDAO.initializeID();
            existingPlayer = playerDAO.findPlayerByIdJson(PlayerID);
        } catch (DaoException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return new Packet(incomingPacket.getCommand(), existingPlayer);


    }
}
