package com.vip.admin.config.assist.convert;

import com.vip.admin.config.model.domain.RbacUser;
import com.vip.admin.config.model.dto.RbacUserDto;
import com.vip.admin.config.model.dto.RbacUserModifyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/19 12:51
 */
@Mapper
public interface RbacUserConverter {

    RbacUserConverter INSTANCE = Mappers.getMapper(RbacUserConverter.class);

    @Mapping(target = "expireTime", ignore = true)
    @Mapping(target = "accessToken", ignore = true)
    @Mapping(target = "verifyErrorTimes", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "secret", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "mobile", ignore = true)
    @Mapping(target = "loginErrorTimes", ignore = true)
    @Mapping(target = "lastOperatorId", ignore = true)
    @Mapping(target = "lastOperator", ignore = true)
    @Mapping(target = "initial", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "bind", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "username", source = "email")
    RbacUser convert(RbacUserDto dto);

    @Mapping(target = "expireTime", ignore = true)
    @Mapping(target = "accessToken", ignore = true)
    @Mapping(target = "verifyErrorTimes", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "secret", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "mobile", ignore = true)
    @Mapping(target = "loginErrorTimes", ignore = true)
    @Mapping(target = "lastOperatorId", ignore = true)
    @Mapping(target = "lastOperator", ignore = true)
    @Mapping(target = "initial", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "bind", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "username", source = "email")
    RbacUser convert(RbacUserModifyDto dto);

}
