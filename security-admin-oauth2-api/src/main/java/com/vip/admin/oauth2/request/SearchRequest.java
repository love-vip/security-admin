package com.vip.admin.oauth2.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author echo
 * @version 1.0
 * @date 2023/7/21 18:02
 */
@Data
@RequiredArgsConstructor
public class SearchRequest implements Serializable {
    /**
     * 真实值
     */
    private final String actualBox;

    /**
     * 显示值
     */
    private final String displayBox;

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
}
