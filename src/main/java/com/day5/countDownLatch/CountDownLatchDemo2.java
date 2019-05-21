package com.day5.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo2 extends Thread{

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void run() {
        try {
            countDownLatch.await();
//            TODO
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadName:"+Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <1000 ; i++) {
            new CountDownLatchDemo2().start();

        }
        countDownLatch.countDown();
    }

}
