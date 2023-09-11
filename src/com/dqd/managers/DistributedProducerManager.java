package com.dqd.managers;

import com.dqd.logging.Loggable;
import com.dqd.models.producer.impl.Producer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistributedProducerManager<T> {
    private Map<String, Producer<T>> producerMap;
    private final Loggable logger;

    public DistributedProducerManager(Loggable logger) {
        this.logger = logger;
        this.producerMap = new HashMap<>();
    }

    public List<Producer> createProducer(int count){
        List<Producer> producers = new ArrayList<>();
        while (count > 0){
            Producer<T> producer = new Producer<>(logger);
            producerMap.put(producer.getProducerId(), producer);
            logger.log("Created producer with id " + producer.getProducerId());
            producers.add(producer);
            count--;
        }
        return producers;
    }

}
