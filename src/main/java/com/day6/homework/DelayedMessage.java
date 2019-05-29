package com.day6.homework;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedMessage implements Delayed {

    private Integer id; // 主键
    private String message;
    private int count;
    private long executeTime;

    public DelayedMessage(Integer id, String message, int count, long excuteTime) {
        this.id = id;
        this.message = message;
        this.count = count;
        //this.executeTime = executeTime;
        this.executeTime = TimeUnit.NANOSECONDS.convert(excuteTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long delayTime) {
        //this.executeTime = executeTime;
        this.executeTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.executeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedMessage delayedMessage = (DelayedMessage) o;
        return this.id > delayedMessage.id ? 1 : 0;
    }
}