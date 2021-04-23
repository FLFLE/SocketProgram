package socketdemo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zengfanyu
 * @date 2021/4/8 14:05
 */
public class TcpClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket ;
        String info;
        Scanner in = new Scanner(System.in);
        while (true) {
            socket = new Socket("127.0.0.1",64201);
            try (OutputStream os = socket.getOutputStream()) {
                info = in.nextLine();
                os.write(info.getBytes());
            }
            socket.close();
        }
    }
}