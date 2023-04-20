package com.vip.admin.oauth2.service.impl;

import cn.hutool.http.HttpRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vip.admin.commons.core.support.BaseService;
import com.vip.admin.commons.core.utils.JacksonUtil;
import com.vip.admin.oauth2.mapper.RbacAuthorityMapper;
import com.vip.admin.oauth2.model.domain.RbacAuthority;
import com.vip.admin.oauth2.service.RbacAuthorityService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/3 18:29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RbacAuthorityServiceImpl extends BaseService<RbacAuthority> implements RbacAuthorityService {

    private final RbacAuthorityMapper rbacAuthorityMapper;

    @Override
    public List<String> authorities(String username) {
        return rbacAuthorityMapper.authorities(username);
    }

    @Override
    public void notifyRefreshAuthority(ServiceInstance serviceInstance) {
        String uri = serviceInstance.getUri().toString();
        Map<String, String> metadata = serviceInstance.getMetadata();
        String docsUrl = metadata.containsKey("context-path") ? uri + metadata.get("context-path") + "/v3/api-docs" : uri + "/v3/api-docs";
        String docs = HttpRequest.get(docsUrl).execute().body();
        Swagger swagger = JacksonUtil.parseJson(docs, Swagger.class);
        /*List<Swagger.Tag> tags = swagger.getTags();*/
        QueryWrapper<RbacAuthority> wrapper = new QueryWrapper<RbacAuthority>().eq("system", serviceInstance.getServiceId());
        List<RbacAuthority> authorities = rbacAuthorityMapper.selectList(wrapper);
        Map<String, Map<String, Swagger.Method>> paths = swagger.getPaths();
        paths.keySet().forEach(url -> {
            Map<String, Swagger.Method> methods = paths.get(url);
            methods.forEach((key, value) -> {
                log.info("系统：「{}」解析{}得到url：{}", serviceInstance.getServiceId(),docsUrl, url);
                if(authorities.stream().noneMatch(authority -> authority.getAuthorityUrl().equals(url))){
                    RbacAuthority authority = new RbacAuthority();
                    authority.setSystem(serviceInstance.getServiceId());
                    authority.setGroup(value.getTags().get(0));
                    authority.setAuthorityUrl(url);
                    authority.setMethod(key);
                    authority.setAuthorityAbbr(value.getOperationId());
                    authority.setAuthorityName(value.getDescription());
                    rbacAuthorityMapper.insert(authority);
                }
            });
        });
    }

    @Data
    public static class Swagger {

        private List<Tag> tags;

        private Map<String, Map<String, Method>> paths;

        @Data
        public static class Tag {
            private String name;
            private String description;
        }

        @Data
        public static class Method {

            private List<String> tags;
            private String summary;
            private String description;
            private String operationId;
        }
    }
}
