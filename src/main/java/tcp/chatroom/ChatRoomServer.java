package tcp.chatroom;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author zengfanyu
 * @date 2021/4/22 23:22
 */
public class ChatRoomServer {
    private static ArrayList<Socket> socketList = new ArrayList<>();

    public ChatRoomServer() {
        int port = 64201;
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                socket = serverSocket.accept();
                System.out.println("用户:"+socket.getInetAddress().getHostAddress()+"进入聊天室");
                System.out.println("--------------------");
                socketList.add(socket);
                new Thread(new Server(socket)).start();
            }
        } catch (Exception e) {
            System.err.println("Error!");
        }
    }

    private static class Server implements Runnable {
        private Socket socket;


        public Server(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            String message;
            byte[] buffer = new byte[1024];
            InputStream ins;
            OutputStream os;
            try {
                while (true) {
                    //接受当前用户发送的消息
                    ins = socket.getInputStream();
                    ins.read(buffer);
                    message = new String(buffer);
                    System.out.println(message);

                    //将消息转发给所有用户
                    for (Socket s : socketList) {
                        if (s!=socket) {
                            os = s.getOutputStream();
                            os.write(message.getBytes());
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Error!");
            }
        }
    }

    public static void main(String[] args) {
        new ChatRoomServer();
    }
}
