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
public @interface Authority {

    /**
     * SpEL的key
     * @return String
     */
    String key() default "";

    /**
     * 启用（默认不生效）
     * @return String
     */
    boolean enable() default false;

    /**
     * 挂载点
     * @return String
     */
    String mount() default "临时名称(可更改)";

    /**
     * 归属菜单
     * @return String
     */
    String menu() default "";

    /**
     * 按钮名称
     * @return String
     */
    String button() default "";

    /**
     * 路由名称
     * @return String
     */
    String route() default "";

    /**
     * 父级菜单
     * @return String
     */
    String parentMenu() default "";

    /**
     * 父级路由名称
     * @return String
     */
    String parentRoute() default "";

}
