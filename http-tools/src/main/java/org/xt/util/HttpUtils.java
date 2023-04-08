package org.xt.util;

import lombok.extern.slf4j.Slf4j;
import org.xt.util.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * 描述：
 *
 * @author : xutao
 * @date : 2022-10-11 20:14
 */
@Slf4j
public class HttpUtils {

    /**
     * 携带头信息下载网络图片
     *
     * @param imageUrl   图片url
     * @param formatName 文件格式名称
     * @param localFile  下载到本地文件
     * @param headers    http协议交互中header信息，如Cookie
     * @return 下载是否成功
     */
    public static boolean downloadImageWithHeaders(String imageUrl, String formatName, File localFile, Map<String, String> headers) {
        InputStream stream = null;
        try {
            URL url = new URL(imageUrl);
            URLConnection conn = url.openConnection();
            if (headers != null && !headers.isEmpty()) {
                //设置头信息
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            conn.setDoInput(true);
            stream = conn.getInputStream();
            return FileUtils.saveImage(stream, formatName, localFile);
        } catch (IOException e) {
            log.error("download image fail, err msg={}", e.getMessage());
            return false;
        } finally {
            FileUtils.close(stream);
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

}
