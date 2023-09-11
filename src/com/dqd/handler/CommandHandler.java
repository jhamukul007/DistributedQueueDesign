package com.dqd.handler;

import com.dqd.logging.impl.ConsoleLogger;
import com.dqd.models.consumer.impl.Consumer;
import com.dqd.managers.ConsumerManager;
import com.dqd.managers.DistributedProducerManager;
import com.dqd.models.DistributedTopic;
import com.dqd.managers.DistributedTopicManager;
import com.dqd.logging.Loggable;
import com.dqd.models.producer.impl.Producer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class CommandHandler {

    public static void main(String[] args) throws IOException {
        Loggable consoleLogger = new ConsoleLogger();
        DistributedTopicManager<String> distributedTopicManager = DistributedTopicManager.getDistributedTopicManager(consoleLogger);
        DistributedProducerManager<String> producerManager = new DistributedProducerManager<>(consoleLogger);
        ConsumerManager<String> consumerManager = new ConsumerManager<>(consoleLogger);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Create topic : ");
        int topicCount = scanner.nextInt();
        List<DistributedTopic> topics = distributedTopicManager.createTopic(topicCount);
        System.out.print("Create Producer : ");
        int producerCount = scanner.nextInt();
        List<Producer> producers = producerManager.createProducer(producerCount);

        System.out.print("Create Consumer : ");
        int consumerCount = scanner.nextInt();
        List<Consumer> consumers = consumerManager.create(consumerCount);

        distributedTopicManager.subscribeTopic(topics.get(0).getTopicId(), consumers);
        distributedTopicManager.subscribeTopic(topics.get(1).getTopicId(), List.of(consumers.get(0), consumers.get(2), consumers.get(3)));
        while (true) {
            System.out.print("Enter command : ");
            String command = scanner.next();

            switch (command) {
                case "SEND" -> {
                    System.out.print("From Producer ");
                    int producerNumber = scanner.nextInt();
                    BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Enter Message:  ");
                    String message = br.readLine();
                    System.out.print("To Topic: ");
                    int topicNumber = scanner.nextInt();

                    Producer<String> producer = producers.get(producerNumber-1);
                    producer.produce(topics.get(topicNumber-1).getTopicId(), message);
                }
                case "EXIT" -> {
                    System.exit(1);
                }
            }
        }
    }
}
