package udp;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author zengfanyu
 * @date 2021/5/17 20:02
 */
public class UDP {

    public static void main(String[] args) throws InterruptedException {
        Thread send = new Thread(new UDPFileReceiver());
        Thread receive = new Thread(new UdpClient());
        receive.start();
        send.start();
        long startTime = System.currentTimeMillis();
        send.join();
        receive.join();
        long endTime = System.currentTimeMillis();
        System.out.println("Time:" + (endTime - startTime));

    }

    static class UdpClient implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            try (DatagramSocket socket = new DatagramSocket()) {
                try (InputStream ins = new FileInputStream("C:/connect.jar")) {
                    byte[] buffer = new byte[102];
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

    static class UDPFileReceiver implements Runnable {
        @Override
        public void run() {
            try (DatagramSocket socket = new DatagramSocket(64201)) {
                byte[] buffer = new byte[102];
                OutputStream os = new FileOutputStream("udpfilercv/connect.jar");
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
                    socket.receive(packet);
                    if (packet.getLength() == 0) {
                        break;
                    }
                    os.write(packet.getData(), 0, packet.getLength());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
