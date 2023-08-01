package com.muke.entity;

import java.io.Serializable;

/**
 * 用户排队抢单信息实体
 */
public class SkillEntity implements Serializable {
    private Long productId;
    private String userId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
