package org.example.common.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：
 *
 * @author : xutao
 * @date : 2022-10-11 20:14
 */
@Slf4j
public class HttpUrlConnectionUtil {
    private static final String RANDOM_PICTURE = "https://iw233.cn/API/Random.php";

    public static void saveImage(String imagePath, byte[] bytes) throws FileNotFoundException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(imagePath);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] getFileBytes(String urlMsg) {
        try {
            URL url = new URL(urlMsg);
            // 打开链接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // get请求
            connection.setRequestMethod("GET");
            // 超时响应时间
            connection.setConnectTimeout(5 * 1000);
            //获取流
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            // 关闭输出流
            inputStream.close();
            // 获取流
            return outStream.toByteArray();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        String picDir = "G:\\pics\\";

        for (int i = 1210; i < 1500; i++) {
            byte[] bytes = getFileBytes(RANDOM_PICTURE);
            String imagePath = picDir + i + ".jpg";
            saveImage(imagePath, bytes);
            Thread.sleep(1000);
        }
    }
}
