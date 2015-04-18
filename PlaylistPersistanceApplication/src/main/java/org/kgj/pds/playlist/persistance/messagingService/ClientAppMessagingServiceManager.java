package org.kgj.pds.playlist.persistance.messagingService;

import java.io.StringReader;
import java.io.StringWriter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.kgj.pds.playlist.persistance.Task;
import org.kgj.pds.playlist.persistance.messagingProtocol.Query;

public class ClientAppMessagingServiceManager extends GenericMessageManager {
	private static ClientAppMessagingServiceManager instance = new ClientAppMessagingServiceManager(
			"tcp://192.168.43.198:61616", "consumerFromPersistence",
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
		Query query = stringToQuery(messageContent);
		System.out.println("login :"
				+ query.getUserManager().getUser().getLogin());
		System.out.println("password :"
				+ query.getUserManager().getUser().getPassword());

		Task task = new Task(query);
		task.start();

	}

	public Query stringToQuery(String queryString) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext
					.newInstance("org.kgj.pds.playlist.persistance.messagingProtocol");
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
					.newInstance("org.kgj.pds.playlist.persistance.messagingProtocol");
			Marshaller mar = jaxbContext.createMarshaller();
			mar.marshal(query, wrt);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return wrt.toString();
	}

}
