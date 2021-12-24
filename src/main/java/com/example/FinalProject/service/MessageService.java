package com.example.FinalProject.service;

import com.example.FinalProject.entities.CustomLog;
import com.example.FinalProject.entities.Places;
import com.example.FinalProject.repositories.LogRepository;
import com.google.gson.Gson;
import org.hibernate.mapping.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Stateless
public class MessageService {

    private LogRepository logRepository = new LogRepository();

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

    public String sendJmsLoggingMessage(Object object, String type){
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(messageQueue);

            connection.start();

            if (type.equals("add")){
                Places place = (Places) object;
                jmsMessage = session.createTextMessage("added place " + place.getData() + " on: [" + LocalDate.now() + "]");
            } else if (type.equals("update")){
                Places place = (Places) object;
                jmsMessage = session.createTextMessage("updated place with id {"+place.getId()+"} values: " + place.getData() + " on: [" + LocalDate.now() + "]");
            } else if (type.equals("delete")){
                jmsMessage = session.createTextMessage("deleted place by id {"+object+"} on: [" + LocalDate.now() + "]");
            }
            producer.send(jmsMessage);
            if (jmsMessage != null){
                logging.add(((TextMessage) jmsMessage).getText());
                logRepository.addCustomLog(new CustomLog(null, ((TextMessage) jmsMessage).getText()));
            }

            return "success";
        } catch (final Exception e){
            throw new RuntimeException("Caught exception from JMS when sending a message", e);
        }
    }

    public String sendJmsLoggingMessageId(Long id){
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(messageQueue);

            connection.start();

            producer.send(jmsMessage);
            if (jmsMessage != null){
                logging.add(((TextMessage) jmsMessage).getText());
                logRepository.addCustomLog(new CustomLog(null, ((TextMessage) jmsMessage).getText()));
            }

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

    public ArrayList<CustomLog> getAllLogs() throws JMSException {
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            consumer = session.createConsumer(messageQueue);

            connection.start();

            final Message jmsMessage = consumer.receive(1000);

            if (jmsMessage == null) {
                ArrayList<CustomLog> errorLog = new ArrayList<>();
                errorLog.add(new CustomLog(null, "jmsMessage is null"));
                return errorLog;
            }

            TextMessage textMessage = (TextMessage) jmsMessage;
            if (textMessage == null) {
                ArrayList<CustomLog> errorLog = new ArrayList<>();
                errorLog.add(new CustomLog(null, "Empty textMessage"));
                return errorLog;
            }
            return logRepository.getAllCustomLogs();
        } catch (final Exception e) {
            throw new RuntimeException("Caught exception from JMS when receiving a message", e);
        }
    }

}
