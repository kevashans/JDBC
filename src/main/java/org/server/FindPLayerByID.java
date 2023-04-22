package org.server;

import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;

public class FindPLayerByID implements Command{
    @Override
    public Packet createResponse(Packet incomingPacket) {
        String existingPlayer = null;

        String PlayerID = incomingPacket.getObj();

        PlayerDaoInterface playerDAO= new MySqlPlayerDao();


        try
        {
            existingPlayer = playerDAO.findPlayerByIdJson(PlayerID);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        return new Packet(incomingPacket.getCommand(), existingPlayer);

    }
}
