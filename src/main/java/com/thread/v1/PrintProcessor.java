package com.thread.v1;

import java.util.concurrent.LinkedBlockingQueue;

public class PrintProcessor extends Thread implements RequestProcessor {

    //    消息队列集合
    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<Request>();
//         下一个请求进程
    private final RequestProcessor nextProcessor;

    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = requests.take();

                System.out.println("print  data:" + request.getName());

                nextProcessor.processRequest(request);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void processRequest(Request request) {
        this.requests.add(request);
    }
}
