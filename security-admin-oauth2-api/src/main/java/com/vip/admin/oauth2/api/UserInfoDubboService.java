package com.vip.admin.oauth2.api;

import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.request.UserInfoBatchRequest;
import com.vip.admin.oauth2.request.UserInfoRequest;
import com.vip.admin.oauth2.response.UserInfoResponse;

import java.util.List;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/27 12:30
 */
public interface UserInfoDubboService {

    /**
     * 根据id查询单个用户详细信息
     * @param request id
     * @return BaseResponse
     */
    Wrapper<UserInfoResponse> query(UserInfoRequest request);

    /**
     * 根据id数组批量查询用户详细信息
     * @param request ids
     * @return BaseResponse
     */
    Wrapper<List<UserInfoResponse>> query(UserInfoBatchRequest request);
}
