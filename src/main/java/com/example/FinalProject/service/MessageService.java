package com.example.FinalProject.service;

import com.example.FinalProject.entities.Places;
import com.google.gson.Gson;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.ArrayList;

@Stateless
public class MessageService {

    private final static Gson gson = new Gson();
    private final ArrayList<String> logging = new ArrayList<>();
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private MessageConsumer consumer;
    private Message jmsMessage;

    @Resource(name = "messageQueue")
    private Queue messageQueue;

    @Resource
    private ConnectionFactory connectionFactory;

    public String sendJmsLoggingMessage(Places place){
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(messageQueue);

            connection.start();

            jmsMessage = session.createTextMessage("added place " + place.getData());
            producer.send(jmsMessage);
            logging.add(String.valueOf(jmsMessage));

            return "success";
        } catch (final Exception e){
            throw new RuntimeException("Caught exception from JMS when sending a message", e);
        }
    }

    public String getMessage() throws JMSException {
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            consumer = session.createConsumer(messageQueue);

            connection.start();

            final Message jmsMessage = consumer.receive(1000);

            if (jmsMessage == null) {
                return "jmsMessage is null";
            }

            TextMessage textMessage = (TextMessage) jmsMessage;
            if (textMessage == null) {
                return "Empty textMessage";
            }
            return textMessage.getText();
        } catch (final Exception e) {
            throw new RuntimeException("Caught exception from JMS when receiving a message", e);
        }
    }

}
