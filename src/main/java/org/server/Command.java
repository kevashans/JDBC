package org.server;

import org.core.Packet;

public interface Command    {
    public Packet createResponse(Packet incomingPacket);

}
