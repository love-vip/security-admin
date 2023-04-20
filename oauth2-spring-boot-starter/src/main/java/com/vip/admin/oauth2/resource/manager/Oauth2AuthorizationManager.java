package com.vip.admin.oauth2.resource.manager;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.function.Supplier;

public class Oauth2AuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        AuthorizationManager.super.verify(authentication, context);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        String uri = context.getRequest().getRequestURI();
        AntPathMatcher matcher = new AntPathMatcher();
        // 当前用户的权限信息 比如资源路径
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
        boolean granted = authentication.get() != null
                && authentication.get().isAuthenticated()
                && authorities.stream().map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> matcher.match(authority.replaceFirst("SCOPE_", ""), uri));
        return new AuthorizationDecision(granted);
    }

}
