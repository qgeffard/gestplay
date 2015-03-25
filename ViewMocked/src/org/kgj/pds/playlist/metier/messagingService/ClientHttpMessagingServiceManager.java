package org.kgj.pds.playlist.metier.messagingService;

import java.util.logging.Logger;
import javax.jms.Message;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class ClientHttpMessagingServiceManager extends GenericMessageManager {

	private static ClientHttpMessagingServiceManager instance = new ClientHttpMessagingServiceManager("vm://127.0.0.1", "producerToView", "consumerFromView");

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
