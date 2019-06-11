package com.day7.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test1 implements Runnable{
    @Override
    public void run() {

        try {
//            TimeUnit.SECONDS.sleep(10);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

static ExecutorService service = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            service.execute(new Test1());
        }

        service.shutdown();
    }


}
