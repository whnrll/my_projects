package com.exaple.common.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：
 *
 * @author xutao
 * @Date 2023-03-10 20:05:54
 */
@Slf4j
public class HttpUtils {

    /**
     * 携带头信息下载网络图片
     *
     * @param imageUrl 图片url
     * @param formatName 文件格式名称
     * @param localFile 下载到本地文件
     * @param headers http协议交互中header信息，如Cookie
     */
    public static void downloadImage(String imageUrl, String formatName, File localFile, Map<String, String> headers) {
        InputStream stream = null;
        try {
            URL url = new URL(imageUrl);
            URLConnection conn = url.openConnection();
            if (headers != null && !headers.isEmpty()) {
                // 设置头信息
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            conn.setDoInput(true);
            stream = conn.getInputStream();
            FileUtil.saveImage(stream, formatName, localFile);
        } catch (IOException e) {
            log.error("download image fail, err msg={}", e.getMessage());
        } finally {
            FileUtil.close(stream);
        }
    }

    /**
     * 描述：保存图像
     *
     * @param imagePath 图片路径
     * @param bytes 字节
     * @throws IOException ioexception
     */
    public static void saveImage(String imagePath, byte[] bytes) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(imagePath);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } finally {
            FileUtil.close(fileOutputStream);
        }
    }

    public static byte[] getFileBytes(String webUrl) {
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            URL url = new URL(webUrl);
            // 打开链接
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            // get请求
            connection.setRequestMethod("GET");
            // 超时响应时间
            connection.setConnectTimeout(5 * 1000);
            // 获取流
            inputStream = connection.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            // 每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            // 使用一个输入流从buffer里把数据读取出来
            while ((len = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }

            // 获取流
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("read bytes exception, path is: [{}], err msg is: [{}]", webUrl, e.getMessage());
            throw new RuntimeException("read bytes exception");
        } finally {
            FileUtil.close(byteArrayOutputStream);
            FileUtil.close(inputStream);
        }
    }

}
