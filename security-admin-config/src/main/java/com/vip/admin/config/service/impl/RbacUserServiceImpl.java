package com.vip.admin.config.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.admin.commons.base.enums.BooleanEnum;
import com.vip.admin.commons.core.support.BaseService;
import com.vip.admin.commons.core.support.Objects;
import com.vip.admin.config.assist.enums.ConfigApiCode;
import com.vip.admin.config.assist.exception.ConfigBizException;
import com.vip.admin.config.model.vo.RbacUserVo;
import com.vip.admin.config.mapper.RbacUserMapper;
import com.vip.admin.config.model.domain.RbacUser;
import com.vip.admin.config.model.dto.RbacUserDto;
import com.vip.admin.config.model.dto.RbacUserModifyDto;
import com.vip.admin.config.model.query.UserPageQuery;
import com.vip.admin.config.service.RbacUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author echo
 * @title: RbacUserServiceImpl
 * @date 2022/03/22 15:49
 */
@Service
@RequiredArgsConstructor
public class RbacUserServiceImpl extends BaseService<RbacUser> implements RbacUserService {

    private final RbacUserMapper rbacUserMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<RbacUser> getByUsername(String username) {
        return Optional.ofNullable(rbacUserMapper.selectOne(new QueryWrapper<RbacUser>().eq("username", username)));
    }

    @Override
    public Optional<RbacUser> getByMobile(String mobile) {
        return Optional.ofNullable(rbacUserMapper.selectOne(new QueryWrapper<RbacUser>().eq("mobile", mobile)));
    }

    @Override
    public void save(RbacUserDto userDto) {
        //用户名不能重复
        Optional<RbacUser> optional = getByUsername(userDto.getUsername());
        Assert.isFalse(optional.isPresent(), () -> new ConfigBizException(ConfigApiCode.CN10003));
        //手机号不能重复
        optional = getByMobile(userDto.getMobile());
        Assert.isFalse(optional.isPresent(), () -> new ConfigBizException(ConfigApiCode.CN10004));
        RbacUser rbacUser = Objects.convert(userDto, RbacUser.class);
        //初始密码
        rbacUser.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        rbacUserMapper.insert(rbacUser);
    }

    @Override
    public void update(RbacUserModifyDto userModifyDto) {
        QueryWrapper<RbacUser> wrapper = new QueryWrapper<RbacUser>().eq("username", userModifyDto.getUsername()).ne("id", userModifyDto.getId());
        RbacUser rbacUser = rbacUserMapper.selectOne(wrapper);
        //用户名不能重复
        Assert.isNull(rbacUser, () -> new ConfigBizException(ConfigApiCode.CN10003));
        //手机号都不能重复
        wrapper = new QueryWrapper<RbacUser>().eq("mobile", userModifyDto.getMobile()).ne("id", userModifyDto.getId());
        rbacUser = rbacUserMapper.selectOne(wrapper);
        Assert.isNull(rbacUser, () -> new ConfigBizException(ConfigApiCode.CN10003));
        rbacUser = Objects.convert(userModifyDto, RbacUser.class);
        rbacUserMapper.updateById(rbacUser);
    }

    @Override
    public void locked(String username) {
        RbacUser rbacUser = getByUsername(username).orElseThrow(() -> new ConfigBizException(ConfigApiCode.CN10001));
        rbacUser.setAccountNonLocked(BooleanEnum.NEGATIVE.isBool());
        rbacUserMapper.updateById(rbacUser);
    }

    @Override
    public void unlock(String username) {
        RbacUser rbacUser = getByUsername(username).orElseThrow(() -> new ConfigBizException(ConfigApiCode.CN10001));
        rbacUser.setAccountNonLocked(BooleanEnum.POSITIVE.isBool());
        rbacUserMapper.updateById(rbacUser);
    }

    @Override
    public IPage<RbacUserVo> selectByPage(UserPageQuery query) {

        Page<RbacUser> page = new Page<>(query.getPageNum(), query.getPageSize());

        QueryWrapper<RbacUser> wrapper = new QueryWrapper<RbacUser>()
                .eq(Objects.isNotEmpty(query.getEnabled()), "enabled", query.getEnabled())
                .like(StrUtil.isNotEmpty(query.getUsername()), "username", query.getUsername());

        IPage<RbacUser> pages = rbacUserMapper.selectPage(page, wrapper);

        return Objects.convert(pages, RbacUserVo.class);
    }

    @Override
    public void reset(Long id) {
        RbacUser rbacUser = rbacUserMapper.selectById(id);
        rbacUser.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        rbacUser.setInitial(BooleanEnum.POSITIVE.isBool());
        rbacUserMapper.updateById(rbacUser);
    }

    @Override
    public void resign(Long[] ids) {
        rbacUserMapper.deleteBatchIds(Arrays.asList(ids));
    }

}
