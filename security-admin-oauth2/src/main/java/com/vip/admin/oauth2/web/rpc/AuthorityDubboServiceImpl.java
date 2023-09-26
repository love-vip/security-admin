package com.vip.admin.oauth2.web.rpc;

import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.api.AuthorityDubboService;
import com.vip.admin.oauth2.request.AuthorityRequest;
import com.vip.admin.oauth2.service.RbacAuthorityService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author echo
 * @version 1.0
 * @date 2023/5/10 04:43
 */
@DubboService
@RequiredArgsConstructor
public class AuthorityDubboServiceImpl implements AuthorityDubboService {

    private final RbacAuthorityService authorityService;
    @Override
    public Wrapper<Void> sync(List<AuthorityRequest> request) {
        authorityService.notifyRefreshAuthority(request);
        return WrapMapper.ok();
    }
}
