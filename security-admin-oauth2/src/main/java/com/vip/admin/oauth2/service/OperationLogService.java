package com.vip.admin.oauth2.service;


import com.vip.admin.oauth2.request.OperationLogRequest;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/21 17:27
 */
public interface OperationLogService {

    /**
     * 保存日志
     * @param request 日志对象
     */
    void save(OperationLogRequest request);
}
