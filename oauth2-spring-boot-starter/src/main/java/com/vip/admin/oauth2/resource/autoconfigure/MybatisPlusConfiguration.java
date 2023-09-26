package com.vip.admin.oauth2.resource.autoconfigure;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.vip.admin.oauth2.api.SecurityDubboService;
import com.vip.admin.oauth2.resource.support.interceptor.DataScopeAopInterceptor;
import com.vip.admin.oauth2.resource.support.interceptor.DataScopeInterceptor;
import com.vip.admin.oauth2.resource.support.utils.SecurityUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author echo
 * @version 1.0
 * @date 2023/9/25 17:28
 */
@Configuration(proxyBeanMethods = false)
public class MybatisPlusConfiguration {

    @DubboReference
    private SecurityDubboService securityDubboService;

    @Bean
    public ConfigurationCustomizer customizer(){
        return configuration -> {
            MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
            interceptor.addInnerInterceptor(new DataScopeInterceptor(securityDubboService));
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
            configuration.addInterceptor(interceptor);
        };
    }

    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MybatisMetaObjectHandler();
    }

    public static class MybatisMetaObjectHandler implements MetaObjectHandler{

        @Override
        public void insertFill(MetaObject metaObject) {
            this.setFieldValByName("creator", SecurityUtils.getUser().getUsername(), metaObject);
            this.setFieldValByName("creatorId", SecurityUtils.getUser().getId(), metaObject);
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            this.setFieldValByName("lastOperator", SecurityUtils.getUser().getUsername(), metaObject);
            this.setFieldValByName("lastOperatorId", SecurityUtils.getUser().getId(), metaObject);
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }

    @Bean
    public DataScopeAopInterceptor interceptor(){
        return new DataScopeAopInterceptor(securityDubboService);
    }
}