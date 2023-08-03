package org.example.dataaccess.entity.vo;

import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Data;
import org.example.dataaccess.entity.base.BaseEntity;

/**
 * <p>Description: </p>
 * @date 2020/1/10
 * @author 贺锟 
 * @version 1.0
 * <p>Copyright:Copyright(c)2019</p>
 */
@Entity
@Table(name="t_authority_menu")
@Data
public class ParentAuthorityMenuVo extends BaseEntity {

    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * 上级菜单的ID
     */
    private Long parentId;

    /**
     * 菜单的编号
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单路径
     */
    private String menuUri;

}
