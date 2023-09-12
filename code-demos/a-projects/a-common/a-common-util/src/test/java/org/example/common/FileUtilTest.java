package org.example.common;

import lombok.extern.slf4j.Slf4j;
import org.example.common.io.FileUtil;
import org.junit.Test;

@Slf4j
public class FileUtilTest {
    @Test
    public void testModifyFileName() {
        String dir = "D:\\学习资料\\视频\\【大厂学苑】深度剖析微服务架构底层源码";
        String fileNameRegex = "  \\[dmz社区 www.dmzshequ.com\\]";
        FileUtil.modifyFileName(dir, fileNameRegex, "");
    }
}
