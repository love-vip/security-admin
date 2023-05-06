package com.vip.admin.oauth2.service;

import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.commons.core.support.IService;
import com.vip.admin.oauth2.model.domain.RbacUser;
import com.vip.admin.oauth2.model.dto.Oauth2LogoutDto;
import com.vip.admin.oauth2.model.dto.Oauth2PasswordDto;

import java.time.LocalDateTime;
import java.util.List;
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
     * 登出（销毁token）
     * @param token 访问令牌
     * @return 成功或者失败
     */
    Wrapper<Void> logout(String token);

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
     * <p>用户登录成功设置最新访问令牌</p>
     * @param username 用户名
     * @param accessToken 用户令牌
     * @param expireTime 过期时间
     */
    void updateAccessToken(String username, String accessToken, LocalDateTime expireTime);

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
     * <p>根据主键id获取用户信息</p>
     * @param id 主键id
     * @return 用户
     */
    Optional<RbacUser> selectByPrimaryKey(Long id);

    /**
     * <p>根据主键id获取用户信息</p>
     * @param ids 主键ids
     * @return 用户
     */
    Optional<List<RbacUser>> selectByPrimaryKey(Long[] ids);

    /**
     * <p>锁定账户</p>
     * @param username 用户名
     * @param compositeKey 记录错误次数key
     */
    void locked(String username, String compositeKey);

    /**
     * <p>解锁账户</p>
     * @param username 用户名
     * @param compositeKey 记录错误次数key
     */
    void unlock(String username, String compositeKey);

    /**
     * <p>绑定谷歌校验</p>
     * @param username 用户名
     */
    void bind(String username);

    /**
     * <p>保存密钥</p>
     * @param username 用户名
     * @return 密钥
     */
    String fillSecret(String username);

}
