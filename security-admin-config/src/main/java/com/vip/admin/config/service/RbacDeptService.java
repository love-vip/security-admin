package com.vip.admin.config.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vip.admin.commons.core.support.IService;
import com.vip.admin.config.model.domain.RbacDept;
import com.vip.admin.config.model.dto.RbacDeptDto;
import com.vip.admin.config.model.dto.RbacDeptModifyDto;
import com.vip.admin.config.model.query.DeptPageQuery;
import com.vip.admin.config.model.vo.RbacDeptVo;
import com.vip.admin.config.model.vo.RbacMembersVo;

/**
 * @author echo
 * @date 2023/4/3 15:47
 */
public interface RbacDeptService extends IService<RbacDept> {

    /**
     * <p>新增部门</p>
     * @param deptDto 部门信息
     */
    void save(RbacDeptDto deptDto);

    /**
     * <p>修改部门信息</p>
     * @param deptModifyDto 部门信息
     */
    void update(RbacDeptModifyDto deptModifyDto);

    /**
     * <p>分页查询部门</p>
     * @param query 查询条件
     * @return 分页数据
     */
    IPage<RbacDeptVo> selectByPage(DeptPageQuery query);

    /**
     * <p>分页查询人员清单</p>
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @param deptId 部门id
     * @return 分页数据
     */
    IPage<RbacMembersVo> selectByPage(int pageNum, int pageSize, int deptId);
}
