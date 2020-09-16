package ru.itis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class Producer {
    public final static String EXCHANGE_NAME = "ex";
    public static String ROUTING_KEY = "";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        ObjectMapper objectMapper = new ObjectMapper();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT.toString().toLowerCase(), true);

            String info = "";
            while (!(info = br.readLine()).equals("q")) {
                Person person = processInfo(info);
                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, objectMapper.writeValueAsString(person).getBytes());
            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }

   public static Person processInfo(String info){
        String[] infoArr = info.split(" ");
        String firstName = infoArr[0];
        String lastName = infoArr[1];
        int passNumb = Integer.parseInt(infoArr[2]);
        int age = Integer.parseInt(infoArr[3]);
        String dateOfPassp = infoArr[4];

        Person person = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .passpNumb(passNumb)
                .age(age)
                .dateOfPassp(dateOfPassp)
                .build();

        return person;
    }
}

