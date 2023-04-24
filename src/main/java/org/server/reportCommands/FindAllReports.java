package org.server.reportCommands;

import org.DAOs.MySqlReportDao;
import org.DAOs.ReportDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class FindAllReports implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket)
    {
        String existingReport = null;
        ReportDaoInterface playerDAO= new MySqlReportDao();

        try
        {
            existingReport = playerDAO.findAllReportsJson();
        } catch (DaoException e)
        {
            throw new RuntimeException(e);
        }
        return new Packet(incomingPacket.getCommand(), existingReport);
    }
}
