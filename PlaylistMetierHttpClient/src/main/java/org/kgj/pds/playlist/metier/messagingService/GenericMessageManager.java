package org.kgj.pds.playlist.metier.messagingService;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;

abstract class GenericMessageManager {

	protected MessageProducer producer;
	protected MessageConsumer consumer;
	protected Session session;
	protected static final Logger logger = Logger.getLogger(ClientHttpMessagingServiceManager.class);

	public GenericMessageManager(String url, String producerQueue, String consumerQueue) {
		
		try {
			BrokerService broker = new BrokerService();
			broker.setPersistent(false);
			broker.setUseJmx(false);
			broker.addConnector(url);
			broker.start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Connect to it and create producer / consumer
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection;
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			producer = createProducer(producerQueue);
			consumer = createConsumer(consumerQueue);

			connection.start();

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	private MessageConsumer createConsumer(String queue) {
		try {
			Destination destination = session.createQueue(queue);
			consumer = session.createConsumer(destination);

			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					logger.info("trigger message");
					GenericMessageManager.this.messageReceived(message);
				}
			});

		} catch (JMSException e) {
			e.printStackTrace();
		}

		return consumer;
	}

	private MessageProducer createProducer(String queue) {
		try {
			Destination destination = session.createQueue(queue);
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		} catch (JMSException e) {
			e.printStackTrace();
		}

		return producer;
	}

	public void send(String message) {
		TextMessage textMessage;
		try {
			textMessage = session.createTextMessage(message);
			producer.send(textMessage);
			logger.info("message sent");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public Session getSession() {
		return session;
	}

	public abstract void messageReceived(Message message);
}
