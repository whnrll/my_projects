package org.example.dataaccess.entity.user;

import javax.persistence.Entity;
import javax.persistence.Table;


import io.swagger.annotations.Api;
import lombok.Data;
import org.example.dataaccess.entity.base.BaseEntity;

/**
 * 
 * t_authority_menu_operation
 *
 */
@Api(value = "HATEOAS接口-菜单操作管理(AuthorityMenuOperation)")
@Entity
@Table(name="t_authority_menu_operation")
@Data
public class AuthorityMenuOperation extends BaseEntity {

    /**
     * 自定义菜单id
     */
    private Long menuId;

    /**
     * 操作名称
     */
    private String operationName;

    /**
     * 操作URI地址
     */
    private String operationUri;

    /**
     * 操作图标
     */
    private String operationIco;

    /**
     * 状态（0无效1有效）
     */
    private Integer status;

    /**
     * 权限菜单url
     */
    private String operationResouceUrl;

    /**
     */
    private static final long serialVersionUID = 1L;
}