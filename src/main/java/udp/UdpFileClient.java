package udp;



import java.io.FileInputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zengfanyu
 * @date 2021/4/23 20:50
 */
public class UdpFileClient {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        try (DatagramSocket socket = new DatagramSocket()) {
            try (InputStream ins = new FileInputStream("C:/connect.jar")) {
                byte[] buffer = new byte[1024];
                while ((ins.read(buffer, 0, buffer.length)) != -1) {
                    DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length, address, 64201);
                    socket.send(packet);
                }
                DatagramPacket packet = new DatagramPacket(new byte[1024], 0, 0, address, 64201);
                socket.send(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
