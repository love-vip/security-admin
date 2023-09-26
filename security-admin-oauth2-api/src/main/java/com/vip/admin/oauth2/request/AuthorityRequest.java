package com.vip.admin.oauth2.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author echo
 * @version 1.0
 * @date 2023/5/10 04:09
 */
@Data
public class AuthorityRequest implements Serializable {
    private String menu;

    private String method;

    private String interfaze;

    private String url;

    private String button;

    private String route;

    private String system;

    private String parentRoute;

    private String parentMenu;

    /* 二级菜单路由 */
    private String secondRoute;

    /* 二级菜单名称 */
    private String secondMenu;

    /** 挂载点 */
    private String mount;

    /**
     * sql映射id
     */
    private String mapperId;

    /**
     * 是否数据权限过滤
     */
    private boolean filter = Boolean.FALSE;

    /**
     * 按钮授权时是否隐藏(0:否|1:是)
     */
    private boolean hidden = Boolean.FALSE;

    private Integer sort;

}
