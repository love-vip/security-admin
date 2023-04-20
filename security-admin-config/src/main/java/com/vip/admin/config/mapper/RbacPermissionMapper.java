package com.vip.admin.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vip.admin.config.model.domain.RbacPermission;
import com.vip.admin.config.model.vo.RbacPermissionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author echo
 * @date 2023/4/3 16:47
 */
public interface RbacPermissionMapper extends BaseMapper<RbacPermission> {

    List<RbacPermissionVo> menus(@Param("username") String username);
}

