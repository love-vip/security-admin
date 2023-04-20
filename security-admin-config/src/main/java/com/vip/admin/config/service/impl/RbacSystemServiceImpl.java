package com.vip.admin.config.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.admin.commons.base.BaseQuery;
import com.vip.admin.commons.core.support.BaseService;
import com.vip.admin.commons.core.support.Objects;
import com.vip.admin.config.assist.enums.ConfigApiCode;
import com.vip.admin.config.assist.exception.ConfigBizException;
import com.vip.admin.config.mapper.RbacSystemMapper;
import com.vip.admin.config.model.domain.RbacSystem;
import com.vip.admin.config.model.dto.RbacSystemDto;
import com.vip.admin.config.model.dto.RbacSystemModifyDto;
import com.vip.admin.config.model.vo.RbacSystemVo;
import com.vip.admin.config.service.RbacSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/3 18:29
 */
@Service
@RequiredArgsConstructor
public class RbacSystemServiceImpl extends BaseService<RbacSystem> implements RbacSystemService {

    private final RbacSystemMapper systemMapper;

    @Override
    public void save(RbacSystemDto systemDto) {
        QueryWrapper<RbacSystem> wrapper = new QueryWrapper<RbacSystem>().eq("system_name", systemDto.getDeptName());
        RbacSystem rbacSystem = systemMapper.selectOne(wrapper);
        //系统已存在
        Assert.isNull(rbacSystem, () -> new ConfigBizException(ConfigApiCode.CN10005));
        rbacSystem = Objects.convert(systemDto, RbacSystem.class);
        systemMapper.insert(rbacSystem);
    }

    @Override
    public void update(RbacSystemModifyDto systemModifyDto) {
        QueryWrapper<RbacSystem> wrapper = new QueryWrapper<RbacSystem>().eq("system", systemModifyDto.getDeptName())
                .ne("id", systemModifyDto.getId());
        RbacSystem rbacSystem = systemMapper.selectOne(wrapper);
        //系统已存在
        Assert.isNull(rbacSystem, () -> new ConfigBizException(ConfigApiCode.CN10005));
        rbacSystem = Objects.convert(systemModifyDto, RbacSystem.class);
        systemMapper.updateById(rbacSystem);
    }

    @Override
    public IPage<RbacSystemVo> selectByPage(BaseQuery query) {
        Page<RbacSystem> page = new Page<>(query.getPageNum(), query.getPageSize());
        QueryWrapper<RbacSystem> wrapper = new QueryWrapper<>();
        IPage<RbacSystem> pages = systemMapper.selectPage(page, wrapper);
        return Objects.convert(pages, RbacSystemVo.class);
    }
}
