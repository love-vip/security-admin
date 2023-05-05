package com.vip.admin.config.assist.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vip.admin.config.model.domain.RbacSystem;
import com.vip.admin.config.model.dto.RbacSystemDto;
import com.vip.admin.config.model.vo.RbacSystemVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/19 12:51
 */
@Mapper
public interface RbacSystemConverter {

    RbacSystemConverter INSTANCE = Mappers.getMapper(RbacSystemConverter.class);

    RbacSystem convert(RbacSystemDto dto);

    RbacSystemVo convert(RbacSystem System);

    default IPage<RbacSystemVo> convert(IPage<RbacSystem> page) {
        return page.convert(this::convert);
    }
}
