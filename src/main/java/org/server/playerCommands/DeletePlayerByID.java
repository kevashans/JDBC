package org.server.playerCommands;

import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class DeletePlayerByID implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {

        ////returns value of 0 or 1 depending if delete succeed

        String PlayerID = incomingPacket.getCommand().substring(19);
        PlayerDaoInterface playerDAO = new MySqlPlayerDao();
        int success = 0;
        try
        {
             playerDAO.initializeID();
             if(playerDAO.deletePlayerByID(PlayerID) == 1){
                 success = 1;
             }
        }catch (DaoException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return new Packet(incomingPacket.getCommand(), String.valueOf(success));
    }
}
