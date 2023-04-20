package com.vip.admin.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>资源服务中心启动类</p>
 * @author echo
 * @title: ConfigProviderApplication
 * @date 2023/3/18 13:43
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.vip.admin.config.mapper")
@EnableFeignClients(basePackages = "com.vip.admin")
@OpenAPIDefinition(info = @Info(title = "资源服务 API", version = "2.0", description = "权限认证后台 Information"),
        servers = {@Server(url = "http://localhost:8002")}, security = @SecurityRequirement(name = "Bearer access_token"))
@SecurityScheme(name = "Bearer access_token", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER,
        description = "直接将有效的access_token填入下方，后续该access_token将作为Bearer access_token")
public class ConfigProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigProviderApplication.class, args);
    }

    /**
     * BCrypt密码编码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
