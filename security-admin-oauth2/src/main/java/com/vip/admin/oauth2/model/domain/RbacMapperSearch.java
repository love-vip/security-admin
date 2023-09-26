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
@TableName("rbac_mapper_search")
@Alias(value = "RbacMapperSearch")
@EqualsAndHashCode(callSuper = true)
public class RbacMapperSearch extends BaseEntity {

    /**
     * sql映射id
     */
    @TableField(value = "mapper_id")
    private String mapperId;

    /**
     * 查询表单(真实值)
     */
    @TableField(value = "actual_box")
    private String actualBox;

    /**
     * 查询表单(显示值)
     */
    @TableField(value = "display_box")
    private String displayBox;

    /**
     * 是否选中(0:否｜1:是)
     */
    @TableField(value = "checked")
    private boolean checked;

    /**
     * 是否包含合并组件(0:否｜1:是)
     */
    @TableField(value = "multi")
    private boolean multi;

    /**
     * 组件值
     */
    @TableField(value = "mapping")
    private String mapping;
}
