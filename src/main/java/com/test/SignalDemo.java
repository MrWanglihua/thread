package com.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SignalDemo extends Thread{

    Lock lock;
    Condition condition;

    public SignalDemo(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        System.out.println("begin -SignalDemo");
        try{
            lock.lock();
            condition.signal();
            System.out.println("begin -SignalDemo");
        }finally {
            lock.unlock();
        }

    }
}
