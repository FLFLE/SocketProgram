package socketdemo;

import lombok.AllArgsConstructor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zengfanyu
 * @date 2021/4/22 22:40
 */
@AllArgsConstructor
public class TcpServer {
    private int port;

    public String messageRcv() {
        byte[] buffer = new byte[1024];
        int len = 0;
        String message = null;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            try (Socket socket = serverSocket.accept()) {
                try (InputStream ins = socket.getInputStream()) {
                    try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                        while ((len = ins.read(buffer)) != -1) {
                            bos.write(buffer, 0, len);
                        }
                        message = bos.toString();
                    }
                }
            }
        } catch (IOException ioe) {
            System.err.println("Rcving is wrong!");
        }
        return message;
    }

    public void FileRcv(String path){
       byte[] buffer = new byte[1024];
       int len = 0;
       try(ServerSocket serverSocket = new ServerSocket(port)){
            try(Socket socket = serverSocket.accept()){
                try(InputStream ins = socket.getInputStream()){
                    try(FileOutputStream fos = new FileOutputStream(new File(path))){
                        while ((len = ins.read(buffer))!=-1){
                            fos.write(buffer,0,len);
                        }
                        System.out.println("Success");
                    }
                }
            }
       }catch (Exception e){
           System.err.println("FileRcv is wrong!");
       }
    }

    public static void main(String[] args) {
        socket.program.SocketProgram.TcpServer server = new socket.program.SocketProgram.TcpServer(64201);
    }
}
