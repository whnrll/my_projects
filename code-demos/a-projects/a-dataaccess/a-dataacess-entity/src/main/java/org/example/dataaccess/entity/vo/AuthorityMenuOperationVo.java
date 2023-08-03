package org.example.dataaccess.entity.vo;

import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Data;
import org.example.dataaccess.entity.base.BaseEntity;

/**
 * 
 * t_authority_menu_operation
 *
 */
@Entity
@Table(name="t_authority_menu_operation")
@Data
public class AuthorityMenuOperationVo extends BaseEntity {

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
     */
    private static final long serialVersionUID = 1L;
}