package com.dqd.managers;

import com.dqd.models.consumer.impl.Consumer;
import com.dqd.logging.Loggable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsumerManager<T> {
    private Map<String, Consumer<T>> consumerMap;
    private final Loggable loggable;

    public ConsumerManager(Loggable loggable) {
        this.loggable = loggable;
        this.consumerMap = new HashMap<>();
    }

    public List<Consumer> create(int count){
        List<Consumer> consumers = new ArrayList<>();
        while (count > 0){
            Consumer<T> consumer = new Consumer<>(loggable);
            consumerMap.put(consumer.getConsumerId(), consumer);
            loggable.log("Created consumer with id " + consumer.getConsumerId());
            consumers.add(consumer);
            count--;
        }
        return consumers;
    }

}
