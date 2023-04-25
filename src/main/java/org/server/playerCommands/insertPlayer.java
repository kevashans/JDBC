package org.server.playerCommands;

import com.google.gson.Gson;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.DTOs.Player;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class insertPlayer implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {
        Gson g = new Gson();
        Player insertPlayer = g.fromJson(incomingPacket.getObj(), Player.class);
        PlayerDaoInterface playerDAO= new MySqlPlayerDao();


        try
        {
            playerDAO.insertPlayer(insertPlayer);
        }
        catch (DaoException de) {

        }

        return incomingPacket;
    }
}
