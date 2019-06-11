package serializable.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerProvider {

    public static void main(String[] args) {
        ObjectInputStream ois = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            ois = new ObjectInputStream(socket.getInputStream());
            User user = (User) ois.readObject();
            System.out.println(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
