package com.dqd.models.consumer;

public interface Consumable<T> {
    void consume(T t);
}
