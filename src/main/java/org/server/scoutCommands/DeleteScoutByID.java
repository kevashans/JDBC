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

        ////returns value of 0 or 1 depending if delete succeed

        String ScoutID = incomingPacket.getCommand().substring(20);
        ScoutDaoInterface scoutDAO = new MySqlScoutDao();
        int success = 0;
        try
        {
            scoutDAO.initializeID();
            if(scoutDAO.deleteScoutByID(ScoutID)==1){
                success=1;
            };
        }catch (DaoException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return new Packet(incomingPacket.getCommand(), String.valueOf(success));
    }
}
