package url;

import lombok.SneakyThrows;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author zengfanyu
 * @date 2021/4/23 21:23
 */
public class Download {
    @SneakyThrows
    public void download(String sourceAddress, int id) {
        URL url = new URL(sourceAddress);
        byte[] buffer = new byte[1024];
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try (InputStream ins = connection.getInputStream()) {
            try (OutputStream os = new FileOutputStream("download/" + id + ".png")) {
                int length = 0;
                while ((length = ins.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                    os.flush();
                }

            }
        }
    }

    public static void main(String[] args) {
        Download source = new Download();
        String url;
        for (int i = 1; i <= 116; i++) {
            url = "http://s3.ananas.chaoxing.com/doc/8d/94/ca/96d2cc98650f4fbd808c4a32201f5402/thumb/" + i + ".png";
            source.download(url, i);
        }
    }
}
