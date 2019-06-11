package com.day7.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test2 implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    static ExecutorService service = new ThreadPoolExecutor(0,0,0,null,null);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            service.execute(new Test2());

        }
        service.shutdown();
    }

}
