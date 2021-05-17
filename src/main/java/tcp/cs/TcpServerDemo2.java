package tcp.cs;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zengfanyu
 * @date 2021/4/22 21:36
 */
public class TcpServerDemo2 {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(64201);) {
            Socket socket = serverSocket.accept();
            //从接收到请求后开始计时
            try (InputStream ins = socket.getInputStream()) {
                byte[] buffer = new byte[1024];
                OutputStream fos = new FileOutputStream("tcpfilercv/connect.jar");
                int len = 0;
                int i = 0;
                while ((len = ins.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
