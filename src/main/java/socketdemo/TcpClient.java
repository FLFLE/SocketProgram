package socketdemo;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author zengfanyu
 * @date 2021/4/22 22:06
 */
@AllArgsConstructor
public class TcpClient {
    private String ip;
    private int port;

    public boolean sendMessage(@NotNull String message) {
        try (Socket socket = new Socket(ip, port);) {
            try (OutputStream os = socket.getOutputStream()) {
                os.write(message.getBytes());
                socket.close();
            }
        } catch (Exception e) {
            System.err.println("Something is wrong!");
            return false;
        }
        return true;
    }

    public boolean sendFile(@NotNull String path) {
        byte[] buffer = new byte[1024];
        int len;
        try (Socket socket = new Socket(ip, port)) {
            try (OutputStream os = socket.getOutputStream()) {
                try (FileInputStream fis = new FileInputStream(new File(path))) {
                    while ((len = fis.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Sending is wrong!");
            return false;
        }
        System.out.println("Success to send message");
        System.out.println("-----------------------");
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        TcpClient client = new TcpClient("127.0.0.1",64201);
    }
}
