package com.dqd.models.producer.impl;

import com.dqd.exceptions.TopicNotFoundException;
import com.dqd.logging.Loggable;
import com.dqd.managers.DistributedTopicManager;
import com.dqd.models.DistributedTopic;
import com.dqd.models.producer.Producible;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@EqualsAndHashCode(of = "producerId")
@Getter
public class Producer<T> implements Producible<T> {
    private String producerId;
    private final Loggable logger;
    public Producer(Loggable logger) {
        this.logger = logger;
        this.producerId = UUID.randomUUID().toString();
    }

    @Override
    public void produce(String topicId, T message) {
        DistributedTopic<T> topic = DistributedTopicManager.getDistributedTopicManager(logger).getTopicById(topicId);
        if(topic == null)
            throw new TopicNotFoundException(String.format("Topic with id %s not found", topicId));
        logger.log(String.format("Producer %s publishing message to topic %s and message: %s", this.getProducerId(),
                topic.getTopicId(), message));
        topic.produce(message);
    }

}
