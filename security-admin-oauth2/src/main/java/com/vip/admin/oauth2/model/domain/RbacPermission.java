package com.vip.admin.oauth2.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.vip.admin.commons.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Table;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/3 17:54
 */
@Data
@Table(name = "rbac_permission")
@Alias(value = "RbacPermission")
@EqualsAndHashCode(callSuper = true)
public class RbacPermission extends BaseEntity {

    /**
     * 父id
     */
    private Long pid;

    /**
     * 所属系统
     */
    @TableField(value = "system")
    private String system;

    /**
     * 权限路由
     */
    @TableField(value = "perm_route")
    private String permRoute;

    /**
     * 权限名称
     */
    @TableField(value = "perm_name")
    private String permName;

    /**
     * 权限图标
     */
    @TableField(value = "perm_icon")
    private String permIcon;

    /**
     * 权限类型
     */
    @TableField(value = "perm_type")
    private String permType;
}
