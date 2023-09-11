package com.dqd.logging.impl;

import com.dqd.logging.Loggable;

public class ConsoleLogger implements Loggable {

    @Override
    public void log(Object o) {
        System.out.println(o);
        System.out.println("------------------------------");
    }
}
