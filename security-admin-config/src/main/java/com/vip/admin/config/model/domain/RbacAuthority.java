package com.vip.admin.config.model.domain;

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
@Table(name = "rbac_authority")
@Alias(value = "rbacAuthority")
@EqualsAndHashCode(callSuper = true)
public class RbacAuthority extends BaseEntity {

    /**
     * 所属系统
     */
    @TableField(value = "system")
    private String system;

    /**
     * 分组
     */
    private String group;

    /**
     * http方式
     */
    private String method;

    /**
     * 资源简称
     */
    @TableField(value = "authority_abbr")
    private String authorityAbbr;

    /**
     * 资源名称
     */
    @TableField(value = "authority_name")
    private String authorityName;

    /**
     * 资源路径
     */
    @TableField(value = "authority_url")
    private String authorityUrl;
}
