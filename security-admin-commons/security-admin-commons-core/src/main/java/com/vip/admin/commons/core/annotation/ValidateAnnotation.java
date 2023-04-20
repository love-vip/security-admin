package com.vip.admin.commons.core.annotation;

import java.lang.annotation.*;

/**
 * <p>The interface Validate annotation.</p>
 * @author echo
 * @title: ValidateAnnotation
 * @date 2023/3/15 16:25
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateAnnotation {
    /**
     * Is validate boolean.
     *
     * @return the boolean
     */
    boolean isValidate() default true;
}