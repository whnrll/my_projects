package org.example.dataaccess.entity.user;

import javax.persistence.*;

import org.example.dataaccess.entity.base.BaseEntity;
import org.example.dataaccess.entity.vo.InstitutionVo;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import io.swagger.annotations.Api;
import lombok.Data;

/**
 * 
 * t_authority_role
 *
 */
@Api(value = "HATEOAS接口-角色管理(AuthorityRole)")
@Entity
@Table(name="t_authority_role")
@Data
public class AuthorityRole extends BaseEntity {

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


    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "institutionId", referencedColumnName="id", insertable = false, updatable = false)
    private InstitutionVo institutionVo;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDec;

    /**
     * 1：机构下的管理员角色，0: 普通角色
     */
    private Integer isAdmin;



}