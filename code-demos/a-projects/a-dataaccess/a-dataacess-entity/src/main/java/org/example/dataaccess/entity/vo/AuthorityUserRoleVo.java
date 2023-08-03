package org.example.dataaccess.entity.vo;

import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Data;
import org.example.dataaccess.entity.base.BaseEntity;

/**
 * 
 * t_authority_user_role
 *
 */
@Entity
@Table(name="t_authority_user_role")
@Data
public class AuthorityUserRoleVo extends BaseEntity {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色机构类型
     */
    private String institutionTypeId;

    /**
     * 角色对应机构类型下的机构id
     */
    private Long institutionId;

    /**
     * 角色对应的机构名称
     */
    private String institutionName;

    /**
     */
    private static final long serialVersionUID = 1L;

}