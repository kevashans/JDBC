package org.server.PlayerCommands;

import com.google.gson.Gson;
import junit.framework.TestCase;
import org.DAOs.MySqlPlayerDao;
import org.DAOs.PlayerDaoInterface;
import org.DTOs.Player;
import org.Exceptions.DaoException;
import org.core.Packet;
import org.server.Command;
import org.server.playerCommands.FindPLayerByID;

import static org.junit.Assert.assertEquals;

public class FindAllPlayerByIDTest extends TestCase {

    PlayerDaoInterface playerDao = new MySqlPlayerDao();
    Command command = new FindPLayerByID();
    Gson gsonParser = new Gson();

    public void testInsert() {

        Packet expectedPacket = null;
        try {
            expectedPacket = new Packet("FIND_ALL_PLAYERS", playerDao.findAllPlayersJson());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        String expectedPacketString = String.valueOf(expectedPacket);

        Packet incomingPacket = new Packet("FIND_ALL_PLAYERS", "");
        Packet actualPacket = command.createResponse(incomingPacket);
        String actualPacketString = String.valueOf(actualPacket);

        assertEquals(expectedPacketString, actualPacketString);
    }
}