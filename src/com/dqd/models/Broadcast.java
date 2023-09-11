package com.dqd.models;

public interface Broadcast<T> {
    void consumeBroadcast();
    void produce(T input);
}
