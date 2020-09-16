package ru.itis.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.Person;
import ru.itis.Producer;
import ru.itis.utils.Utils;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Consumer3 {
    private final static String QUEUE_3 = "queue_3";

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_3, true, false, false, null);
            channel.queueBind(QUEUE_3, Producer.EXCHANGE_NAME, Producer.ROUTING_KEY);

            channel.basicConsume(QUEUE_3, false, (consumerTag, message) -> {
                String personAsString = new String(message.getBody());
                Person person = objectMapper.readValue(personAsString, Person.class);

                Utils.createPdf("C:/Users/gimaz/IdeaProjects/ru/itis/amqp1/pdf/Third" + UUID.randomUUID() + ".pdf", person, "third title");
            }, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException();
        }
    }
}
