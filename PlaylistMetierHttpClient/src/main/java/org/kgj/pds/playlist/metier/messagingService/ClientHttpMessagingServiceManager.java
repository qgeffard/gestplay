package org.kgj.pds.playlist.metier.messagingService;

import java.io.StringReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.kgj.pds.playlist.metier.generated.Query;
import org.kgj.pds.playlist.metier.integrityAndDispatcher.IntegrityChecker;

public class ClientHttpMessagingServiceManager extends GenericMessageManager {

	private static ClientHttpMessagingServiceManager instance = new ClientHttpMessagingServiceManager("tcp://localhost:61616", "producerToView", "consumerFromView");
	private IntegrityChecker integrityChecker;
	
	private ClientHttpMessagingServiceManager(String url, String producerQueue, String consumerQueue) {
		super(url, producerQueue, consumerQueue);
		integrityChecker = new IntegrityChecker();
	}

	public static ClientHttpMessagingServiceManager getInstance() {
		return instance;
	}

	@Override
	public void messageReceived(Message message) {
		logger.debug("Message inc "+ message.toString());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("org.kgj.pds.playlist.metier.generated");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			String messageContent = ((TextMessage) message).getText();
			Query query = (Query) unmarshaller.unmarshal(new StringReader(messageContent)); 
			
			integrityChecker.entryPointCheckIntegrity(query);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
