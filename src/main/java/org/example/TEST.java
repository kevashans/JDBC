package org.example;

import org.DAOs.*;
import org.DTOs.Report;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;
import org.server.CommandFactory;

public class TEST {
    public static void main(String[] args) {



//        Packet incomingPacket = new Packet(null,null);
//        Packet response = null;
//        incomingPacket.setCommand("FIND_REPORT_BY_SCOUT_ID SC07");
//        System.out.println(incomingPacket.getCommand());
//        CommandFactory factory = new CommandFactory();
//        Command command = factory.createCommand(incomingPacket.getCommand());
//        System.out.println("command " + command);
//
//        if (command != null) {
//            response = command.createResponse(incomingPacket);
//        }
//        System.out.println(response.getObj());



        ReportDaoInterface reportDAO= new MySqlReportDao();
//       Report r =new Report("PL01", "SC04", 2013, "great overall inside player", "ball dominant, average efficiency, better ball dominant player in harden");
        try {
//            System.out.println(reportDAO.findReportByIdJson("PL01","SC07"));
            System.out.println(reportDAO.findReportByPlayerName("LeBron James"));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
