package com.vip.admin.oauth2.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author echo
 * @version 1.0
 * @date 2023/9/25 16:58
 */
@Data
@RequiredArgsConstructor
public class SecurityConditionRequest implements Serializable {

    private final Long userId;

    private final String mapperId;
}