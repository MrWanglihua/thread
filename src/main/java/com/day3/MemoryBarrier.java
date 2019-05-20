package com.day3;

public class MemoryBarrier {

    int value = 3;
    boolean isFinish = false;

    void exeToCPU0(){
        value = 10;
        
        isFinish = true;

    }
    void exeToCPU1(){
        if(isFinish){
            assert value==10;
        }
    }

}
