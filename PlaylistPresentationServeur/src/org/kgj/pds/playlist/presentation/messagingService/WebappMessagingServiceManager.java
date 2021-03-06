package org.kgj.pds.playlist.presentation.messagingService;

import java.io.StringReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.kgj.pds.playlist.presentation.messagingProtocol.Query;
import org.kgj.playlist.presentation.processingService.*;
import org.kgj.playlist.presentation.processingService.Process;

@SuppressWarnings("unused")
public class WebappMessagingServiceManager extends GenericMessageManager {

	private static WebappMessagingServiceManager instance = new WebappMessagingServiceManager("tcp://192.168.43.32:61616", "consumerFromView", "producerToView");
	
	public WebappMessagingServiceManager(String url, String producerQueue, String consumerQueue) {
		super(url, producerQueue, consumerQueue);
	}

	public static WebappMessagingServiceManager getInstance() {
		return instance;
	}

	@Override
	public void messageReceived(Message message) {
		logger.debug("Message inc "+ message.toString());
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("org.kgj.pds.playlist.presentation.messagingProtocol");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			String messageContent = ((TextMessage) message).getText();
			Query query = (Query) unmarshaller.unmarshal(new StringReader(messageContent)); 
			logger.info("\nPRES : Request : "+messageContent);
			Process process = new Process(query);

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
