package org.server.reportCommands;

import org.DAOs.MySqlReportDao;
import org.DAOs.ReportDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class FindReportByPlayerID implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {
        String existingReport = null;
        String PlayerID = incomingPacket.getCommand().substring(24);
        ReportDaoInterface reportDAO= new MySqlReportDao();

        try
        {
            reportDAO.initializeID();
            existingReport = reportDAO.findReportByPlayerIDJson(PlayerID);
            System.out.println(existingReport);
        } catch (DaoException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return new Packet(incomingPacket.getCommand(), existingReport);
    }
}
