package com.dqd.models.producer;

public interface Producible<T> {
    void produce(String topic, T message);
}
