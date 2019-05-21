package com.day5.homework;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue extends Thread{

    Lock lock = new ReentrantLock();
    Condition conditionPut = lock.newCondition();
    Condition conditionTake = lock.newCondition();

private LinkedBlockingQueue<Request> blockingQueue = new LinkedBlockingQueue<>();
private static final  int queue_size = 10;


    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                if(blockingQueue.size()<0){
                    System.out.println("队列无消息，等待中。。。");
                    conditionTake.await();
                }
                Request request = blockingQueue.take();
                System.out.println("take  data:" + request.getName());
                conditionPut.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


public void put(Request request){
try {

    lock.lock();

    if(blockingQueue.size()>1 && blockingQueue.size()<=queue_size){
        conditionTake.signalAll();
    }
    if(blockingQueue.size()==queue_size){
        System.out.println("队列已满，请等待。。。");
        conditionPut.await();

    }
    blockingQueue.add(request);
    System.out.println("put  data:" + request.getName());



} catch (InterruptedException e) {
    e.printStackTrace();
} finally {
    lock.unlock();
}




}


}
