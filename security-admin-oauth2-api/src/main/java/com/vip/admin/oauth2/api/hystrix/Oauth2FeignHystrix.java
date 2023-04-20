package com.vip.admin.oauth2.api.hystrix;

import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.api.Oauth2FeignApi;

/**
 * <p></p>
 * @author echo
 * @version 1.0
 * @date 2023/3/30 15:21
 */
public class Oauth2FeignHystrix implements Oauth2FeignApi {

    @Override
    public Wrapper<?> notifyRefreshAuthority(String system) {
        return WrapMapper.waiting();
    }
}