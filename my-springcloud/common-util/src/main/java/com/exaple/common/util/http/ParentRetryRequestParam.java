package com.exaple.common.util.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentRetryRequestParam {
    private int currentCount;

    private int retryCount;
}
