package org.server.PlayerCommands;

import com.google.gson.Gson;
import junit.framework.TestCase;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.DTOs.Player;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.junit.Test;
import org.server.Command;
import org.server.playerCommands.FindPLayerByID;

import static org.junit.Assert.assertEquals;

public class FindAllPlayersTest extends TestCase {

    PlayerDaoInterface playerDao = new MySqlPlayerDao();
    Command command = new FindPLayerByID();
    Gson gsonParser = new Gson();
@Test
    public void testInsert() {

        String playerID = "PL02";
        Packet expectedPacket = null;
        try {
            playerDao.initializeID();

            expectedPacket = new Packet("FIND_PLAYER_BY_ID" + playerID,  playerDao.findPlayerByIdJson(playerID));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        String expectedPacketString = String.valueOf(expectedPacket.getObj());

        Packet incomingPacket = new Packet("FIND_PLAYER_BY_IDPL02", playerID);
        Packet actualPacket = command.createResponse(incomingPacket);
        String actualPacketString = String.valueOf(actualPacket.getObj());

        assertEquals(expectedPacketString, actualPacketString);
    }
}