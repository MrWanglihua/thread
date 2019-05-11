package com.thread.homework1;

import com.thread.homework1.news.PrintProcessor;
import com.thread.homework1.news.Request;
import com.thread.homework1.news.RequestProcessor;
import com.thread.homework1.news.SaveProcessor;
import com.thread.homework1.old.JoinChain;

public class HomeTest {

    static PrintProcessor printProcessor;

    public HomeTest() {

        SaveProcessor processor = new SaveProcessor();
        processor.start();

        printProcessor = new PrintProcessor(processor);
        printProcessor.start();

    }

    static void doTest(Request request){
        printProcessor.processRequest(request);
    }


    public static void main(String[] args) {
       /* JoinChain joinChainA = new JoinChain("joinChainA");
        JoinChain joinChainB = new JoinChain("joinChainB");
        JoinChain joinChainC = new JoinChain("joinChainC");
        try {
            joinChainA.start();
            joinChainA.join();
            joinChainB.start();
            joinChainB.join();
            joinChainC.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       Request request = new Request();
       request.setName("Tom");
       new HomeTest().doTest(request);


    }
}
