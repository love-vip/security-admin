package com.vip.admin.oauth2.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/12 10:41
 */
@Data
public class OperationLogRequest implements Serializable {

    /**
     * 操作人
     */
    private String operator;

    /**
     * 来源系统
     */
    private String system;

    /**
     * 页面
     */
    private String page;

    /**
     * 请求url
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 执行时长
     */
    private Long executeDuration;

    /**
     * IP地址
     */
    private String ip;
}
