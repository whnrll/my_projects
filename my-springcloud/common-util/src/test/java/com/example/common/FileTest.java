package com.example.common;

import java.io.File;

import com.exaple.common.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * 描述：文件测试
 *
 * @author xutao
 * @Date 2022-11-25 21:38:33
 */
public class FileTest {
    private static final String REGEX = "【.*】";

    private static final Logger LOGGER = LoggerFactory.getLogger(FileTest.class);

    @Test
    public void modifyFileName() {
        String srcDir = "D:\\下载\\15、Java高性能、高并发、高可用的分布式架构系统开发课程 2022 完结";
        FileUtil.modifyFileName(srcDir, "【.*】", "");
    }

    @Test
    public void addSuffix() {
        String srcDir = "G:\\下载2\\Webpack全面实战企业级项目搭建";
        String suffix = ".mp4";
        FileUtil.addSuffix(srcDir, suffix);
    }

    @Test
    public void replace() {
        String removeStr = "第01讲：什么是微服务架构【瑞客论坛 www.ruike1.com】";
        String regex = "【.*】";
        String replace = removeStr.replaceFirst(regex, "");
        LOGGER.info("replace={}", replace);
    }

    @Test
    public void filePath() throws Exception {
        String path = "";
        File file = FileUtils.getFile(path);
        LOGGER.info("canonicalPath={}", file.getCanonicalPath());
        LOGGER.info("absolutePath={}", file.getAbsolutePath());
        LOGGER.info("name={}", file.getName());
    }

    @Test
    public void deleteFiles() {
        String srcDir = "D:\\学习资料\\视频\\聚焦Java性能优化 打造亿级流量秒杀系统(完结)";
        FileUtil.deleteFiles(srcDir, "下载说明.txt");
    }

    @Test
    public void deleteFilesWithSuffix() throws Exception {
        String[] deleteSuffix = new String[]{".url"};
        String srcDir = "D:\\学习资料\\视频\\聚焦Java性能优化 打造亿级流量秒杀系统(完结)";
        FileUtil.deleteFilesWithSuffix(srcDir, deleteSuffix);
    }

    @Test
    public void deleteFilesWithPrefix() throws Exception {
        String[] deletePrefix = new String[]{"一切与IT有关的教程", "下载说明", "全网最大的免费IT学习资源社区", "Dmz社区 - www.dmzshequ.com"};
        String srcDir = "E:\\BaiduNetdiskDownload\\Redis核心技术与实战";
        FileUtil.deleteFilesWithPrefix(srcDir, deletePrefix);
    }

    @Test
    public void moveFile() throws Exception {
        String[] moveSuffix = new String[]{"mp4", "mkv", "m4a", "pdf", "ev4", "avi", "zip", "txt", "ev4", "ev4a"};
        String srcDir = "G:\\下载\\Spring+Tomcat源码级分析\\4.视频";
        FileUtil.moveFile(srcDir, srcDir, moveSuffix);
    }

    @Test
    public void fileSuffix() {
        // 修改压缩包文件后缀
        String suffix = ".cc";
        // 压缩包根目录
        String zipParentDir = "";
        FileUtil.modifyFileNameSuffix(zipParentDir, suffix);

    }
}
