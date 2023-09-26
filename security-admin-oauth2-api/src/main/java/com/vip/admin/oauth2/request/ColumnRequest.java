package com.vip.admin.oauth2.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author echo
 * @version 1.0
 * @date 2023/7/21 18:01
 */
@Data
@RequiredArgsConstructor
public class ColumnRequest implements Serializable {
    /**
     * 真实值
     */
    private final String actualColumn;

    /**
     * 显示值
     */
    private final String displayColumn;

    /**
     * 是否必须选中
     */
    private final boolean checked;

    /**
     * 是否包含多列(0:否｜1:是)
     */
    private final boolean multi;

    /**
     * 列(显示值)
     */
    private final String[] mapping;

    /**
     * 是否遮罩脱敏(0:否｜1:是)
     */
    private final boolean mask;

    /**
     * 数据类型（String, Number, Datetime, Boolean）
     */
    private final String type;

    /**
     * 是否支持行过滤(0:否｜1:是)
     */
    private final boolean filter;
}