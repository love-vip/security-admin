package com.vip.admin.oauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vip.admin.oauth2.model.domain.RbacPermission;
import com.vip.admin.oauth2.model.vo.RbacPermissionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author echo
 * @date 2023/4/3 16:47
 */
public interface RbacPermissionMapper extends BaseMapper<RbacPermission> {

    List<RbacPermissionVo> menus(@Param("username") String username);
}

