package com.thread.build;

/**
 * 通过实现Runnable接口方法创建线程
 */
public class MyThread1 implements Runnable{
    public void run() {
        System.out.println("MyThread.run()");
    }
}
