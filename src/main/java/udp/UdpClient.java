package udp;

import java.net.*;
import java.util.Scanner;

/**
 * @author zengfanyu
 * @date 2021/4/23 19:56
 */
public class UdpClient {
    public static void main(String[] args) throws UnknownHostException {
        Scanner in = new Scanner(System.in);
        String message;
        InetAddress address = InetAddress.getByName("127.0.0.1");
        byte[] buffer =new byte[1024];

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            while (true) {
                message = in.nextLine();
                byte[] messageBytes = message.getBytes();
                //创建数据报
                DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, address, 64201);
                clientSocket.send(packet);

                //接收服务器返回的应答
                DatagramPacket rcvPacket = new DatagramPacket(buffer,0,buffer.length);
                clientSocket.receive(rcvPacket);
                message = new String(rcvPacket.getData());
                System.out.println(message);
                System.out.println("---------------");
            }
        } catch (Exception e) {
            System.err.println("Wrong!");
        }
    }
}
