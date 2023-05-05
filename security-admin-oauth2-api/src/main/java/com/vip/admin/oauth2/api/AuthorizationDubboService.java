package com.vip.admin.oauth2.api;

import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.request.AccessTokenBatchRequest;
import com.vip.admin.oauth2.request.AccessTokenRequest;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/29 13:10
 */
public interface AuthorizationDubboService {

    /**
     * 作废用户令牌
     * @param request 用户令牌
     * @return BaseResponse
     */
    Wrapper<Void> expire(AccessTokenRequest request);

    /**
     * 批量作废用户令牌
     * @param request 用户令牌
     * @return BaseResponse
     */
    Wrapper<Void> expire(AccessTokenBatchRequest request);

    /**
     * 更新用户令牌对象
     * @param request 用户令牌
     * @return BaseResponse
     */
    Wrapper<Void> renew(AccessTokenRequest request);
}
