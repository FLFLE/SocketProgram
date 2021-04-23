package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

/**
 * @author zengfanyu
 * @date 2021/4/23 20:12
 */
public class UdpServer {
    public static void main(String[] args) {

        String message;
        //服务器端需要开放端口
        try (DatagramSocket serverSocket = new DatagramSocket(64201)) {
            while (true) {
                byte[] buffer = new byte[1024];
                //接受数据到buffer[]中
                DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
                serverSocket.receive(packet);

                //拆开数据包得到内容
                message = new String(packet.getData());
                int sourcePort = packet.getPort();
                InetAddress sourceAddress = packet.getAddress();


                System.out.println("Rvc success");
                System.out.println(message);

                //返回应答信息
                String backInfo = "Rcv back success";
                DatagramPacket backPacket = new DatagramPacket(backInfo.getBytes(), backInfo.getBytes().length, sourceAddress, sourcePort);
                serverSocket.send(backPacket);
                System.out.println("----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
