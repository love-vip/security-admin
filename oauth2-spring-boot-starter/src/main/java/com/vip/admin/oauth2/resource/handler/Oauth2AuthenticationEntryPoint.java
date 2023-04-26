package com.vip.admin.oauth2.resource.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Oauth2AuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        /*response.setStatus(HttpStatus.UNAUTHORIZED.value());*/

        // oauth2 认证失败导致的，还有一种可能是非oauth2认证失败导致的，比如没有传递token，但是访问受权限保护的方法
        if (exception instanceof OAuth2AuthenticationException) {
            OAuth2AuthenticationException oAuth2AuthenticationException = (OAuth2AuthenticationException) exception;
            OAuth2Error error = oAuth2AuthenticationException.getError();
            log.info("认证失败,异常类型:[{}],异常:[{}]", exception.getClass().getName(), error);
        }
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        /* 解决跨域问题 */
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.getWriter().write("{\"code\":\"401\",\"message\":\"Unauthorized~认证失败\"}");
    }
}
