package org.example.common.zip;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.sevenzipjbinding.*;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

/**
 * 描述：
 *
 * @author : xutao
 * @date : 2021-12-13 23:39
 */
public class Un7zUtil {
    private static Logger LOG = LoggerFactory.getLogger(Un7zUtil.class);

    /**
     *
     * @Description (解压7z)
     * @param file7zPath(7z文件路径)
     * @param outPutPath(解压路径)
     * @param passWord(文件密码.没有可随便写,或空)
     * @return
     * @throws Exception
     */
    public static boolean un7z(String file7zPath, final String outPutPath, String passWord) throws Exception {
        IInArchive archive = null;
        RandomAccessFile randomAccessFile = null;;
        try {
            randomAccessFile = new RandomAccessFile(file7zPath, "r");
            archive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile), passWord);
            int numberOfItems = archive.getNumberOfItems();
            ISimpleInArchive simpleInArchive = archive.getSimpleInterface();
            for (final ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
                final int[] hash = new int[] { 0 };
                if (!item.isFolder()) {
                    ExtractOperationResult result;
                    final long[] sizeArray = new long[1];
                    result = item.extractSlow(new ISequentialOutStream() {
                        @Override
                        public int write(byte[] data) throws SevenZipException {
                            try {
                                String str = item.getPath();
                                str = str.substring(0, str.lastIndexOf(File.separator));
                                File file = new File(outPutPath + File.separator + str + File.separator);
                                if (!file.exists()) {
                                    file.mkdirs();
                                }
                                File file1 = new File(outPutPath + File.separator + item.getPath());
                                FileUtils.writeByteArrayToFile(file1,data, true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            hash[0] ^= Arrays.hashCode(data); // Consume data
                            sizeArray[0] += data.length;
                            return data.length; // Return amount of consumed
                        }
                    }, passWord);

                    if (result == ExtractOperationResult.OK) {
                        LOG.error("解压成功...." + String.format("%9X | %10s | %s", hash[0], sizeArray[0], item.getPath()));
                    } else {
                        LOG.error("解压失败：密码错误或者其他错误...." + result);
                    }
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            archive.close();
            randomAccessFile.close();
        }
    }

    /**
     * 递归删除文件夹
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // 删除文件
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File[] files = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFile(files[i]); // 把每个文件用这个方法进行迭代
                }
                file.delete(); // 删除文件夹
            }
        }
    }
}
