package org.kgj.pds.playlist.metier.messagingService;

import java.net.SocketTimeoutException;
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
	protected static final Logger logger = Logger
			.getLogger(ServeurHttpViewSideMessagingServiceManager.class);
	private int nbProc = Runtime.getRuntime().availableProcessors();
	ExecutorService execute = Executors.newFixedThreadPool(nbProc);

	public GenericMessageManager(String url, String producerQueue,
			String consumerQueue) {
		logger.info("------- ACTIVEMQ -------");
		logger.info("Connection to broker starting...");
		// Connect to it and create producer / consumer
		Connection connection;
		try {
			connection = getConnection(url);
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = createProducer(producerQueue);
			consumer = createConsumer(consumerQueue);
			connection.start();
			logger.info("Connection to broker started");
		} catch (JMSException e) {

		}
		logger.info("Producer queue = " + producerQueue);
		logger.info("Consumer queue = " + consumerQueue);
		logger.info("------------------------");
	}

	private Connection getConnection(String url) {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				url);
		try {
			Connection connection = connectionFactory.createConnection();
			return connection;
		} catch (JMSException e) {
			if (e.getLinkedException() instanceof SocketTimeoutException) {
				String urlLocal = "tcp://localhost:61616";
				if (url.equals(urlLocal)) {
					logger.error("No activemq service up");
				} else {
					logger.warn(url + " not responding, try on localhost");
					return getConnection(urlLocal);
				}
			}
		}
		return null;
	}

	private MessageConsumer createConsumer(String queue) {
		try {
			Destination destination = session.createQueue(queue);
			consumer = session.createConsumer(destination);

			consumer.setMessageListener(new MessageListener() {
				public void onMessage(final Message message) {
					logger.info("message triggered - thread start");
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
			logger.info("Message Send");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public Session getSession() {
		return session;
	}

	public abstract void messageReceived(Message message);
}
