package com.day5.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore信号灯
 * 用作限流
 */
public class Car extends Thread{

    int num ;
    Semaphore semaphore;

    public Car(int num, Semaphore semaphore) {
        this.num = num;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();//获取许可证
            System.out.println(" 第"+num+" 占用一个停车位");
            TimeUnit.SECONDS.sleep(20);
            System.out.println(" 第"+num+" 辆车走喽");
            semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
