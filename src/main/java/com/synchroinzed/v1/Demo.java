package com.synchroinzed.v1;

import java.lang.reflect.Constructor;

public class Demo {
    private static int count = 0;
    /*public static void inc(){

        try {
            Thread.sleep(1);
            System.out.println("运行中："+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            count++;


    }*/

    /**
     * 使用synchronized之后
     */
    public static void inc(){


        synchronized (Demo.class){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }




    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <1000 ; i++) {
            new Thread(()->Demo.inc()).start();
        }

        Thread.sleep(3000);
        System.out.println("运行结果"+count);

    }


}
