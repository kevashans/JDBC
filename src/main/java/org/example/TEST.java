package org.example;

import org.DAOs.MySqlPlayerDao;
import org.DAOs.MySqlScoutDao;
import org.DAOs.PlayerDaoInterface;
import org.DAOs.ScoutDaoInterface;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;
import org.server.CommandFactory;

public class TEST {
    public static void main(String[] args) {



        Packet incomingPacket = new Packet(null,null);
        Packet response = null;
        incomingPacket.setCommand("FIND_PLAYER_BY_ID PL1");
        System.out.println(incomingPacket.getCommand());
        CommandFactory factory = new CommandFactory();
        Command command = factory.createCommand(incomingPacket.getCommand());
        System.out.println("command " + command);

        if (command != null) {
            response = command.createResponse(incomingPacket);
        }
        System.out.println(response.getObj());
    }
}
