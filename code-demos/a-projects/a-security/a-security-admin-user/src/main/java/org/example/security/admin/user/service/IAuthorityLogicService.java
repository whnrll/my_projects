package org.example.security.admin.user.service;

import org.example.common.exception.BusinessException;
import org.example.common.exception.ComponentException;
import org.example.dataaccess.entity.user.AuthorityUser;
import org.example.dataaccess.entity.user.AuthorityUserRole;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>Description: </p>
 * @date 
 * @author 
 * @version 1.0
 * <p>Copyright:Copyright(c)2020</p>
 */
public interface IAuthorityLogicService {
    /**
     * 保存角色指定的权限信息(支持批量方式, 多个角色可以同时保存)
     * @param roleAuth
     * @param roleIds
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    void saveProvileges(String roleAuth, String... roleIds) throws BusinessException;

    /**
     * 分配用户与角色的关联关系接口
     * @param authorityUserRole
     * @param authorityUser
     * @throws ComponentException
     */
    void userAssignRoles(AuthorityUserRole authorityUserRole, AuthorityUser authorityUser) throws ComponentException;
}
