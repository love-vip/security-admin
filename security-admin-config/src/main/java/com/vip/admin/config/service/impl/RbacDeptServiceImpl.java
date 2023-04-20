package com.vip.admin.config.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.admin.commons.core.support.BaseService;
import com.vip.admin.commons.core.support.Objects;
import com.vip.admin.config.assist.enums.ConfigApiCode;
import com.vip.admin.config.assist.exception.ConfigBizException;
import com.vip.admin.config.model.vo.RbacMembersVo;
import com.vip.admin.config.mapper.RbacDeptMapper;
import com.vip.admin.config.model.domain.RbacDept;
import com.vip.admin.config.model.dto.RbacDeptDto;
import com.vip.admin.config.model.dto.RbacDeptModifyDto;
import com.vip.admin.config.model.query.DeptPageQuery;
import com.vip.admin.config.model.vo.RbacDeptVo;
import com.vip.admin.config.service.RbacDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/3 18:29
 */
@Service
@RequiredArgsConstructor
public class RbacDeptServiceImpl extends BaseService<RbacDept> implements RbacDeptService {

    private final RbacDeptMapper deptMapper;

    @Override
    public void save(RbacDeptDto deptDto) {
        QueryWrapper<RbacDept> wrapper = new QueryWrapper<RbacDept>().eq("dept_name", deptDto.getDeptName());
        RbacDept rbacDept = deptMapper.selectOne(wrapper);
        //部门已存在
        Assert.isNull(rbacDept, () -> new ConfigBizException(ConfigApiCode.CN10005));
        rbacDept = Objects.convert(deptDto, RbacDept.class);
        deptMapper.insert(rbacDept);
    }

    @Override
    public void update(RbacDeptModifyDto deptModifyDto) {
        QueryWrapper<RbacDept> wrapper = new QueryWrapper<RbacDept>().eq("dept_name", deptModifyDto.getDeptName()).ne("id", deptModifyDto.getId());
        RbacDept rbacDept = deptMapper.selectOne(wrapper);
        //部门已存在
        Assert.isNull(rbacDept, () -> new ConfigBizException(ConfigApiCode.CN10005));
        rbacDept = Objects.convert(deptModifyDto, RbacDept.class);
        deptMapper.updateById(rbacDept);
    }

    @Override
    public IPage<RbacDeptVo> selectByPage(DeptPageQuery query) {
        Page<RbacDept> page = new Page<>(query.getPageNum(), query.getPageSize());

        QueryWrapper<RbacDept> wrapper = new QueryWrapper<RbacDept>()
                .eq(Objects.isNotEmpty(query.getLevel()), "level", query.getLevel())
                .like(StrUtil.isNotEmpty(query.getDeptName()), "dept_name", query.getDeptName());

        IPage<RbacDept> pages = deptMapper.selectPage(page, wrapper);

        return Objects.convertPage(pages, RbacDeptVo.class, (d, v) -> {
            Opt.ofNullable(d.getPid()).ifPresent(pid ->{
                RbacDept parent = deptMapper.selectById(d.getPid());
                v.setParentDeptName(parent.getDeptName());
            });
        });
    }

    @Override
    public IPage<RbacMembersVo> selectByPage(int pageNum, int pageSize, int deptId) {
        Page<RbacMembersVo> page = new Page<>(pageNum, pageSize);
        return deptMapper.selectMembers(page, deptId);
    }
}
