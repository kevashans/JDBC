package org.server;


public class CommandFactory
{

    public CommandFactory()
    {

    }

    public Command createCommand(String command)
    {
        Command newCommand = null;

        if (command == "FIND_PLAYER_BY_ID"){
            newCommand = new FindPLayerByID();
        }

        return newCommand;
    }
}
