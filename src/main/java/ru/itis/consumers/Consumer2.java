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

public class Consumer2 {
    private final static String QUEUE_2 = "queue_2";

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_2, true, false, false, null);
            channel.queueBind(QUEUE_2, Producer.EXCHANGE_NAME, Producer.ROUTING_KEY);

            channel.basicConsume(QUEUE_2, false, (consumerTag, message) -> {
                String personAsString = new String(message.getBody());
                Person person = objectMapper.readValue(personAsString, Person.class);

                Utils.createPdf("C:/Users/gimaz/IdeaProjects/ru/itis/amqp1/pdf/Second." + UUID.randomUUID() + ".pdf", person, "second title");
            }, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }
}
