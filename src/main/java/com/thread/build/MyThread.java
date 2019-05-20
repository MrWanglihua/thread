package com.thread.build;

/**
 * 采用继承Thread类方式创建线程
 */
public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("MyThread.run()");
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        myThread1.start();
        myThread2.start();
    }
}
