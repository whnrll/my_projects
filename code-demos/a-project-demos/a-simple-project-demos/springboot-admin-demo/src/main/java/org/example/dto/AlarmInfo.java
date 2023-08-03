package org.example.dto;

import lombok.Data;

/**
 * 描述：报警信息
 *
 * @author xutao
 * @Date 2023-02-26 19:42:45
 */
@Data
public class AlarmInfo {
    private String from;

    private String to;

    private String info;
}
