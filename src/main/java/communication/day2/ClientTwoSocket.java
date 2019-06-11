package communication.day2;

import java.io.*;
import java.net.Socket;

public class ClientTwoSocket {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1",8080);

            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

            PrintWriter os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String readLine = sin.readLine();

            while (!"bye".equals(readLine)){
                os.println(readLine);
                os.flush();
                System.out.println("Client:" + readLine);
                System.out.println("Service:"+is.readLine());
                readLine = sin.readLine();
            }
            os.close();
            is.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error:"+e);
        }


    }
}
