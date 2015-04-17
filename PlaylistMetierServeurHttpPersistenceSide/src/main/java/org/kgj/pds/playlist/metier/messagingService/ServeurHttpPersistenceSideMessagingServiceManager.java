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

import org.kgj.pds.playlist.metier.messagingProtocol.Query;
import org.kgj.playlist.metier.checkAndDispatch.DispatcherPersistenceSide;

@SuppressWarnings("restriction")
public class ServeurHttpPersistenceSideMessagingServiceManager extends
		GenericMessageManager {

	private static ServeurHttpPersistenceSideMessagingServiceManager instance = new ServeurHttpPersistenceSideMessagingServiceManager(
			"tcp://localhost:61616", "producerToPersistence",
			"consumerFromPersistence");

	private ServeurHttpPersistenceSideMessagingServiceManager(String url,
			String producerQueue, String consumerQueue) {
		super(url, producerQueue, consumerQueue);
	}

	public static ServeurHttpPersistenceSideMessagingServiceManager getInstance() {
		return instance;
	}

	@Override
	public void messageReceived(Message message) {
		logger.debug("Message inc " + message.toString());
		DispatcherPersistenceSide router = new DispatcherPersistenceSide();
		String messageContent;
		try {
			messageContent = ((TextMessage) message).getText();
			Query query = stringToQuery(messageContent);
			
			router.sendToVS(query);
			
		} catch (JMSException e) {
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
