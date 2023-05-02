package org.server.scoutCommands;

import com.google.gson.Gson;
import org.DAOs.MySqlScoutDao;
import org.DAOs.ScoutDaoInterface;
import org.DTOs.Scout;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class InsertScout implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {
        Gson g = new Gson();
        Scout insertScout = g.fromJson(incomingPacket.getObj(), Scout.class);
        ScoutDaoInterface scoutDAO= new MySqlScoutDao();
        try
        {
            scoutDAO.insertScout(insertScout);
        }
        catch (DaoException de) {
            System.err.println("Error: " + de.getMessage());

        }

        return incomingPacket;
    }
}
