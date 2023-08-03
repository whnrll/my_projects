package org.example.dataaccess.entity.vo;

import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Data;
import org.example.dataaccess.entity.base.BaseEntity;

/**
 * 
 * t_authority_role
 *
 */
@Entity
@Table(name="t_authority_role")
@Data
public class AuthorityRoleVo extends BaseEntity {

    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * 机构类型id
     */
    private String institutionTypeId;

    /**
     * 对应机构类型下的机构id
     */
    private Long institutionId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDec;




}