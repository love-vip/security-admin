package com.vip.admin.oauth2.resource.support.authority.annotation;

import java.lang.annotation.*;

/**
 * @author echo
 * @version 1.0
 * @date 2023/7/21 14:09
 */
@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Search {

    /**
     * 显示值
     * @return String
     */
    String displayBox() default "";

    /**
     * 真实值
     * @return String
     */
    String actualBox() default "";

    /**
     * 是否必须选中
     * @return 布尔值
     */
    boolean checked() default false;

    /**
     * 多列映射
     * @return String
     */
    String[] mapping() default {};
}
