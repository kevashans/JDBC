package org.server.reportCommands;

import org.DAOs.MySqlReportDao;
import org.DAOs.ReportDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class FindReportByScoutID implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {
        String existingReport = null;
        String ScoutID = incomingPacket.getCommand().substring(24);
        ReportDaoInterface reportDAO= new MySqlReportDao();

        try
        {

            existingReport = reportDAO.findReportByScoutIDJson(ScoutID);
        } catch (DaoException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return new Packet(incomingPacket.getCommand(), existingReport);
    }
}
