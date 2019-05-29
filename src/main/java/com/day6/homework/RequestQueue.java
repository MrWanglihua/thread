package com.day6.homework;

import java.util.*;
import java.util.concurrent.*;


public class RequestQueue {

    private static final ExecutorService service = Executors.newFixedThreadPool(10);
    //
    private static final LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private static final DelayQueue<Request> delayQueue = new DelayQueue<>();

    static List<String> list = new ArrayList<>();


    //    使用静态块启动线程
    static {
        startLinkedQueue();

        startDelayQueue();
    }

    private static void startDelayQueue() {
        service.submit(()->{
            for (;;) {
                Request message = delayQueue.take();

                if(receive(message)){
                    System.out.println("操作成功！");
                }else {
                    System.out.println("消息发送失败");
                    if (message.getDelayTime() > 30) {
                        System.out.println("没救了，放弃重试");
                    } else {
                        sendDelayMsg(message, true);
                    }
                }



            }
        });
    }

    /**
     * 通过链表实现消费者角色
     */
    private static void startLinkedQueue() {

        service.submit(() -> {
            for (; ; ) {
                Request take = linkedBlockingQueue.take();
                if (receive(take)) {
                    System.out.println("处理成功！");
                }else {
                    sendDelayMsg(take,true);
                }
            }
        });


    }

    /**
     * 将消息转到阻塞队列
     * @param message
     * @param addCount
     */
    private static void sendDelayMsg(Request message, boolean addCount) {
        // 使用线程实现消息的异步发送，防止阻塞
        if (addCount) {
            int count = message.getCount() + 1;
            long delayTime = count+10;
            message.setCount(count);
            message.setDelayTime(delayTime);
        }

        sendDelayMsg(message);

    }

    private static void sendDelayMsg(Request message) {
        service.submit(()->{
            delayQueue.add(message);
        });
    }

    /**
     * 判断是否购买成功
     *
     * @param message
     * @return
     */
    private static boolean receive(Request message) {


        if (list.size() < Ticket.length) {
//            Random random = new Random();
//            int res = random.nextInt(10);
            list.add(message.getName());
            System.out.println(message.getName() + "抢到票，编号为[" + list.indexOf(message.getName()) + "]");
            return true;

        } else {
            return false;
        }
       /* Random random = new Random();
        int res = random.nextInt(2);
        if (0 == res) {
            System.out.println("接收到消息:" + message.getName() + ",重试次数：" + message.getCount());
            return true;
        } else {
            System.out.println("接收消息失败");
            return false;
        }*/

    }


    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            Request delayedMessage = new Request( "操作者" + i, 0);
            new Thread(() -> {
                sendMessage(delayedMessage);
            }).start();
        }

        for (;;) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    refundTicket();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    private static void refundTicket() {


        service.submit(()->{
            Random random = new Random();
            int nextInt = random.nextInt(Ticket.length);
            list.remove(nextInt);

        });
    }

    private static void sendMessage(Request delayedMessage) {
        service.submit(()->{
            linkedBlockingQueue.add(delayedMessage);
        });
    }

}
