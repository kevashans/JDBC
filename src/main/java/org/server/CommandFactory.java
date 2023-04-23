package org.server;


import org.server.playerCommands.FindAllPlayers;
import org.server.playerCommands.FindPLayerByID;
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
        }

        return newCommand;
    }
}
