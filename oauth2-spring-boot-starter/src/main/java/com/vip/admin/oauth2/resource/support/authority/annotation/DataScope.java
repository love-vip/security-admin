package com.vip.admin.oauth2.resource.support.authority.annotation;

import java.lang.annotation.*;

/**
 * @author echo
 * @version 1.0
 * @date 2023/5/9 21:00
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DataScope {

    /**
     * SpEL的key
     * @return String
     */
    String key() default "";

    /**
     * 是否需数据过滤（默认不过滤）
     * @return boolean
     */
    boolean filter() default false;

    /**
     * 是否需aop处理（默认不处理）
     * @return boolean
     */
    boolean aop() default false;

    /**
     * 是否驼峰（默认下划线）
     * @return boolean
     */
    boolean camel() default false;

    /**
     * sql映射id
     * @return String
     */
    String mapperId() default "";

    /**
     * sql映射id集合
     * @return Array
     */
    String[] mapperIds() default {};

    /**
     * 列值
     * @return Array
     */
    Column[] columns() default {};

    /**
     * 查询表单值
     * @return Array
     */
    Search[] searches() default {};
}
