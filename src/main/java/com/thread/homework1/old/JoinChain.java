package com.thread.homework1.old;

import java.util.concurrent.TimeUnit;

public class JoinChain extends Thread{
    private String name;

    public JoinChain(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前Join线程:"+name);
    }
}
