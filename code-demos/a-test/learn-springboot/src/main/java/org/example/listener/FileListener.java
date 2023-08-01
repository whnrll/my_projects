package org.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;

/**
 * 描述：文件侦听器
 *
 * @author xutao
 * @Date 2023-02-28 00:41:04
 */
@Slf4j
@Component
public class FileListener extends FileAlterationListenerAdaptor {
    @Override
    public void onFileChange(File file) {
        log.info("file changed, file name: [{}], lastModified: [{}]", file.getName(), new Date(file.lastModified()));
    }
}
