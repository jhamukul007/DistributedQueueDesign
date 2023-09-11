package com.dqd.models.consumer.impl;

import com.dqd.logging.Loggable;
import com.dqd.models.consumer.Consumable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@EqualsAndHashCode(of = "consumerId")
@Getter
public class Consumer<T> implements Consumable<T> {
    private String consumerId;
    private Loggable loggable;

    public Consumer(Loggable loggable) {
        this.consumerId = UUID.randomUUID().toString();
        this.loggable = loggable;
    }

    @Override
    public void consume(T message) {
        loggable.log(String.format("%s received %s ", this.consumerId, message));
    }
}
