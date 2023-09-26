package com.vip.admin.oauth2.service;

import com.vip.admin.oauth2.request.SecurityConditionRequest;
import com.vip.admin.oauth2.request.SecurityDataScopeRequest;

import java.util.List;

/**
 * @author echo
 * @version 1.0
 * @date 2023/9/25 19:04
 */
public interface RbacSecurityService {

    String attain(SecurityConditionRequest request);

    void sync(List<SecurityDataScopeRequest> request);
}
