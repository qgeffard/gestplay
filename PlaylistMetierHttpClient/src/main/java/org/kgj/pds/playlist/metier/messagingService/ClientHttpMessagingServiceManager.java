package org.kgj.pds.playlist.metier.messagingService;

import java.io.StringReader;
import java.util.Iterator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.kgj.pds.playlist.metier.generated.Query;

public class ClientHttpMessagingServiceManager extends GenericMessageManager {

	private static ClientHttpMessagingServiceManager instance = new ClientHttpMessagingServiceManager("tcp://localhost:61616", "producerToView", "consumerFromView", true);

	// private static final Logger logger =
	// Logger.getLogger(ClientHttpMessagingServiceManager.class);

	private ClientHttpMessagingServiceManager(String url, String producerQueue, String consumerQueue, boolean needBroker) {
		super(url, producerQueue, consumerQueue, needBroker);
	}

	public static ClientHttpMessagingServiceManager getInstance() {
		return instance;
	}

	@Override
	public void messageReceived(Message message) {
		logger.info("I receive a message from view !");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("org.kgj.pds.playlist.metier.generated");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			String messageContent = ((TextMessage) message).getText();

			Query query = (Query) unmarshaller.unmarshal(new StringReader(messageContent)); 

			logger.info(query.toString());
			logger.info("----------------");
			logger.info(query.getStatus().getSucced());
			logger.info("----------------");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
