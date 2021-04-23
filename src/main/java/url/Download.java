package url;

import lombok.SneakyThrows;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author zengfanyu
 * @date 2021/4/23 21:23
 */
public class Download {
    @SneakyThrows
    public void download(String sourceAddress) {
        URL url = new URL(sourceAddress);
        byte[] buffer = new byte[1024];
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try (InputStream ins = connection.getInputStream()) {
            try (OutputStream os = new FileOutputStream("sjl.html")) {
                while ((ins.read(buffer)) != -1) {
                    os.write(buffer, 0, buffer.length);
                }
            }
        }connection.disconnect();
    }

    public static void main(String[] args) {
        Download source = new Download();
        source.download("https://shaojiale.cn/?p=193");
    }
}
