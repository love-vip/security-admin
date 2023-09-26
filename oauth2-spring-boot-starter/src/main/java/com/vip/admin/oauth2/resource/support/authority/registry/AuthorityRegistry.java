package com.vip.admin.oauth2.resource.support.authority.registry;

import cn.hutool.extra.spring.SpringUtil;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.api.AuthorityDubboService;
import com.vip.admin.oauth2.request.AuthorityRequest;
import com.vip.admin.oauth2.resource.support.authority.annotation.Authority;
import com.vip.admin.oauth2.resource.support.authority.annotation.DataScope;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author echo
 * @version 1.0
 * @date 2023/5/10 00:29
 */
@Slf4j
public class AuthorityRegistry implements ApplicationListener<ApplicationReadyEvent> {
    @DubboReference
    private AuthorityDubboService authorityDubboService;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        RequestMappingHandlerMapping mapping = SpringUtil.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<AuthorityRequest> list = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            AuthorityRequest request = new AuthorityRequest();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            if(method.hasMethodAnnotation(Authority.class)){
                Authority authority = method.getMethodAnnotation(Authority.class);
                if(authority != null && authority.enable()){
                    PathPatternsRequestCondition paths = info.getPathPatternsCondition();
                    if(paths != null){
                        paths.getPatterns().forEach(path -> request.setUrl(path.getPatternString()));
                    }
                    PatternsRequestCondition patternsCondition = info.getPatternsCondition();
                    if(patternsCondition != null){
                        patternsCondition.getPatterns().forEach(request::setUrl);
                    }
                    request.setMenu(authority.menu());
                    request.setRoute(authority.route());
                    request.setButton(authority.button());
                    request.setInterfaze(method.getMethod().getName());
                    RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
                    for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                        request.setMethod(requestMethod.toString());
                    }
                    //资源url上一层级菜单路由
                    Authority parentAuthority = method.getMethod().getDeclaringClass().getAnnotation(Authority.class);
                    if(parentAuthority != null){
                        request.setSecondMenu(parentAuthority.menu());
                        request.setSecondRoute(parentAuthority.route());
                        request.setParentMenu(parentAuthority.parentMenu());
                        request.setParentRoute(parentAuthority.parentRoute());
                        String system = event.getApplicationContext().getEnvironment().getProperty("spring.application.name");
                        request.setSystem(system);
                        request.setMount(parentAuthority.mount());
                    }
                    DataScope dataScope = method.getMethodAnnotation(DataScope.class);
                    //如果存在数据权限
                    if(dataScope != null && dataScope.filter()){
                        request.setFilter(Boolean.TRUE);
                        request.setMapperId(dataScope.mapperId());
                        request.setHidden(Boolean.TRUE);
                    }
                    list.add(request);
                }
            }
        }
        List<AuthorityRequest> sorts = list.stream()
                .peek(var -> {
                    if(var.isFilter()) {
                        var.setSort(Integer.MIN_VALUE);
                    } else {
                        var.setSort(Integer.MAX_VALUE);
                    }
                })
                .sorted(Comparator.comparing(AuthorityRequest::getParentRoute).thenComparing(AuthorityRequest::getSecondRoute).thenComparing(AuthorityRequest::getSort))
                .collect(Collectors.toList());
        Wrapper<Void> wrapper = authorityDubboService.sync(sorts);
        log.info("同步是否成功：{}", wrapper.success());
    }
}
