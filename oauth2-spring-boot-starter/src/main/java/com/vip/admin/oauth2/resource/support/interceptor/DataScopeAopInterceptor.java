package com.vip.admin.oauth2.resource.support.interceptor;

import com.vip.admin.oauth2.api.SecurityDubboService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author echo
 * @version 1.0
 * @date 2023/9/25 18:19
 */
@Slf4j
@RequiredArgsConstructor
public class DataScopeAopInterceptor {

    private final SecurityDubboService securityDubboService;
}
