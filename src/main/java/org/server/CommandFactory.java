package org.server;

import org.core.Utilities;
import org.enums;
import org.server.playerCommands.*;
import org.server.reportCommands.*;
import org.server.scoutCommands.*;


public class CommandFactory {

    public CommandFactory() {

    }

    public Command createCommand(String command) {
        Command newCommand = null;
        if (Utilities.isInEnum(command, enums.PlayerCommands.class)) {

            if (command.contains("FIND_PLAYER_BY_ID")) {
                newCommand = new FindPLayerByID();
            } else if (command.contains("FIND_ALL_PLAYERS")) {
                newCommand = new FindAllPlayers();
            } else if (command.contains("DELETE_PLAYER_BY_ID")) {
                newCommand = new DeletePlayerByID();
            } else if (command.contains("INSERT_PLAYER")) {
                newCommand = new insertPlayer();
            } else if (command.contains("FIND_PLAYER_USING_FILTER")) {
                newCommand = new FindPlayerUsingFilter();
            }
        }
        else if(Utilities.isInEnum(command, enums.ScoutCommands.class)){
            if(command.contains("FIND_SCOUT_BY_ID")){
                newCommand = new FindScoutByID();
            }else if(command.contains("FIND_ALL_SCOUTS")){
                newCommand = new FindAllScouts();
            }else if(command.contains("INSERT_SCOUT")){
                newCommand = new InsertScout();
            }else if(command.contains("FIND_SCOUT_USING_FILTER")){
                newCommand = new FindScoutUsingFilter();
            }else if(command.contains("FIND_ALL_SCOUTS")){
                newCommand = new FindAllScouts();
            }else if(command.contains("DELETE_SCOUT_BY_ID")){
                newCommand = new DeleteScoutByID();
            }
        }
        else if(Utilities.isInEnum(command, enums.ReportCommands.class)){
            if (command.contains("FIND_ALL_REPORTS")){
                newCommand = new FindAllReports();
            }else if(command.contains("FIND_REPORT_BY_ID")){
                newCommand = new FindReportByID();
            }else if(command.contains("FIND_REPORT_BY_PLAYER_ID")){
                newCommand = new FindReportByPlayerID();
            }else if(command.contains("FIND_REPORT_BY_SCOUT_ID")){
                newCommand = new FindReportByScoutID();
            }else if(command.contains("FIND_REPORT_BY_PLAYER_NAME")){
                newCommand = new FindReportByPlayerName();
            }else if(command.contains("FIND_REPORT_BY_SEASON")){
                newCommand = new FindReportBySeason();
            }else if(command.contains("INSERT_REPORT")){
                newCommand = new InsertReport();
            }
        }



        return newCommand;
    }
}
