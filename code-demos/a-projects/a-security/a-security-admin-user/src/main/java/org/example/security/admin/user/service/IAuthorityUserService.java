package org.example.security.admin.user.service;


import org.example.common.exception.ComponentException;
import org.example.dataaccess.entity.user.AuthorityUser;

public interface IAuthorityUserService {
    /**
     * 新增用户接口
     * @param user
     * @param loginUser
     * @throws ComponentException
     */
    void addUser(AuthorityUser user, AuthorityUser loginUser) throws ComponentException;

    /**
     * 用户登陆接口处理逻辑
     * @param username
     * @param password
     * @param clientIp
     * @throws ComponentException
     */
    AuthorityUser userLogin(String userAccount, String password, String clientIp) throws ComponentException;

    /**
     * 重置用户密码接口, 新密码为"账号+123"
     * @param newPwd
     * @param userAccount
     * @param loginUser
     * @throws ComponentException
     */
    void resetPwd(String userAccount, AuthorityUser loginUser) throws ComponentException;
}
