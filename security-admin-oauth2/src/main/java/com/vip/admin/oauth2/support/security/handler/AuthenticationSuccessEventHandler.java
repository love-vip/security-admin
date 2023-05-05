package com.vip.admin.oauth2.support.security.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.vip.admin.oauth2.service.RbacUserService;
import com.vip.admin.oauth2.support.security.core.constant.SecurityConstants;
import com.vip.admin.oauth2.support.security.util.OAuth2EndpointUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

/**
 * @author echo
 * @title: AuthenticationSuccessEventHandler
 * @date 2023/3/15 11:57
 */
@Slf4j
public class AuthenticationSuccessEventHandler implements AuthenticationSuccessHandler {

    private final HttpMessageConverter<OAuth2AccessTokenResponse> accessTokenHttpResponseConverter = new OAuth2AccessTokenResponseHttpMessageConverter();

    /**
     * Called when a user has been successfully authenticated.
     * @param request the request which caused the successful authentication
     * @param response the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     * the authentication process.
     */
    @SneakyThrows
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        log.info("用户：{} 登录成功", authentication.getPrincipal());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //用户登录成功设置最新访问令牌
        setAccessToken(authentication);

        // 输出token
        sendAccessTokenResponse(response, authentication);
    }

    private void sendAccessTokenResponse(HttpServletResponse response, Authentication authentication) throws IOException {

        OAuth2AccessTokenResponse accessTokenResponse = OAuth2EndpointUtils.sendAccessTokenResponse(authentication);

        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);

        // 无状态 注意删除 context 上下文的信息
        SecurityContextHolder.clearContext();

        this.accessTokenHttpResponseConverter.write(accessTokenResponse, MediaType.APPLICATION_JSON, httpResponse);
    }

    private void setAccessToken(Authentication authentication){
        RbacUserService userService = SpringUtil.getBean(RbacUserService.class);
        OAuth2AccessTokenResponse tokenResponse = OAuth2EndpointUtils.sendAccessTokenResponse(authentication);
        String accessToken = tokenResponse.getAccessToken().getTokenValue();
        Instant expireAt = tokenResponse.getAccessToken().getExpiresAt();
        Assert.notNull(expireAt, "accessToken过期时间不允许为空");
        LocalDateTime expireTime = LocalDateTime.ofInstant(expireAt, ZoneId.systemDefault());
        Map<String, Object> additional = tokenResponse.getAdditionalParameters();
        String username = additional.get("sub").toString();
        AuthorizationGrantType grantType = (AuthorizationGrantType)additional.get("grant_type");
        if (StrUtil.equals(SecurityConstants.GOOGLE.getValue(), grantType.getValue())) {
            userService.updateAccessToken(username, accessToken, expireTime);
        }
    }

}