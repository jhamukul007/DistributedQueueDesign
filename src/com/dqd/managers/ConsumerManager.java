package com.dqd.managers;

import com.dqd.models.consumer.impl.Consumer;
import com.dqd.logging.Loggable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConsumerManager<T> {
    private Map<String, Consumer<T>> consumerMap;
    private final Loggable loggable;

    private static ConsumerManager consumerManager;

    private ConsumerManager(Loggable loggable) {
        this.loggable = loggable;
        this.consumerMap = new ConcurrentHashMap<>();
    }

    public static ConsumerManager getConsumerManager(Loggable loggable) {
        if (consumerManager == null) {
            synchronized (ConsumerManager.class) {
                if (consumerManager == null)
                    consumerManager = new ConsumerManager(loggable);
            }
        }
        return consumerManager;
    }

    public List<Consumer> create(int count) {
        List<Consumer> consumers = new ArrayList<>();
        while (count > 0) {
            Consumer<T> consumer = new Consumer<>(loggable);
            consumerMap.put(consumer.getConsumerId(), consumer);
            loggable.log("Created consumer with id " + consumer.getConsumerId());
            consumers.add(consumer);
            count--;
        }
        return consumers;
    }

}
