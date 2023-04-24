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
        String PlayerID = incomingPacket.getCommand().substring(18,22);
        String ScoutID = incomingPacket.getCommand().substring(23);
        ReportDaoInterface reportDAO= new MySqlReportDao();

        try
        {

            existingReport = reportDAO.findReportByIdJson(PlayerID,ScoutID);
        } catch (DaoException e)
        {
            throw new RuntimeException(e);
        }
        return new Packet(incomingPacket.getCommand(), existingReport);
    }
}
