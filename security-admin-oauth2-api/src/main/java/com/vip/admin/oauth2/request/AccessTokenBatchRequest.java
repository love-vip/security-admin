package com.vip.admin.oauth2.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/29 13:14
 */
@Data
@RequiredArgsConstructor
public class AccessTokenBatchRequest implements Serializable {

    private final String[] accessTokens;
}
