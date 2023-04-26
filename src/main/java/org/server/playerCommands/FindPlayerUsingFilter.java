package org.server.playerCommands;

import org.Comparator.CompDraftYear;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class FindPlayerUsingFilter implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {
        String existingPlayer = null;
        PlayerDaoInterface playerDAO= new MySqlPlayerDao();

        try
        {
            playerDAO.initializeID();
            existingPlayer = playerDAO.findPlayerUsingFilter(new CompDraftYear()).toString();
        } catch (DaoException e)
        {
            throw new RuntimeException(e);
        }
        return new Packet(incomingPacket.getCommand(), existingPlayer);
    }
}
