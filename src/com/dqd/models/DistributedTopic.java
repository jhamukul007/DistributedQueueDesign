package com.dqd.models;

import com.dqd.models.consumer.impl.Consumer;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@EqualsAndHashCode(of = "topicId")
@Getter
public class DistributedTopic<T> implements Broadcast<T> {
    private String topicId;
    @Setter(AccessLevel.NONE)
    private BlockingQueue<T> queue;
    @Setter(AccessLevel.NONE)
    private List<Consumer> consumers;

    public DistributedTopic() {
        this.topicId = UUID.randomUUID().toString();
        this.queue = new ArrayBlockingQueue<>(1000);
        this.consumers = new ArrayList<>();
    }

    public void addConsumer(Consumer consumer) {
        this.consumers.add(consumer);
    }

    public void removeConsumer(Consumer consumer) {
        this.consumers.remove(consumer);
    }


    @Override
    public void consumeBroadcast() {
        if (!queue.isEmpty()) {
            T message = queue.poll();
            consumers.forEach(consumer -> {
                consumer.consume((String) message);
            });
        }
    }

    @Override
    public void produce(T input) {
        queue.offer(input);
        consumeBroadcast();
    }
}
