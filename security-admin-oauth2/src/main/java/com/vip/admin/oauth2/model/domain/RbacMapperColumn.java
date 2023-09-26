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
 * @date 2023/7/18 16:49
 */
@Data
@TableName("rbac_mapper_column")
@Alias(value = "RbacMapperColumn")
@EqualsAndHashCode(callSuper = true)
public class RbacMapperColumn extends BaseEntity {

    /**
     * sql映射id
     */
    @TableField(value = "mapper_id")
    private String mapperId;

    /**
     * 列(真实值)
     */
    @TableField(value = "actual_column")
    private String actualColumn;

    /**
     * 列(显示值)
     */
    @TableField(value = "display_column")
    private String displayColumn;

    /**
     * 是否选中(0:否｜1:是)
     */
    @TableField(value = "checked")
    private boolean checked;

    /**
     * 是否包含多列(0:否｜1:是)
     */
    @TableField(value = "multi")
    private boolean multi;

    /**
     * 列(显示值)
     */
    @TableField(value = "mapping")
    private String mapping;

    /**
     * 是否遮罩脱敏(0:否｜1:是)
     */
    @TableField(value = "mask")
    private boolean mask;

    /**
     * 数据类型（String, Number, Datetime, Boolean）
     */
    @TableField(value = "type")
    private String type;

    /**
     * 是否支持行过滤(0:否｜1:是)
     */
    @TableField(value = "filter")
    private boolean filter;
}
