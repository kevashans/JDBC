package org.server.playerCommands;

import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class FindAllPlayers implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket)
    {
        ////returns list of all players in json string inside the package payload
        String existingPlayer = null;
        PlayerDaoInterface playerDAO= new MySqlPlayerDao();

        try
        {
            playerDAO.initializeID();
            existingPlayer = playerDAO.findAllPlayersJson();
        } catch (DaoException e)
        {
            throw new RuntimeException(e);
        }
        return new Packet(incomingPacket.getCommand(), existingPlayer);
    }
}
