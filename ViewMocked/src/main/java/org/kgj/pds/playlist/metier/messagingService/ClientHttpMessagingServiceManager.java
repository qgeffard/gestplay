package org.kgj.pds.playlist.metier.messagingService;

import javax.jms.Message;


public class ClientHttpMessagingServiceManager extends GenericMessageManager {

	private static ClientHttpMessagingServiceManager instance = new ClientHttpMessagingServiceManager("tcp://localhost:61616", "consumerFromView", "producerToView");

	private ClientHttpMessagingServiceManager(String url, String producerQueue, String consumerQueue) {
		super(url, producerQueue, consumerQueue);
	}

	public static ClientHttpMessagingServiceManager getInstance() {
		return instance;
	}

	@Override
	public void messageReceived(Message message) {
		
	}

}
