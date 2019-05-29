package com.day5.homework;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class QueueTest {

    BlockingQueue queue;

    public QueueTest() {
        this.queue = new BlockingQueue();
        queue.start();
    }

    private void doTest(String name){
        Request request = new Request();
        request.setName(name);

        queue.put(request);
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("","");
    }

    public static void main(String[] args) {

       /* for (int i = 0; i < 20; i++) {
            new QueueTest().doTest("Mic"+i);
        }*/
        System.out.println(16>>> 2);

    }


}
