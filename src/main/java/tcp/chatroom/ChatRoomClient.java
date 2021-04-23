package tcp.chatroom;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zengfanyu
 * @date 2021/4/22 23:22
 */
public class ChatRoomClient {
    private static Socket socket;

    public ChatRoomClient(String addr, int port) {
        try {
            socket = new Socket(addr, port);
            new Thread(new Send()).start();
            new Thread(new Receive()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Send implements Runnable {
        OutputStream os;
        Scanner in = new Scanner(System.in);
        String message;
        String information;

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                os = socket.getOutputStream();
                message = in.nextLine();
                information = "User " + socket.getInetAddress().getHostAddress() + ":\n" + message;
                os.write(information.getBytes());
            }
        }
    }

    static class Receive implements Runnable {
        byte[] buffer = new byte[1024];
        int len;
        InputStream ins;
        ByteArrayOutputStream bos;
        String message;

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                bos = new ByteArrayOutputStream();
                ins = socket.getInputStream();
                len = ins.read(buffer);
                message = new String(buffer);
                System.out.println(message);
            }

        }
    }

    public static void main(String[] args) {
        new ChatRoomClient("127.0.0.1", 64201);
    }
}
