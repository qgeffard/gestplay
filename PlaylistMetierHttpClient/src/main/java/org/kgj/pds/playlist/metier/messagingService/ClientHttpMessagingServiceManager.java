package org.kgj.pds.playlist.metier.messagingService;

import javax.jms.Message;

import org.apache.log4j.Logger;

public class ClientHttpMessagingServiceManager extends GenericMessageManager{
	
	private static ClientHttpMessagingServiceManager instance = new ClientHttpMessagingServiceManager("vm://127.0.0.1", "producerToView", "consumerFromView");
	private static final Logger logger = Logger.getLogger(ClientHttpMessagingServiceManager.class);
	
	private ClientHttpMessagingServiceManager(String url, String producerQueue, String consumerQueue) {
		super(url, producerQueue, consumerQueue);
	}
	
	public static ClientHttpMessagingServiceManager getInstance(){
		return instance;
	}

	@Override
	public void messageReceived(Message message) {
		// TODO Auto-generated method stub
		logger.info("I receive a message from view !");
	}

}
