package tcp.cs;

import lombok.SneakyThrows;
import udp.UDP;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zengfanyu
 * @date 2021/5/17 20:13
 */
public class TCP {
    public static void main(String[] args) throws InterruptedException {
        Thread send = new Thread(new TCPFileSender());
        Thread receive = new Thread(new TCPFileReceiver());
        receive.start();
        send.start();
        long startTime = System.currentTimeMillis();
        send.join();
        receive.join();
        long endTime = System.currentTimeMillis();
        System.out.println("Time:" + (endTime - startTime));
    }

    public static class TCPFileSender implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            Socket socket = new Socket("127.0.0.1", 64201);
//            Socket socket = new Socket("127.0.0.1", 64201);
            Scanner in = new Scanner(System.in);
            try (OutputStream outputStream = socket.getOutputStream()) {
                try (InputStream ins = new FileInputStream("C:/connect.jar")) {
                    byte[] buffer = new byte[102];
                    int len = 0;
                    while ((len = ins.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, len);
                    }
                }
            }
            socket.close();
        }
    }

    public static class TCPFileReceiver implements Runnable {

        @Override
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(64201);) {
                Socket socket = serverSocket.accept();
                //从接收到请求后开始计时
                try (InputStream ins = socket.getInputStream()) {
                    byte[] buffer = new byte[102];
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
}
