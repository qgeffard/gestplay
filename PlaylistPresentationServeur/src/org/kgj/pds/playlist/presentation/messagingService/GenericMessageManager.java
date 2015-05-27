package org.kgj.pds.playlist.presentation.messagingService;

import java.net.SocketTimeoutException;

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
			.getLogger(GenericMessageManager.class);

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
			logger.error(e.getMessage());
		} catch (NullPointerException npe) {
			logger.error("There is no broker running");
		}

		logger.info("Producer queue = " + producerQueue);
		logger.info("Consumer queue = " + consumerQueue);
		logger.info("------------------------");
	}

	@SuppressWarnings("finally")
	private Connection getConnection(String url) {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				url);
		Connection connection = null;

		try {
			connection = connectionFactory.createConnection();
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
		} finally {
			return connection;
		}

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
			logger.info("\nPRES : Message Send : " +message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public Session getSession() {
		return session;
	}

	public abstract void messageReceived(Message message);
}
