package com.thread.homework1.news;

import java.util.concurrent.LinkedBlockingQueue;

public class PrintProcessor extends Thread implements RequestProcessor{

    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<Request>();

    private  final RequestProcessor nextProcessor;

    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public void processRequest(Request request) {
        requests.add(request);
    }

    @Override
    public void run() {
        while(true){
            try {
                Request request = requests.take();

                System.out.println("print  data:" + request.getName());

                nextProcessor.processRequest(request);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
