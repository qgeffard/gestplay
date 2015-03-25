package org.kgj.pds.playlist.metier.messagingService;

import java.io.File;

import javax.jms.Message;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.kgj.pds.playlist.metier.generated.Query;
import org.w3c.dom.Node;

public class ClientHttpMessagingServiceManager extends GenericMessageManager {

	private static ClientHttpMessagingServiceManager instance = new ClientHttpMessagingServiceManager("vm://127.0.0.1", "producerToView", "consumerFromView");
	private static final Logger logger = Logger.getLogger(ClientHttpMessagingServiceManager.class);

	private ClientHttpMessagingServiceManager(String url, String producerQueue, String consumerQueue) {
		super(url, producerQueue, consumerQueue);
	}

	public static ClientHttpMessagingServiceManager getInstance() {
		return instance;
	}

	@Override
	public void messageReceived(Message message) {
		// TODO Auto-generated method stub
		logger.info("I receive a message from view !");
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("generated");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			Query query = (Query) unmarshaller.unmarshal((File) message); //lol
			
			logger.info(query.toString());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
