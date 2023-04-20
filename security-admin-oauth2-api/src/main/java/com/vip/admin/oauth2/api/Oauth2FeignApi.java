package com.vip.admin.oauth2.api;

import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.api.hystrix.Oauth2FeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p></p>
 * @author echo
 * @version 1.0
 * @date 2023/3/30 15:21
 */
@FeignClient(value = "admin-oauth2", fallback = Oauth2FeignHystrix.class)
public interface Oauth2FeignApi {

    @GetMapping(value ="/api/oauth2/notify/{system}")
    Wrapper<?> notifyRefreshAuthority(@PathVariable("system") String system);
}
