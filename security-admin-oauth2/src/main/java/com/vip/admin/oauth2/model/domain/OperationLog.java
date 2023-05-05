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
 * @date 2023/4/21 17:20
 */
@Data
@Table(name = "operation_log")
@Alias(value = "operationLog")
@EqualsAndHashCode(callSuper = true)
public class OperationLog extends BaseEntity {

    /**
     * 操作人
     */
    @TableField(value = "operator")
    private String operator;

    /**
     * 所属系统
     */
    @TableField(value = "system")
    private String system;

    /**
     * 页面
     */
    @TableField(value = "page")
    private String page;

    /**
     * 请求url
     */
    @TableField(value = "request_url")
    private String requestUrl;

    /**
     * 请求参数
     */
    @TableField(value = "request_param")
    private String requestParam;

    /**
     * 执行时长
     */
    @TableField(value = "execute_duration")
    private Integer executeDuration;

    /**
     * IP地址
     */
    @TableField(value = "ip")
    private String ip;
}
