package com.vip.admin.oauth2.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author echo
 * @version 1.0
 * @date 2023/9/25 19:20
 */
@Data
@RequiredArgsConstructor
public class SecurityDataScopeRequest implements Serializable {

    private final boolean aop;

    private final boolean camel;

    private final String mapperId;

    private final String[] mapperIds;

    private final List<ColumnRequest> columns;

    private final List<SearchRequest> searches;

}
