package org.example.dataaccess.entity.user;

import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Data;
import org.example.dataaccess.entity.base.BaseEntity;

/**
 * 
 * t_authority_no_limit_urit
 *
 */
@Entity
@Table(name="t_authority_no_limit_uri")
@Data
public class AuthorityNoLimitUri extends BaseEntity {

    /**
     * 不限制的uri
     */
    private String noLimitUri;

    /**
     */
    private static final long serialVersionUID = 1L;

}