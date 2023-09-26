package com.vip.admin.oauth2.api;

import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.request.SecurityConditionRequest;
import com.vip.admin.oauth2.request.SecurityDataScopeRequest;

import java.util.List;

/**
 * @author echo
 * @version 1.0
 * @date 2023/9/25 16:58
 */
public interface SecurityDubboService {

    /**
     * 获取用户某个查询的where条件
     * @param request 获取用户某个查询的where条件
     * @return Wrapper
     */
    Wrapper<String> attain(SecurityConditionRequest request);

    /**
     * 同步接口数据
     * @param request 同步接口数据
     * @return Wrapper
     */
    Wrapper<Void> sync(List<SecurityDataScopeRequest> request);
}
