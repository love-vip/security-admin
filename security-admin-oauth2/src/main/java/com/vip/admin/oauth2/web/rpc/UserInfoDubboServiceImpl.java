package com.vip.admin.oauth2.web.rpc;

import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.api.UserInfoDubboService;
import com.vip.admin.oauth2.model.domain.RbacUser;
import com.vip.admin.oauth2.request.UserInfoBatchRequest;
import com.vip.admin.oauth2.request.UserInfoRequest;
import com.vip.admin.oauth2.response.UserInfoResponse;
import com.vip.admin.oauth2.service.RbacUserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/27 12:40
 */
@RequiredArgsConstructor
@DubboService(version = "1.0.0", group = "admin-oauth2", timeout = 5000)
public class UserInfoDubboServiceImpl implements UserInfoDubboService {

    private final RbacUserService userService;

    @Override
    public Wrapper<UserInfoResponse> query(UserInfoRequest request) {
        Optional<RbacUser> optional = userService.selectByPrimaryKey(request.getId());
        UserInfoResponse userInfo = optional.map(UserInfoConverter.INSTANCE::convert).orElseGet(UserInfoResponse::new);
        return WrapMapper.success(userInfo);
    }

    @Override
    public Wrapper<List<UserInfoResponse>> query(UserInfoBatchRequest request) {
        Optional<List<RbacUser>> optional = userService.selectByPrimaryKey(request.getIds());
        List<UserInfoResponse> userInfos = optional.map(UserInfoConverter.INSTANCE::convert).orElseGet(ArrayList::new);
        return WrapMapper.success(userInfos);
    }

    @Mapper
    public interface UserInfoConverter {
        UserInfoConverter INSTANCE = Mappers.getMapper(UserInfoConverter.class);

        @Mapping(target = "id", source = "id")
        UserInfoResponse convert(RbacUser rbacUser);

        @Mapping(target = "id", source = "id")
        List<UserInfoResponse> convert(List<RbacUser> rbacUser);
    }
}
