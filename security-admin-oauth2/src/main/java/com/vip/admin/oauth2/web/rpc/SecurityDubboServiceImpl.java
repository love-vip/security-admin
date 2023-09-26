package com.vip.admin.oauth2.web.rpc;

import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.api.SecurityDubboService;
import com.vip.admin.oauth2.request.SecurityConditionRequest;
import com.vip.admin.oauth2.request.SecurityDataScopeRequest;
import com.vip.admin.oauth2.service.RbacSecurityService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author echo
 * @version 1.0
 * @date 2023/9/25 19:02
 */
@DubboService
@RequiredArgsConstructor
public class SecurityDubboServiceImpl implements SecurityDubboService {

    private final RbacSecurityService securityService;

    @Override
    public Wrapper<String> attain(SecurityConditionRequest request) {
        return WrapMapper.success(securityService.attain(request));
    }

    @Override
    public Wrapper<Void> sync(List<SecurityDataScopeRequest> request) {
        securityService.sync(request);
        return WrapMapper.ok();
    }
}
