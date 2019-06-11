package serializable.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientConsumer {

    public static void main(String[] args) {

        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            socket = new Socket("127.0.0.1",8080);
            User user = new User();
            user.setName("Mic");
            user.setAge(18);

             objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(user);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(objectOutputStream !=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(socket !=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
