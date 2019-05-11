package com.thread.v1;

public class Main {
    PrintProcessor printProcessor ;

    public Main() {

        Saveprocessor saveprocessor = new Saveprocessor();
        saveprocessor.start();

        this.printProcessor = new PrintProcessor(saveprocessor);
        printProcessor.start();
    }

    private void doTest(Request request){
        printProcessor.processRequest(request);
    }

    public static void main(String[] args) {
        Request request = new Request();
        request.setName("Mic");

       new Main().doTest(request);
    }

}
