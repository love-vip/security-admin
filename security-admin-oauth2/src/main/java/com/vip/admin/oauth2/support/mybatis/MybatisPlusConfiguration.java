package com.vip.admin.oauth2.support.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
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
            this.setFieldValByName("creator", "admin", metaObject);
            this.setFieldValByName("creatorId", 0L, metaObject);
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }

        @Override
        public void updateFill(MetaObject metaObject) {
        }
    }
}
