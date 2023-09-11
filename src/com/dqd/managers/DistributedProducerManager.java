package com.dqd.managers;

import com.dqd.logging.Loggable;
import com.dqd.models.producer.impl.Producer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DistributedProducerManager<T> {
    private Map<String, Producer<T>> producerMap;
    private final Loggable logger;

    private static DistributedProducerManager producerManager;

    private DistributedProducerManager(Loggable logger) {
        this.logger = logger;
        this.producerMap = new ConcurrentHashMap<>();
    }

    public static DistributedProducerManager getDistributedProducerManager(Loggable logger) {
        if (producerManager == null) {
            synchronized (DistributedProducerManager.class) {
                if (producerManager == null)
                    producerManager = new DistributedProducerManager(logger);
            }
        }
        return producerManager;
    }

    public List<Producer> createProducer(int count) {
        List<Producer> producers = new ArrayList<>();
        while (count > 0) {
            Producer<T> producer = new Producer<>(logger);
            producerMap.put(producer.getProducerId(), producer);
            logger.log("Created producer with id " + producer.getProducerId());
            producers.add(producer);
            count--;
        }
        return producers;
    }

}
