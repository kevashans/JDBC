package org.server.reportCommands;

import com.google.gson.Gson;
import org.DAOs.MySqlReportDao;
import org.DAOs.ReportDaoInterface;
import org.DTOs.Player;
import org.DTOs.Report;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;

public class InsertReport implements Command {
    @Override
    public Packet createResponse(Packet incomingPacket) {
        Gson g = new Gson();
        Report insertReport = g.fromJson(incomingPacket.getObj(), Report.class);
        ReportDaoInterface reportDAO= new MySqlReportDao();
        int success=0;
        try
        {
            if(reportDAO.insertReport(insertReport)==1){
                success = 1;
            }
        }
        catch (DaoException de) {
            System.err.println("Error: " + de.getMessage());
        }

        return new Packet(incomingPacket.getCommand(), String.valueOf(success));

    }
}
