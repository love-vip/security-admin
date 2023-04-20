package com.vip.admin.oauth2.resource.autoconfigure;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.vip.admin.oauth2.resource.autoconfigure.IgnoreUrlsConfiguration.IgnoreUrlsConfig;

/**
 * @author echo
 * @title: IgnoreUrlsConfig
 * @date 2022/8/23 20:50
 */
@Configuration
@ConditionalOnProperty(prefix = "secure.ignore", name = "urls", matchIfMissing = true)
@EnableConfigurationProperties(IgnoreUrlsConfig.class)
public class IgnoreUrlsConfiguration {

    @Data
    @ConfigurationProperties(prefix = "secure.ignore")
    public static class IgnoreUrlsConfig {
        private String[] urls  = new String[] {"0"};
    }

}
