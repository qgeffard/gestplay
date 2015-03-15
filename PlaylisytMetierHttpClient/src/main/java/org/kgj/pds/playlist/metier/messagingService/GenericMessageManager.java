package org.kgj.pds.playlist.metier.messagingService;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

abstract class GenericMessageManager {

    protected TriggerReceptionOfMessage target;

    protected MessageProducer producer;
    protected MessageConsumer consumer;
    protected Session session;
    protected Destination destination;

    public GenericMessageManager(String url, String producerQueue, String consumerQueue) {
        initialize(url);
        createProducer(producerQueue);
        createConsumer(consumerQueue);
    }

    private void createConsumer(String queue) {
        try {
            Destination destination = session.createQueue(queue);
            consumer = session.createConsumer(destination);

            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    target.messageReceived(message);
                }
            });

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void createProducer(String queue) {
        try {
            Destination destination = session.createQueue(queue);
            producer = session.createProducer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void initialize(String url) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void setMessageTarget(TriggerReceptionOfMessage target) {
        this.target = target;
    }

    protected void send(Message message) {
        try {
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        TextMessage textMessage;
        try {
            textMessage = session.createTextMessage(message);
            producer.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    protected Session getSession() {
        return session;
    }
}
