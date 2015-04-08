package org.kgj.pds.playlist.metier.messagingService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import org.apache.log4j.Logger;

abstract class GenericMessageManager {

	protected MessageProducer producer;
	protected MessageConsumer consumer;
	protected Session session;
	protected static final Logger logger = Logger.getLogger(ClientHttpMessagingServiceManager.class);
	private int nbProc = Runtime.getRuntime().availableProcessors();
	ExecutorService execute = Executors.newFixedThreadPool(nbProc);

	public GenericMessageManager(String url, String producerQueue, String consumerQueue) {

		// Connect to it and create producer / consumer
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
				public void onMessage(final Message message) {
					logger.info("message triggered - thread start");
					execute.execute(new Runnable() {

						public void run() {
							GenericMessageManager.this.messageReceived(message);
						}
					});
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
