package com.vip.admin.oauth2.web.rpc;

import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.api.AuthorizationDubboService;
import com.vip.admin.oauth2.request.AccessTokenBatchRequest;
import com.vip.admin.oauth2.request.AccessTokenRequest;
import com.vip.admin.oauth2.service.RbacAuthorityService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author echo
 * @version 1.0
 * @date 2023/5/1 17:29
 */
@RequiredArgsConstructor
@DubboService(version = "1.0.0", group = "admin-oauth2", timeout = 5000)
public class AuthorizationDubboServiceImpl implements AuthorizationDubboService {

    private final RbacAuthorityService authorityService;
    private final OAuth2AuthorizationService authorizationService;
    @Override
    public Wrapper<Void> expire(AccessTokenRequest request) {
        OAuth2Authorization authorization = authorizationService.findByToken(request.getAccessToken(), OAuth2TokenType.ACCESS_TOKEN);
        if(authorization != null && ! authorization.getAccessToken().isExpired()){
            authorizationService.remove(authorization);
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<Void> expire(AccessTokenBatchRequest request) {
        Stream.of(request.getAccessTokens()).forEach(accessToken -> expire(new AccessTokenRequest(accessToken)));
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<Void> renew(AccessTokenRequest request) {
        OAuth2Authorization authorization = authorizationService.findByToken(request.getAccessToken(), OAuth2TokenType.ACCESS_TOKEN);
        if(authorization != null && ! authorization.getAccessToken().isExpired()){
            String principalName = authorization.getPrincipalName();
            //查询用户当前可访问资源集合
            List<String> urls = authorityService.authorities(principalName);
            // 获取用户可访问资源
            Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(urls.toArray(new String[]{}));
            String scope = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
            OAuth2Authorization.Builder builder = OAuth2Authorization.from(authorization);
            OAuth2AccessToken accessToken = authorization.getAccessToken().getToken();
            builder.token(accessToken, (metadata) -> {
                Map<String, Object> claims = (Map)metadata.get(OAuth2Authorization.Token.CLAIMS_METADATA_NAME);
                Map<String, Object> newClaims = claims.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, replacement) -> replacement, TreeMap::new));
                newClaims.put(OAuth2ParameterNames.SCOPE, scope);
                metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, newClaims);
            });
            authorizationService.save(builder.build());
        }
        return WrapMapper.ok();
    }
}
