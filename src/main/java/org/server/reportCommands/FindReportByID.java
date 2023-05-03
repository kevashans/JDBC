package org.server.reportCommands;

import org.DAOs.*;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class FindReportByID implements Command
{
    @Override
    public Packet createResponse(Packet incomingPacket)
    {
        String existingReport = null;
        String IDS = incomingPacket.getCommand().substring(17);
        String[] tokens = IDS.split("[,]");
        String PlayerID = tokens[0];
        String ScoutID = tokens[1];
        ReportDaoInterface reportDAO= new MySqlReportDao();

        try
        {
            reportDAO.initializeID();
            existingReport = reportDAO.findReportByIdJson(PlayerID,ScoutID);
        } catch (DaoException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return new Packet(incomingPacket.getCommand(), existingReport);
    }
}
