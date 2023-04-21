/*package com.vip.admin.oauth2.web.registry;

import com.alibaba.cloud.dubbo.registry.event.ServiceInstancesChangedEvent;
import com.vip.admin.oauth2.service.RbacAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;*/


/**
 * <p>新的实例被注册</p>
 * @author echo
 * @version 1.0
 * @date 2023/4/17 13:55
 */
/*@Component
@RequiredArgsConstructor
public class Oauth2CloudRegistry implements ApplicationListener<ServiceInstancesChangedEvent> {

    private final RbacAuthorityService authorityService;

    @Override
    public void onApplicationEvent(ServiceInstancesChangedEvent event) {
        List<ServiceInstance> serviceInstances = event.getServiceInstances();
        //如果某服务有多个实例的情况下，只拿最后一个(最新的)实例的api-doc文档操作
        serviceInstances.stream().reduce((first, second) -> second).ifPresent(authorityService::notifyRefreshAuthority);
    }

}*/
