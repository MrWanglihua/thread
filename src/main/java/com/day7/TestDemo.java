package com.day7;

import java.util.concurrent.ExecutorService;

public class TestDemo implements Runnable{

    static ExecutorService service = Demo.newCachedThreadPool();

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <100 ; i++) {
            service.execute(new TestDemo());
        }
        service.shutdown();
    }

}
