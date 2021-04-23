package socketdemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zengfanyu
 * @date 2021/4/22 20:14
 */
public class TcpServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(64201);
        Socket socket;
        byte[] buffer = new byte[1024];
        int len = 0;
        String info;
        while (true) {
            socket = serverSocket.accept();
            try (InputStream ins = socket.getInputStream()) {
                try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                    while ((len = ins.read(buffer)) != -1) {
                        bos.write(buffer, 0, len);
                    }
                    info = bos.toString();
                    System.out.println(info.toUpperCase());
                }
            }
            socket.close();
        }

    }
}
