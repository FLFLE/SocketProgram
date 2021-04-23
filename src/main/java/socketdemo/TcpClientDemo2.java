package socketdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zengfanyu
 * @date 2021/4/22 20:14
 */
public class TcpClientDemo2 {
    public static void main(String[] args) throws IOException {
//        while (true) {
        Socket socket = new Socket("192.168.0.199", 64201);
//            Socket socket = new Socket("127.0.0.1", 64201);
        Scanner in = new Scanner(System.in);
        try (OutputStream outputStream = socket.getOutputStream()) {
            try (InputStream ins = new FileInputStream("软件盗版.docx")) {
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = ins.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
            }
        }
        socket.close();
//        }
    }
}
