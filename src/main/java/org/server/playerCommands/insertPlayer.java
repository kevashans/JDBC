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
        ////read object inside the packet (in the form of json string) and transform it into a java object
        Player insertPlayer = g.fromJson(incomingPacket.getObj(), Player.class);
        PlayerDaoInterface playerDAO= new MySqlPlayerDao();
        int success=0;

        try
        {
            if(playerDAO.insertPlayer(insertPlayer)==1){

            }
        }
        catch (DaoException de) {
            System.err.println("Error: " + de.getMessage());
        }

        return new Packet(incomingPacket.getCommand(), String.valueOf(success));

    }
}
