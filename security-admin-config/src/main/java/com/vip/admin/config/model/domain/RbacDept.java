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
@Table(name = "rbac_dept")
@Alias(value = "rbacDept")
@EqualsAndHashCode(callSuper = true)
public class RbacDept extends BaseEntity {

    /**
     * 父id
     */
    private Long pid;

    /**
     * 部门分级
     */
    private Integer level;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name")
    private String deptName;
}
