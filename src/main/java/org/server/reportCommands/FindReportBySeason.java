package org.server.reportCommands;

import org.DAOs.MySqlReportDao;
import org.DAOs.ReportDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class FindReportBySeason implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {

        String existingReport = null;
        String year = incomingPacket.getCommand().substring(22);
        System.out.println("year: " + year);
        ReportDaoInterface reportDAO= new MySqlReportDao();

        try
        {

            existingReport = reportDAO.findReportBySeasonJson(Integer.valueOf(year));
            System.out.println(existingReport);
        } catch (DaoException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        return new Packet(incomingPacket.getCommand(), existingReport);
    }
}
