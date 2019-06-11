package communication.day2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceTwoSocket {

    public static void main(String[] args) {

        try {
//            创建一个serviceSocket在端口8080监听客户请求
            ServerSocket service = new ServerSocket(8080);
//            使用accept()阻塞等待客户端请求，有客户请求来则产生一个Socket对象，并继续执行。
            Socket socket = service.accept();

            String line;
//            通过Socket获得一个输入流，并构造一个BufferReader对象
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            通过Socket获得一个输出流,并构建一个PrintWriter对象
            PrintWriter os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//            由系统标准输入设备构建一个BufferReader对象
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
//            从标准输出上打印从客户端读入的字符串
            System.out.println("Client:" +is.readLine());

             line = sin.readLine();
//             如果该字符为“bye”，则停止循环
             while (!"bye".equals(line)){
//                 向客户端输出该字符
                 os.println(line);
//                 刷新输出流，使Client端马上收到该字符
                 os.flush();
                 System.out.println("Service:"+line);
                 System.out.println("Client:"+is.readLine());

                 line = sin.readLine();
             }

             is.close();
             os.close();
             socket.close();
             service.close();
        } catch (IOException e) {
            System.out.println("Error:"+e);
        }
    }

}
