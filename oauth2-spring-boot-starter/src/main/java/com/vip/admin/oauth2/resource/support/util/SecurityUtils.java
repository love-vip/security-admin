package com.vip.admin.oauth2.resource.support.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;

import java.util.Collections;
import java.util.Map;

/**
 * @author echo
 * @title: SecurityUtils
 * @date 2023/3/15 14:45
 */
@UtilityClass
public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public Oauth2Principal getUser(Authentication authentication) {
        if (authentication instanceof AnonymousAuthenticationToken) {
            return new Oauth2Principal(0L, "admin", Collections.emptyMap());
        }
        if (authentication instanceof BearerTokenAuthentication) {
            BearerTokenAuthentication tokenAuthentication = (BearerTokenAuthentication)authentication;
            AuthenticatedPrincipal principal = (AuthenticatedPrincipal)authentication.getPrincipal();
            Map<String, Object> attributes = tokenAuthentication.getTokenAttributes();
            JSONObject userInfo = (JSONObject)attributes.get("user_info");
            long id = userInfo.getAsNumber("id").longValue();
            return new Oauth2Principal(id, principal.getName(), userInfo);
        }
        return null;
    }

    /**
     * 获取用户
     */
    public Oauth2Principal getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return new Oauth2Principal(0L, "admin", Collections.emptyMap());
        }
        return getUser(authentication);
    }

    @RequiredArgsConstructor
    public static class Oauth2Principal {

        @Getter
        private final Long id;

        @Getter
        private final String username;

        @Getter
        private final Map<String, Object> userInfo;

    }

}