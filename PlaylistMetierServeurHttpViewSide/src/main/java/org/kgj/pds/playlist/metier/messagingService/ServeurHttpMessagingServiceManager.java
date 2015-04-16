package org.kgj.pds.playlist.metier.messagingService;

import java.io.StringReader;
import java.io.StringWriter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.kgj.pds.playlist.metier.checkAndDispatch.IntegrityChecker;
import org.kgj.pds.playlist.metier.messagingProtocol.Query;


public class ServeurHttpMessagingServiceManager extends GenericMessageManager {

	private static ServeurHttpMessagingServiceManager instance = new ServeurHttpMessagingServiceManager("tcp://localhost:61616", "producerToView", "consumerFromView");
	
	private ServeurHttpMessagingServiceManager(String url, String producerQueue, String consumerQueue) {
		super(url, producerQueue, consumerQueue);

	}

	public static ServeurHttpMessagingServiceManager getInstance() {
		return instance;
	}

	@Override
	public void messageReceived(Message message) {
		logger.debug("Message inc "+ message.toString());
		IntegrityChecker integrityChecker  = new IntegrityChecker();
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("org.kgj.pds.playlist.metier.messagingProtocol");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			String messageContent = ((TextMessage) message).getText();
			Query query = stringToQuery(messageContent); 
			
			integrityChecker.entryPointCheckIntegrity(query, message);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Query stringToQuery(String queryString) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext
					.newInstance("org.kgj.pds.playlist.metier.messagingProtocol");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (Query) unmarshaller
					.unmarshal(new StringReader(queryString));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String queryToString(Query query) {
		JAXBContext jaxbContext;

		StringWriter wrt = new StringWriter();
		try {
			jaxbContext = JAXBContext
					.newInstance("org.kgj.pds.playlist.metier.messagingProtocol");
			Marshaller mar = jaxbContext.createMarshaller();
			mar.marshal(query, wrt);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return wrt.toString();
	}

}
