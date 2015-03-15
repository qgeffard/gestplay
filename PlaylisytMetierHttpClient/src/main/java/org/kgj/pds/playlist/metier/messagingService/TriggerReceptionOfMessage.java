package org.kgj.pds.playlist.metier.messagingService;

import javax.jms.Message;

public interface TriggerReceptionOfMessage {

    public void messageReceived(Message message);
}
