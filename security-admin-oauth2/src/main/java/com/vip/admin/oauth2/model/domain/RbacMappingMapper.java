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
@TableName("rbac_mapping_mapper")
@Alias(value = "RbacMappingMapper")
@EqualsAndHashCode(callSuper = true)
public class RbacMappingMapper extends BaseEntity {

    public RbacMappingMapper() {
    }

    public RbacMappingMapper(String mapperId, String mapperIds, boolean aop) {
        this.mapperId = mapperId;
        this.mapperIds = mapperIds;
        this.aop = aop;
    }

    /**
     * sql映射id
     */
    @TableField(value = "mapper_id")
    private String mapperId;

    /**
     * sql映射id集合
     */
    @TableField(value = "mapper_ids")
    private String mapperIds;

    /**
     * 是否aop(0:否｜1:是)
     */
    @TableField(value = "aop")
    private boolean aop;

    /**
     * 是否驼峰(0:否｜1:是)
     */
    @TableField(value = "camel")
    private boolean camel;

}
