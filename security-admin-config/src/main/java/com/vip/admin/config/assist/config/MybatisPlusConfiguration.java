package com.vip.admin.config.assist.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.vip.admin.oauth2.resource.support.util.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author echo
 * @title: MybatisPlusConfiguration
 * @date 2023/3/15 13:34
 */
@Configuration
public class MybatisPlusConfiguration {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
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
}
