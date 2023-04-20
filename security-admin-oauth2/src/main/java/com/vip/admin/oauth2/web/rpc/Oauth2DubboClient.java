package com.vip.admin.oauth2.web.rpc;

import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.api.Oauth2DubboApi;
import com.vip.admin.oauth2.service.RbacAuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/6 11:17
 */
@Slf4j
@DubboService
@RequiredArgsConstructor
public class Oauth2DubboClient implements Oauth2DubboApi {

    private final RbacAuthorityService authorityService;
    @Override
    public Wrapper<?> notifyRefreshAuthority(String system) {
        return WrapMapper.ok();
    }
}
