package org.xt.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * 描述：
 *
 * @author : xutao
 * @date : 2022-10-11 20:58
 */
@Slf4j
public class FileUtils {
    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            log.error("close stream fail, err msg={}", e.getMessage());
        }
    }

    public static Properties load(String config) {
        Properties properties = new Properties();
        try {
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
            properties.load(stream);
        } catch (IOException e) {
            log.error("load config[{}] fail, err msg={}", e.getMessage());
        }
        return properties;
    }

    public static void saveImage(String imagePath, byte[] bytes) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(imagePath));
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(fileOutputStream);
        }
    }

    public static boolean saveImage(InputStream stream, String formatName, File output) throws IOException {
        BufferedImage bufferedImg = ImageIO.read(stream);
        if (Objects.isNull(bufferedImg)) {
            log.error("download pictures fail");
            throw new RuntimeException("download pictures fail");
        }

        return ImageIO.write(bufferedImg, formatName, output);
    }
}
