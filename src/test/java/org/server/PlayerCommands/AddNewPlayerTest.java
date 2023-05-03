package org.server.PlayerCommands;

import com.google.gson.Gson;
import junit.framework.TestCase;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.DTOs.Player;
import org.core.Packet;
import org.enums;
import org.server.Command;
import org.server.playerCommands.insertPlayer;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class AddNewPlayerTest extends TestCase {

    PlayerDaoInterface playerDao = new MySqlPlayerDao();
    Command command = new insertPlayer();
    Gson gsonParser = new Gson();

    public void testInsert() {

        Player newPlayer = new Player("PL100", "JunitTest", Date.valueOf("2003-03-22"), "C", 35);
        String newPlayerJson= gsonParser.toJson(newPlayer);

        Packet expectedPacket = new Packet(enums.PlayerCommands.INSERT_PLAYER.toString(), newPlayerJson);

        String expectedPacketString = String.valueOf(expectedPacket);

        Packet incomingPacket = new Packet(enums.PlayerCommands.INSERT_PLAYER.toString(), newPlayerJson);
        Packet actualPacket = command.createResponse(incomingPacket);
        String actualPacketString = String.valueOf(actualPacket);

        assertEquals(expectedPacketString, actualPacketString);
    }
}