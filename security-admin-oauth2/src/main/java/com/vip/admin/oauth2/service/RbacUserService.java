package com.vip.admin.oauth2.service;

import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.commons.core.support.IService;
import com.vip.admin.oauth2.model.domain.RbacUser;
import com.vip.admin.oauth2.model.dto.Oauth2PasswordDto;

import java.util.Map;
import java.util.Optional;

/**
 * @author echo
 * @title: RbacUserService
 * @date 2023/3/16 15:47
 */
public interface RbacUserService extends IService<RbacUser> {

    /**
     * 登录 & 谷歌二次校验
     * @param args 参数
     * @return token信息
     */
    Wrapper<?> sign(Map<String, Object> args);

    /**
     * <p>修改密码</p>
     * @param passwordDto 密码
     */
    void updatePassword(Oauth2PasswordDto passwordDto);

    /**
     * <p>修改密码</p>
     * @param username 用户名
     * @param password 密码
     */
    void updatePassword(String username, String password);

    /**
     * <p>根据唯一用户名获取用户信息</p>
     * @param username 用户名
     * @return 用户
     */
    Optional<RbacUser> getByUsername(String username);

    /**
     * <p>根据唯一手机号获取用户信息</p>
     * @param mobile 手机号
     * @return 用户
     */
    Optional<RbacUser> getByMobile(String mobile);

    /**
     * <p>锁定账户</p>
     * @param username 用户名
     */
    void locked(String username);

    /**
     * <p>解锁账户</p>
     * @param username 用户名
     */
    void unlock(String username);

    /**
     * <p>保存密钥</p>
     * @param username 用户名
     * @return 密钥
     */
    String fillSecret(String username);

}
