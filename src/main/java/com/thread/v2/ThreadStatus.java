package com.thread.v2;

import jdk.nashorn.internal.ir.Block;

import java.util.concurrent.TimeUnit;

public class ThreadStatus {

    public static void main(String[] args) {

//        TIME_WAITING
        new Thread(()->{
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"time_waiting").start();
//        WAITING,线程通过在ThreadStatus类锁上通过wait进行等待
        new Thread(()->{
            while(true){
                synchronized(ThreadStatus.class){
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Waiting").start();
//        线程在ThreadStatus加锁后，不会释放锁
        new Thread(new BlockDemo(),"BlockDemo1").start();
        new Thread(new BlockDemo(),"BlockDemo2").start();
    }


    static class BlockDemo extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized(BlockDemo.class){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
