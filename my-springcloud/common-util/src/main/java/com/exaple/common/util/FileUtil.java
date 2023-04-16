package com.exaple.common.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.filefilter.*;
import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：
 * 描述：
 *
 * @author xutao
 * @Date 2023-03-10 20:19:22
 */
@Slf4j
public class FileUtil {
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
            log.error("load config[{}] fail, err msg is: {}", config, e.getMessage());
        }
        return properties;
    }

    public static void saveImage(String imagePath, byte[] bytes) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(imagePath);
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

    /**
     * 删除指定目录下指定前缀的文件，包含子孙目录
     *
     * @param dir 指定目录
     * @param prefix 前缀数组
     */
    public static void deleteFilesWithPrefix(String dir, String[] prefix) {
        if (StringUtils.isBlank(dir) || prefix == null) {
            return;
        }

        File file = org.apache.commons.io.FileUtils.getFile(dir);
        Collection<File> files = org.apache.commons.io.FileUtils.listFiles(file, new PrefixFileFilter(prefix), TrueFileFilter.INSTANCE);
        deleteFile(files);
    }

    /**
     * 删除指定目录下指定后缀的文件，包含子孙目录
     *
     * @param dir 指定目录
     * @param suffix 后缀数组
     */
    public static void deleteFilesWithSuffix(String dir, String[] suffix) {
        if (StringUtils.isBlank(dir) || suffix == null) {
            return;
        }

        File file = org.apache.commons.io.FileUtils.getFile(dir);
        Collection<File> files = org.apache.commons.io.FileUtils.listFiles(file, new SuffixFileFilter(suffix), TrueFileFilter.INSTANCE);
        deleteFile(files);
    }

    public static void deleteFiles(String dir, String fileName) {
        if (StringUtils.isBlank(dir) || StringUtils.isBlank(fileName)) {
            return;
        }

        File file = org.apache.commons.io.FileUtils.getFile(dir);
        Collection<File> files = org.apache.commons.io.FileUtils.listFiles(file, new NameFileFilter(fileName), TrueFileFilter.INSTANCE);
        deleteFile(files);
    }

    /**
     * 移动指定目录下带有指定后缀的文件到新的目录
     *
     * @param srcDir srcDir
     * @param destDir destDir
     * @param suffix suffix
     * @throws Exception Exception
     */
    public static void moveFile(String srcDir, String destDir, String[] suffix) throws Exception {
        Collection<File> files = org.apache.commons.io.FileUtils.listFiles(org.apache.commons.io.FileUtils.getFile(srcDir), new SuffixFileFilter(suffix), TrueFileFilter.INSTANCE);
        files.forEach(o -> {
            try {
                org.apache.commons.io.FileUtils.moveFileToDirectory(o, org.apache.commons.io.FileUtils.getFile(destDir), true);
            } catch (IOException e) {
                System.out.println("移动文件：" + o.getAbsolutePath() + "异常。错误消息：" + e.getMessage());
            }
        });
    }

    /**
     * 替换目录下所有文件的文件名匹配的字符
     *
     * @param dir 指定目录
     * @param regex 匹配字符串
     */
    public static void modifyFileName(String dir, String regex, String replacement) {
        if (StringUtils.isBlank(dir)) {
            return;
        }

        modifyFileName(listFiles(dir), regex, replacement);
    }

    public static void modifyFileNameSuffix(String dir, String suffix) {
        if (StringUtils.isBlank(dir)) {
            return;
        }

        modifyFileNameSuffix(listFiles(dir), suffix);
    }

    /**
     * 获取目录下的所有文件
     *
     * @param dir 目录
     * @return 所有文件
     */
    public static Collection<File> listFiles(String dir) {
        File file = org.apache.commons.io.FileUtils.getFile(dir);
        return org.apache.commons.io.FileUtils.listFiles(file, FileFileFilter.FILE, TrueFileFilter.INSTANCE);
    }


    public static void addSuffix(String dir, String suffix) {
        File file = org.apache.commons.io.FileUtils.getFile(dir);
        Collection<File> files = org.apache.commons.io.FileUtils.listFiles(file, FileFileFilter.FILE, TrueFileFilter.INSTANCE);
        Iterator<File> iterator = files.iterator();
        while (iterator.hasNext()) {
            File next = iterator.next();
            String parent = next.getParent();
            String name = next.getName();
            boolean renameTo = next.renameTo(new File(parent, name + suffix));
            if (!renameTo) {
                System.out.println(name);
            }
        }
    }

    private static void modifyFileName(Collection<File> files, String removeStr, String replacement) {
        Iterator<File> iterator = files.iterator();
        while (iterator.hasNext()) {
            File next = iterator.next();
            String parent = next.getParent();
            String name = next.getName();
            log.info("parent={}, file={}", parent, name);
            String replace = name.replaceFirst(removeStr, replacement);
            boolean renameTo = next.renameTo(new File(parent, replace));
            if (!renameTo) {
                System.out.println(name);
            }
        }
    }

    private static void modifyFileNameSuffix(Collection<File> files, String suffix) {
        Iterator<File> iterator = files.iterator();
        while (iterator.hasNext()) {
            try {
                File next = iterator.next();
                String canonicalPath = next.getCanonicalPath();
                String newFileName = canonicalPath.substring(0, canonicalPath.lastIndexOf(".")) + suffix;
                boolean renameTo = next.renameTo(new File(newFileName));
                if (!renameTo) {
                    System.out.println(next.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void deleteFile(Collection<File> files) {
        Iterator<File> iterator = files.iterator();
        while (iterator.hasNext()) {
            File next = iterator.next();
            try {
                org.apache.commons.io.FileUtils.forceDelete(next);
            } catch (IOException e){
                System.out.println("删除文件：" + next.getAbsolutePath() + "异常。错误消息：" + e.getMessage());
            }
        }
    }
}
