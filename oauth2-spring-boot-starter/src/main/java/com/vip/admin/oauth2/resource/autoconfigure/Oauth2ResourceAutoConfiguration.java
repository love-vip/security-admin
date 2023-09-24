package com.vip.admin.oauth2.resource.autoconfigure;

import com.vip.admin.oauth2.resource.handler.Oauth2AccessDeniedHandler;
import com.vip.admin.oauth2.resource.handler.Oauth2AuthenticationEntryPoint;
import com.vip.admin.oauth2.resource.manager.Oauth2AuthorizationManager;
import com.vip.admin.oauth2.resource.autoconfigure.IgnoreUrlsConfiguration.IgnoreUrlsConfig;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

/**
 * @author echo
 * @title: Oauth2ResourceAutoConfiguration
 * @date 2023/03/28 11:09
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Import({IgnoreUrlsConfiguration.class})
@ConditionalOnClass(OAuth2ResourceServerProperties.class)
@AutoConfigureAfter({ IgnoreUrlsConfiguration.class, OAuth2ResourceServerAutoConfiguration.class })
public class Oauth2ResourceAutoConfiguration {

    private final IgnoreUrlsConfig ignoreUrlsConfig;

    /**
     * @see org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider
     * @see org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector
     * @param httpSecurity Security注入点
     * @return 过滤器链
     * @throws Exception 异常
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain resources(HttpSecurity httpSecurity,  OpaqueTokenIntrospector introspector) throws Exception {
        // opaque处理
        httpSecurity.oauth2ResourceServer(customizer -> customizer.opaqueToken(opaque -> opaque.introspector(introspector)));
        // 自定义处理token请求头过期或签名错误的结果
        httpSecurity.oauth2ResourceServer(customizer -> customizer.authenticationEntryPoint(new Oauth2AuthenticationEntryPoint()));
        // 自定义处理token请求头鉴权失败的结果
        httpSecurity.oauth2ResourceServer(customizer -> customizer.accessDeniedHandler(new Oauth2AccessDeniedHandler()));
        //AJAX进行跨域请求时的预检,需要向另外一个域名的资源发送一个HTTP OPTIONS请求头,用以判断实际发送的请求是否安全
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers(HttpMethod.OPTIONS).permitAll());
        //白名单不拦截
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers(ignoreUrlsConfig.getUrls()).permitAll());
        /* 请求拦截处理 */
        httpSecurity.authorizeHttpRequests(customizer -> customizer.anyRequest().access(new Oauth2AuthorizationManager()));
        //跨域保护禁用
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        //跨域保护禁用
        httpSecurity.cors(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    /**
     * 从request请求中那个地方获取到token
     */
    @Bean
    public BearerTokenResolver bearerTokenResolver() {
        DefaultBearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();
        // 设置请求头的参数，即从这个请求头中获取到token
        bearerTokenResolver.setBearerTokenHeaderName(HttpHeaders.AUTHORIZATION);
        bearerTokenResolver.setAllowFormEncodedBodyParameter(false);
        // 是否可以从uri请求参数中获取token
        bearerTokenResolver.setAllowUriQueryParameter(false);
        return bearerTokenResolver;
    }

    @Bean
    @Primary
    @SneakyThrows
    public OpaqueTokenIntrospector introspector(OAuth2ResourceServerProperties properties) {
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, (chain, authType) -> true).build();
        SSLConnectionSocketFactory ssl = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        HttpClientConnectionManager connMgr = PoolingHttpClientConnectionManagerBuilder.create().setSSLSocketFactory(ssl).build();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connMgr).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplateBuilder()
                .basicAuthentication(properties.getOpaquetoken().getClientId(), properties.getOpaquetoken().getClientSecret())
                .requestFactory(() -> requestFactory).build();
        return new NimbusOpaqueTokenIntrospector(properties.getOpaquetoken().getIntrospectionUri(), restTemplate);
    }

}