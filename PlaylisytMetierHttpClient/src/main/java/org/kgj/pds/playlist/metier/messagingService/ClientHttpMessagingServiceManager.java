package org.kgj.pds.playlist.metier.messagingService;

public class ClientHttpMessagingServiceManager extends GenericMessageManager{
	
	private static ClientHttpMessagingServiceManager instance = new ClientHttpMessagingServiceManager("vm://127.0.0.1", "producerToView", "consumerFromView");
	
	private ClientHttpMessagingServiceManager(String url, String producerQueue, String consumerQueue) {
		super(url, producerQueue, consumerQueue);
	}
	
	public static ClientHttpMessagingServiceManager getInstance(){
		return instance;
	}

}
