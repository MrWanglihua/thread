package com.day5.homework;

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

    }

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            new QueueTest().doTest("Mic"+i);
        }

    }


}
