package org.server;


import org.server.playerCommands.FindAllPlayers;
import org.server.playerCommands.FindPLayerByID;
import org.server.reportCommands.FindAllReports;
import org.server.reportCommands.FindReportByID;
import org.server.reportCommands.FindReportByPlayerID;
import org.server.reportCommands.FindReportByScoutID;
import org.server.scoutCommands.FindAllScouts;
import org.server.scoutCommands.FindScoutByID;

public class CommandFactory
{

    public CommandFactory()
    {

    }

    public Command createCommand(String command)
    {
        Command newCommand = null;

        if (command.contains("FIND_PLAYER_BY_ID"))
        {
            newCommand = new FindPLayerByID();
        }else if(command.contains("FIND_ALL_PLAYERS"))
        {
            newCommand = new FindAllPlayers();
        }else if(command.contains("FIND_SCOUT_BY_ID"))
        {
            newCommand = new FindScoutByID();
        }else if(command.contains("FIND_ALL_SCOUTS"))
        {
            newCommand = new FindAllScouts();
        }else if(command.contains("FIND_ALL_REPORTS"))
        {
            newCommand = new FindAllReports();
        }else if(command.contains("FIND_REPORT_BY_ID"))
        {
            newCommand = new FindReportByID();
        }else if(command.contains("FIND_REPORT_BY_SCOUT_ID"))
        {
            newCommand = new FindReportByScoutID();
        }else if(command.contains("FIND_REPORT_BY_PLAYER_ID"))
        {
            newCommand = new FindReportByPlayerID();
        }

        return newCommand;
    }
}
