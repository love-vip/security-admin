package com.vip.admin.oauth2.service.impl;

import com.vip.admin.commons.core.support.BaseService;
import com.vip.admin.oauth2.mapper.RbacPermissionMapper;
import com.vip.admin.oauth2.model.domain.RbacPermission;
import com.vip.admin.oauth2.model.vo.RbacPermissionVo;
import com.vip.admin.oauth2.service.RbacPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/3 18:29
 */
@Service
@RequiredArgsConstructor
public class RbacPermissionServiceImpl extends BaseService<RbacPermission> implements RbacPermissionService {

    private final RbacPermissionMapper permissionMapper;

    /**
     * 查询用户访问系统的可见菜单列表
     * @param authentication Principal
     * @return 菜单列表
     */
    @Override
    public List<RbacPermissionVo> query(Authentication authentication) {
//        OAuth2IntrospectionAuthenticatedPrincipal principal = (OAuth2IntrospectionAuthenticatedPrincipal)authentication.getPrincipal();
//        String clientId = principal.getAttribute("client_id");
        String username = authentication.getName();
        return permissionMapper.menus(username);
    }
}
