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
 * @date 2023/4/12 10:09
 */
@Data
@Table(name = "rbac_system")
@Alias(value = "rbacSystem")
@EqualsAndHashCode(callSuper = true)
public class RbacSystem extends BaseEntity {

    /**
     * 系统
     */
    private String system;

    /**
     * 系统名称
     */
    @TableField(value = "system_name")
    private String systemName;
}
