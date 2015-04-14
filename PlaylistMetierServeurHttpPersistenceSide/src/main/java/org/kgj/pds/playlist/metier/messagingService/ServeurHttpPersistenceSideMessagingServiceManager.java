package org.kgj.pds.playlist.metier.messagingService;

import java.io.StringReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.kgj.pds.playlist.metier.messagingProtocol.Query;

@SuppressWarnings("restriction")
public class ServeurHttpPersistenceSideMessagingServiceManager extends GenericMessageManager {

	private static ServeurHttpPersistenceSideMessagingServiceManager instance = new ServeurHttpPersistenceSideMessagingServiceManager("tcp://localhost:61616",
			"producerToPersistence", "consumerFromPersistence");

	private ServeurHttpPersistenceSideMessagingServiceManager(String url, String producerQueue, String consumerQueue) {
		super(url, producerQueue, consumerQueue);
	}

	public static ServeurHttpPersistenceSideMessagingServiceManager getInstance() {
		return instance;
	}

	@Override
	public void messageReceived(Message message) {
		logger.debug("Message inc " + message.toString());

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("org.kgj.pds.playlist.metier.messagingProtocol");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			String messageContent = ((TextMessage) message).getText();
			Query query = (Query) unmarshaller.unmarshal(new StringReader(messageContent));

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}