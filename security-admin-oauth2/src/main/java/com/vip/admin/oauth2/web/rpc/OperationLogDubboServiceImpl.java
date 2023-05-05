package com.vip.admin.oauth2.web.rpc;

import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.api.OperationLogDubboService;
import com.vip.admin.oauth2.request.OperationLogRequest;
import com.vip.admin.oauth2.service.OperationLogService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/23 14:45
 */
@RequiredArgsConstructor
@DubboService(version = "1.0.0", group = "admin-oauth2", timeout = 5000)
public class OperationLogDubboServiceImpl implements OperationLogDubboService {

    private final OperationLogService operationLogService;

    @Override
    public Wrapper<Void> save(OperationLogRequest request) {
        operationLogService.save(request);
        return WrapMapper.ok();
    }
}
