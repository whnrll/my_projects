package org.example.dataaccess.entity.user;

import javax.persistence.*;

import org.example.dataaccess.entity.base.BaseEntity;
import org.example.dataaccess.entity.vo.AuthorityMenuOperationVo;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import lombok.Data;

/**
 * 
 * t_authority_role_operation
 *
 */
@Entity
@Table(name= "t_authority_role_operation")
@Data
@NamedEntityGraph(name = "AuthorityRoleOperation.fullMenuOperation", attributeNodes = {@NamedAttributeNode("authorityMenuOperationVo")})
public class AuthorityRoleOperation extends BaseEntity {

    /**
     * 自定义角色id
     */
    private Long roleId;

    /**
     * 自定义操作id
     */
    private Long operationId;



    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "operationId", referencedColumnName="id", insertable = false, updatable = false)
    private AuthorityMenuOperationVo authorityMenuOperationVo;

    /**
     */
    private static final long serialVersionUID = 1L;

}