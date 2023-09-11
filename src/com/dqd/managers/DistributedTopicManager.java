package com.dqd.managers;

import com.dqd.exceptions.TopicNotFoundException;
import com.dqd.models.consumer.impl.Consumer;
import com.dqd.models.DistributedTopic;
import com.dqd.logging.Loggable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistributedTopicManager<T> {
    private Map<String, DistributedTopic<T>> distributedTopicMap;
    private final Loggable consoleLogger;
    private static DistributedTopicManager manager = null;

    private DistributedTopicManager(Loggable consoleLogger) {
        this.consoleLogger = consoleLogger;
        this.distributedTopicMap = new HashMap<>();
    }

    // Multi-Threaded singleton design pattern
    public static DistributedTopicManager getDistributedTopicManager(Loggable consoleLogger) {
        if (manager == null) {
            synchronized (DistributedTopicManager.class) {
                if (manager == null)
                    manager = new DistributedTopicManager(consoleLogger);
            }
        }
        return manager;
    }

    public List<DistributedTopic> createTopic(int number) {
        List<DistributedTopic> topics = new ArrayList<>();
        while (number > 0) {
            DistributedTopic<T> topic = new DistributedTopic<>();
            distributedTopicMap.put(topic.getTopicId(), topic);
            consoleLogger.log("Created topic with id " + topic.getTopicId());
            topics.add(topic);
            number--;
        }
        return topics;
    }

    public void subscribeTopic(String topicId, List<Consumer> consumers) {
        DistributedTopic<T> topic = distributedTopicMap.get(topicId);
        if (topic == null)
            throw new TopicNotFoundException("Topic not found with id " + topicId);
        consumers.forEach(consumer -> {
            topic.addConsumer(consumer);
            consoleLogger.log(String.format("Consumer %s added to the topic %s ", consumer.getConsumerId(), topicId));
        });
    }

    public DistributedTopic<T> getTopicById(String topicId) {
        return distributedTopicMap.get(topicId);
    }

}
