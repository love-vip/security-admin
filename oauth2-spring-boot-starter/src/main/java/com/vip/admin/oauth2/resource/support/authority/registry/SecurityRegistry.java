package com.vip.admin.oauth2.resource.support.authority.registry;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.api.SecurityDubboService;
import com.vip.admin.oauth2.request.ColumnRequest;
import com.vip.admin.oauth2.request.SearchRequest;
import com.vip.admin.oauth2.request.SecurityDataScopeRequest;
import com.vip.admin.oauth2.resource.support.authority.annotation.DataScope;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author echo
 * @version 1.0
 * @date 2023/7/21 16:31
 */
@Slf4j
public class SecurityRegistry implements ApplicationListener<ApplicationReadyEvent> {
    @DubboReference
    private SecurityDubboService securityDubboService;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        RequestMappingHandlerMapping mapping = SpringUtil.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<SecurityDataScopeRequest> list = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            HandlerMethod method = m.getValue();
            if(method.hasMethodAnnotation(DataScope.class)){
                DataScope dataScope = method.getMethodAnnotation(DataScope.class);
                if(dataScope != null && dataScope.filter()){
                    List<ColumnRequest> columns = new ArrayList<>();
                    List<SearchRequest> searches = new ArrayList<>();
                    if(!ObjectUtils.isEmpty(dataScope.columns())){
                        columns = Arrays.stream(dataScope.columns()).map(v -> new ColumnRequest(v.actualColumn(), v.displayColumn(), v.checked(), StrUtil.isAllNotEmpty(v.mapping()), v.mapping(), v.mask(), "Null".equals(v.type().name()) ? null : v.type().name(), v.filter())).collect(Collectors.toList());
                    }
                    if(!ObjectUtils.isEmpty(dataScope.searches())){
                        searches = Arrays.stream(dataScope.searches()).map(v -> new SearchRequest(v.actualBox(), v.displayBox(), v.checked(), StrUtil.isAllNotEmpty(v.mapping()), v.mapping())).collect(Collectors.toList());
                    }
                    String[] mapperIds = dataScope.mapperIds().length == 0 ? new String[]{dataScope.mapperId()} : dataScope.mapperIds();
                    list.add(new SecurityDataScopeRequest(dataScope.aop(), dataScope.camel(), dataScope.mapperId(), mapperIds, columns, searches));
                }
            }
        }
        List<SecurityDataScopeRequest> sorts = list.stream().sorted(Comparator.comparing(SecurityDataScopeRequest::getMapperId)).collect(Collectors.toList());
        Wrapper<Void> wrapper = securityDubboService.sync(sorts);
        log.info("数据权限同步是否成功：{}", wrapper.success());
    }
}
