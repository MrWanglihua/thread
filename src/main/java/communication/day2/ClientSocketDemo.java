package communication.day2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketDemo {
    public static void main(String[] args) {

        Socket socket  =null;
        PrintWriter out = null;

        try {
            socket = new Socket("127.0.0.1",8080);

            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            out.print("Hello Mic");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out!=null) out.close();
                if(socket !=null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
