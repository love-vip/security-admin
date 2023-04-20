package com.vip.admin.oauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vip.admin.oauth2.model.domain.RbacAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author echo
 * @date 2023/4/3 16:47
 */
public interface RbacAuthorityMapper extends BaseMapper<RbacAuthority> {

    List<String> authorities(@Param("username") String username);

//    void insertOrUpdate(RbacAuthority authority);
}

