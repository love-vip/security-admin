package com.vip.admin.oauth2.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vip.admin.commons.base.enums.BooleanEnum;
import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.commons.core.support.BaseService;
import com.vip.admin.commons.core.support.Objects;
import com.vip.admin.commons.core.utils.JacksonUtil;
import com.vip.admin.oauth2.mapper.RbacUserMapper;
import com.vip.admin.oauth2.model.domain.RbacUser;
import com.vip.admin.oauth2.model.dto.Oauth2PasswordDto;
import com.vip.admin.oauth2.model.vo.SignVo;
import com.vip.admin.oauth2.service.RbacUserService;
import com.vip.admin.oauth2.support.enums.Oauth2ApiCode;
import com.vip.admin.oauth2.support.exception.Oauth2BizException;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @author echo
 * @title: RbacUserServiceImpl
 * @date 2022/03/22 15:49
 */
@Service
@RequiredArgsConstructor
public class RbacUserServiceImpl extends BaseService<RbacUser> implements RbacUserService {

    private final Environment environment;
    private final RbacUserMapper rbacUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoadBalancerClient loadBalancerClient;
    private final OAuth2AuthorizationService authorizationService;

    @Override
    public Wrapper<?> sign(Map<String, Object> args) {
        String clientId = environment.getProperty("spring.application.name");
        ServiceInstance serviceInstance = loadBalancerClient.choose(clientId);
        String uri = serviceInstance.getUri().toString();
        Map<String, String> metadata = serviceInstance.getMetadata();
        String tokenUrl = metadata.containsKey("context-path") ? uri + metadata.get("context-path") + "/oauth2/token" : uri + "/oauth2/token";
        HttpResponse response = HttpRequest.post(tokenUrl).form(args).execute();
        if(response.isOk()){
            SignVo vo = JacksonUtil.parseJson(response.body(), SignVo.class);
            return WrapMapper.success(vo);
        }
        return JacksonUtil.parseJson(response.body(), Wrapper.class);
    }

    @Override
    public void updatePassword(Oauth2PasswordDto passwordDto) {
        OAuth2Authorization authorization = authorizationService.findByToken(passwordDto.getAccess_token(), OAuth2TokenType.ACCESS_TOKEN);
        String principalName = Optional.ofNullable(authorization).map(OAuth2Authorization::getPrincipalName).orElseThrow(() -> new Oauth2BizException(Oauth2ApiCode.ACCOUNT_CREDENTIAL_EXPIRED));
        updatePassword(principalName, passwordDto.getPassword());
    }

    @Override
    public void updatePassword(String username, String password) {
        //新密码不能和旧密码一样
        RbacUser rbacUser = getByUsername(username).orElseThrow(() -> new Oauth2BizException(Oauth2ApiCode.CN10001));
        Assert.isFalse(passwordEncoder.matches(password, rbacUser.getPassword()), () -> new Oauth2BizException(Oauth2ApiCode.CN10002));
        rbacUser.setPassword(passwordEncoder.encode(password));
        rbacUserMapper.updateById(rbacUser);
    }

    @Override
    public Optional<RbacUser> getByUsername(String username) {
        return Optional.ofNullable(rbacUserMapper.selectOne(new QueryWrapper<RbacUser>().eq("username", username)));
    }

    @Override
    public Optional<RbacUser> getByMobile(String mobile) {
        return Optional.ofNullable(rbacUserMapper.selectOne(new QueryWrapper<RbacUser>().eq("mobile", mobile)));
    }

    @Override
    public void locked(String username) {
        RbacUser rbacUser = getByUsername(username).orElseThrow(() -> new Oauth2BizException(Oauth2ApiCode.CN10001));
        rbacUser.setAccountNonLocked(BooleanEnum.NEGATIVE.isBool());
        rbacUserMapper.updateById(rbacUser);
    }

    @Override
    public void unlock(String username) {
        RbacUser rbacUser = getByUsername(username).orElseThrow(() -> new Oauth2BizException(Oauth2ApiCode.CN10001));
        rbacUser.setAccountNonLocked(BooleanEnum.POSITIVE.isBool());
        rbacUserMapper.updateById(rbacUser);
    }

    @Override
    public String fillSecret(String username) {
        RbacUser rbacUser = getByUsername(username).orElseThrow(() -> new Oauth2BizException(Oauth2ApiCode.CN10001));
        if(Objects.isNotEmpty(rbacUser.getSecret())){
            return rbacUser.getSecret();
        }
        //从未绑定过谷歌校验器
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials();
        String secret = googleAuthenticatorKey.getKey();
        rbacUser.setSecret(secret);
//        user.setBind(BooleanEnum.POSITIVE.isBool());
        rbacUserMapper.updateById(rbacUser);
        return secret;

    }

}
