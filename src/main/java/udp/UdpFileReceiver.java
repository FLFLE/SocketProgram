package udp;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author zengfanyu
 * @date 2021/4/23 20:50
 */
public class UdpFileReceiver {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(64201)) {
            byte[] buffer = new byte[1024];
            OutputStream os = new FileOutputStream("copy.rtf");
            while (true){
                DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
                socket.receive(packet);
                os.write(packet.getData());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
