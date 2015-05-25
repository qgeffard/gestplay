package org.kgj.pds.playlist.persistance.messagingService;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.kgj.pds.playlist.Utils.QueryMarshaller;
import org.kgj.pds.playlist.persistance.PlaylistPersistanceApplicationClient.ClientRMI;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query;

public class ClientAppMessagingServiceManager extends GenericMessageManager {
	private static ClientAppMessagingServiceManager instance = new ClientAppMessagingServiceManager(
			"tcp://localhost:61616", "consumerFromPersistence",
			"producerToPersistence");

	private ClientAppMessagingServiceManager(String url, String producerQueue,
			String consumerQueue) {
		super(url, producerQueue, consumerQueue);
	}

	public static ClientAppMessagingServiceManager getInstance() {
		return instance;
	}

	@Override
	public void messageReceived(Message message) {
		// logger.debug("Message inc "+ message.toString());
		System.out.println("Message recu :" + message.toString());

		String messageContent = null;
		try {
			messageContent = ((TextMessage) message).getText();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		Query query = QueryMarshaller.stringToQuery(messageContent);

		ClientRMI task = new ClientRMI(query);
		task.start();

	}

	

}
