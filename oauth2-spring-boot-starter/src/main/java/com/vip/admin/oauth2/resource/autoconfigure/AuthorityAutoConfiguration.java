package com.vip.admin.oauth2.resource.autoconfigure;

import com.vip.admin.oauth2.resource.support.authority.registry.AuthorityRegistry;
import com.vip.admin.oauth2.resource.support.authority.registry.SecurityRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/9 11:14
 */
@Configuration(proxyBeanMethods = false)
public class AuthorityAutoConfiguration {

    @Bean
    public AuthorityRegistry authorityRegistry() {
        return new AuthorityRegistry();
    }

    @Bean
    public SecurityRegistry securityRegistry() {
        return new SecurityRegistry();
    }
}
