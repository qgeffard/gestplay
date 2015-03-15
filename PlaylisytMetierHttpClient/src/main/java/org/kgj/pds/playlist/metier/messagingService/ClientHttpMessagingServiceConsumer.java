package org.kgj.pds.playlist.metier.messagingService;

import javax.jms.Message;

public class ClientHttpMessagingServiceConsumer implements TriggerReceptionOfMessage {
	private static ClientHttpMessagingServiceConsumer instance = new ClientHttpMessagingServiceConsumer();
	
	private ClientHttpMessagingServiceConsumer(){
		
	}
	
	public static ClientHttpMessagingServiceConsumer getInstance(){
		return instance;
	}
	
	public void messageReceived(Message message) {
		System.out.println(message.toString());
		
		//TODO faire un truc
		
		ClientHttpMessagingServiceManager.getInstance().send("lol");
	}

}
