package com.vip.admin.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.admin.config.model.domain.RbacDept;
import com.vip.admin.config.model.vo.RbacMembersVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/12 10:21
 */
public interface RbacDeptMapper extends BaseMapper<RbacDept> {

    IPage<RbacMembersVo> selectMembers(Page<RbacMembersVo> page, @Param("deptId") int deptId);
}
