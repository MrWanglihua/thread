package com.day6.homework;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 消息队列
 * linkedBlockingQueue 实现消息队列
 * delayQueue 实现发送消息失败后的延时投递
 */
public class MessageQueue {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static final LinkedBlockingQueue<DelayedMessage> linkedBlockingQueue = new LinkedBlockingQueue<>();


    private static final DelayQueue<DelayedMessage> delayQueue = new DelayQueue();

    /**
     *
     * 使用静态块启动队列
     */
    static {
        startUp();

        startUpDelayQueue();
    }


    public static void startUp() {
        executorService.submit(() -> {
            for (; ; ) {
                try {
                    DelayedMessage message = linkedBlockingQueue.take();

                    if (receive(message)) {
                        System.out.println("消息发送成功 ");
                    } else {
                        System.out.println("消息发送失败");
                        sendDelayMsg(message, true);
                    }

                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public static void startUpDelayQueue() {
        executorService.submit(() -> {
            for (; ; ) {
                try {
                    DelayedMessage message = delayQueue.take();

                    if (receive(message)) {
                        System.out.println("消息发送成功");
                    } else {
                        System.out.println("消息发送失败");
                        if (message.getCount() > 3) {
                            System.out.println("没救了，放弃重试");
                        } else {
                            sendDelayMsg(message, true);
                        }

                    }

                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public static void sendMsg(DelayedMessage message) {
        // 使用线程实现消息的异步发送，防止阻塞
        executorService.submit(() -> {
            linkedBlockingQueue.add(message);
        });
    }

    public static void sendDelayMsg(DelayedMessage message, boolean addCount) {
        // 使用线程实现消息的异步发送，防止阻塞
        if (addCount) {
            int count = message.getCount() + 1;
            long delayTime = count * 10;
            message.setExecuteTime(delayTime);
            message.setCount(count);
        }

        sendDelayMsg(message);
    }


    public static void sendDelayMsg(DelayedMessage message) {
        // 使用线程实现消息的异步发送，防止阻塞
        executorService.submit(() -> {
            delayQueue.add(message);
        });
    }


    /**
     * 模拟接收消息的接口
     *
     * @return
     */
    public static boolean receive(DelayedMessage message) {

        Random random = new Random();
        int res = random.nextInt(2);
        if (0 == res) {
            System.out.println("接收到消息:" + message.getMessage() + ",重试次数：" + message.getCount());
            return true;
        } else {
            System.out.println("接收消息失败");
            return false;
        }

    }

    public static void main(String[] args) {

        for (int i = 0; i < 50; i++) {
            DelayedMessage delayedMessage = new DelayedMessage(i, "消息" + i, 0, 0);
            new Thread(() -> {
                sendMsg(delayedMessage);
            }).start();
        }
    }
}