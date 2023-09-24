package com.vip.admin.config.service;

import com.vip.admin.commons.core.support.IService;
import com.vip.admin.config.model.domain.RbacPermission;
import com.vip.admin.config.model.vo.RbacPermissionVo;

import java.util.List;

/**
 * @author echo
 * @date 2023/4/3 15:47
 */
public interface RbacPermissionService extends IService<RbacPermission> {

    /**
     * 查询用户访问系统的可见菜单列表
     * @return 菜单列表
     */
    List<RbacPermissionVo> query();

}
