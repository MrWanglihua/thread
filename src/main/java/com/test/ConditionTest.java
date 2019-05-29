package com.test;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void main(String[] args) {
       /* Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(new WaitDemo(lock,condition)).start();
        new Thread(new SignalDemo(lock,condition)).start();*/
        Random random = new Random();

        for (int i = 0; i <10 ; i++) {
            System.out.println(random.nextInt(2));
        }


    }

}
