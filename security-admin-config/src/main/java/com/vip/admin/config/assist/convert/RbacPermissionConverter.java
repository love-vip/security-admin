package com.vip.admin.config.assist.convert;

import com.vip.admin.config.model.domain.RbacPermission;
import com.vip.admin.config.model.dto.RbacPermDto;
import com.vip.admin.config.model.dto.RbacPermModifyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/19 12:51
 */
@Mapper
public interface RbacPermissionConverter {

    RbacPermissionConverter INSTANCE = Mappers.getMapper(RbacPermissionConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorityId", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "lastOperatorId", ignore = true)
    @Mapping(target = "lastOperator", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    RbacPermission convert(RbacPermDto dto);

    @Mapping(target = "permType", ignore = true)
    @Mapping(target = "authorityId", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "lastOperatorId", ignore = true)
    @Mapping(target = "lastOperator", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    RbacPermission convert(RbacPermModifyDto dto);
}
