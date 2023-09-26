package com.vip.admin.oauth2.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vip.admin.commons.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * @author echo
 * @version 1.0
 * @date 2023/9/25 19:07
 */
@Data
@TableName("rbac_mapping_user_condition")
@Alias(value = "rbacMappingUserCondition")
@EqualsAndHashCode(callSuper = true)
public class RbacMappingUserCondition extends BaseEntity {

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * sql映射id
     */
    @TableField(value = "mapper_id")
    private String mapperId;

    /**
     * 查询条件
     */
    @TableField(value = "condition_")
    private String condition;
}
