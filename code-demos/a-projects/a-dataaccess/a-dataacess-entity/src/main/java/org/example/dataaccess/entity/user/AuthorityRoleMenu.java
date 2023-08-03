package org.example.dataaccess.entity.user;

import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Data;
import org.example.dataaccess.entity.base.BaseEntity;

/**
 * 
 * t_authority_role_menu
 *
 */
@Entity
@Table(name= "t_authority_role_menu")
@Data
public class AuthorityRoleMenu extends BaseEntity {

    /**
     * 自定义角色id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     */
    private static final long serialVersionUID = 1L;
}