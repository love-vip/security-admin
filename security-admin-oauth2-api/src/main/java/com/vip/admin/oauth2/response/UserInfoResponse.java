package com.vip.admin.oauth2.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/27 12:35
 */
@Data
public class UserInfoResponse implements Serializable {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 工号
     */
    private String jobNum;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 在职状态（0:离职 1:在职）
     */
    private boolean enabled;

    /**
     * 是否还未锁定(0:否 1是)
     */
    private boolean accountNonLocked;

    /**
     * 是否初始用户
     */
    private boolean initial;

    /**
     * 是否已绑定谷歌校验器
     */
    private boolean bind;
}
