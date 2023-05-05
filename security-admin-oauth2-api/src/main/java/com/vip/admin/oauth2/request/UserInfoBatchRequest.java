package com.vip.admin.oauth2.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/27 12:32
 */
@Data
@RequiredArgsConstructor
public class UserInfoBatchRequest implements Serializable {

    private final Long[] ids;
}
