package com.thread.v1;

import java.util.concurrent.LinkedBlockingQueue;

public class Saveprocessor extends Thread implements RequestProcessor{

    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<Request>();

    @Override
    public void run() {
        while (true){

            try {
                Request request = requests.take();

                System. out .println("begin save request info:"+request);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }

    public void processRequest(Request request) {
    requests.add(request);
    }
}
