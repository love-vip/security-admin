package com.vip.admin.oauth2.api;

import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.request.OperationLogRequest;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/22 22:12
 */
public interface OperationLogDubboService {

    /**
     * 保存操作日志
     * @param request 日志对象
     * @return BaseResponse
     */
    Wrapper<Void> save(OperationLogRequest request);
}
