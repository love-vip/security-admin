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
public @interface Column {

    /**
     * 显示值
     * @return String
     */
    String displayColumn() default "";

    /**
     * 真实值
     * @return String
     */
    String actualColumn() default "";

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

    /**
     * 数据类型（String, Number, Datetime, Boolean）
     * @return 枚举
     */
    Types type() default Types.Null;

    /**
     * 是否遮罩脱敏
     * @return 布尔值
     */
    boolean mask() default false;

    /**
     * 是否支持行过滤(0:否｜1:是)
     * @return 布尔值
     */
    boolean filter() default true;

}
