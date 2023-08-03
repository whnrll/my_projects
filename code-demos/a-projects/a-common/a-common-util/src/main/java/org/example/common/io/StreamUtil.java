package org.example.common.io;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamUtil {
    public static void close(Closeable closeable) {
        try {
            if (Objects.nonNull(closeable)) {
                closeable.close();
            }
        } catch (IOException e) {
            log.error("close stream error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
