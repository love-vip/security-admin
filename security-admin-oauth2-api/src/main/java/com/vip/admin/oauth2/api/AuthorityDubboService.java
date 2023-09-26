package com.vip.admin.oauth2.api;

import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.request.AuthorityRequest;

import java.util.List;

/**
 * @author echo
 * @version 1.0
 * @date 2023/9/25 22:36
 */
public interface AuthorityDubboService {

    /**
     * 同步接口数据
     * @param request 同步接口数据
     * @return Wrapper
     */
    Wrapper<Void> sync(List<AuthorityRequest> request);
}
