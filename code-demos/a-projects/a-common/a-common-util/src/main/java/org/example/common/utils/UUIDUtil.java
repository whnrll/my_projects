package org.example.common.utils;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：uuidutil
 *
 * @author xutao
 * @Date 2023-02-27 22:57:35
 */
@Slf4j
public class UUIDUtil {
    /**
     * 描述：uuid生成
     *
     * @return {@link String }
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
