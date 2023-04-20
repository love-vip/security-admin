package com.vip.admin.oauth2.service;

import com.vip.admin.commons.core.support.IService;
import com.vip.admin.oauth2.model.domain.RbacAuthority;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author echo
 * @date 2023/4/3 15:47
 */
public interface RbacAuthorityService extends IService<RbacAuthority> {

    /**
     * 获取可访问资源
     * @param username 用户名
     * @return 可访问资源
     */
    List<String> authorities(String username);

    /**
     * 通知刷新最新资源路径
     * @param serviceInstance 来源系统
     */
    void notifyRefreshAuthority(ServiceInstance serviceInstance);
}
