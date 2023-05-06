package com.vip.admin.config.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vip.admin.commons.base.wrapper.Wrapper;
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
import com.vip.admin.oauth2.api.AuthorizationDubboService;
import com.vip.admin.oauth2.request.AccessTokenBatchRequest;
import com.vip.admin.oauth2.request.AccessTokenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author echo
 * @title: RbacUserServiceImpl
 * @date 2022/03/22 15:49
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RbacUserServiceImpl extends BaseService<RbacUser> implements RbacUserService {

    private final RbacUserMapper rbacUserMapper;

    private final PasswordEncoder passwordEncoder;

    @DubboReference(version = "1.0.0", group = "admin-oauth2", timeout = 5000)
    private AuthorizationDubboService authorizationDubboService;

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
        if(rbacUser.getAccessToken() != null && rbacUser.getExpireTime()!= null && rbacUser.getExpireTime().isAfter(LocalDateTime.now())){
            Wrapper<Void> wrapper = authorizationDubboService.expire(new AccessTokenRequest(rbacUser.getAccessToken()));
            log.info("RPC调用结果：{}-作废用户「{}」令牌&令牌对象「{}」", wrapper.success(), rbacUser.getUsername(), rbacUser.getAccessToken());
        }
    }

    @Override
    public void resign(Long[] ids) {
        List<RbacUser> rbacUsers = rbacUserMapper.selectBatchIds(Arrays.asList(ids));
        List<String> accessTokens = rbacUsers.stream()
                .filter(user -> user.getAccessToken() != null)
                .filter(user -> user.getExpireTime() != null)
                .filter(user -> user.getExpireTime().isAfter(LocalDateTime.now()))
                .map(RbacUser::getAccessToken).collect(Collectors.toList());
        if(Objects.isNotEmpty(accessTokens)){
            Wrapper<Void> wrapper = authorizationDubboService.expire(new AccessTokenBatchRequest(accessTokens.toArray(new String[] {})));
            log.info("RPC调用结果：{}-作废用户令牌令牌对象「{}」", wrapper.success(), accessTokens);
        }
        rbacUserMapper.deleteBatchIds(Arrays.asList(ids));
    }

}
