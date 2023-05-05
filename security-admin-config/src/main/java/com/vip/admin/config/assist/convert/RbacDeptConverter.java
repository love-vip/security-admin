package com.vip.admin.config.assist.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vip.admin.config.model.domain.RbacDept;
import com.vip.admin.config.model.dto.RbacDeptDto;
import com.vip.admin.config.model.vo.RbacDeptVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.function.BiConsumer;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/19 12:51
 */
@Mapper
public interface RbacDeptConverter {

    RbacDeptConverter INSTANCE = Mappers.getMapper(RbacDeptConverter.class);

    RbacDept convert(RbacDeptDto dto);

    RbacDeptVo convert(RbacDept Dept);

    default IPage<RbacDeptVo> convert(IPage<RbacDept> page, BiConsumer<RbacDept, RbacDeptVo> consumer) {
        return page.convert(dept -> {
            RbacDeptVo vo = this.convert(dept);
            consumer.accept(dept, vo);
            return vo;
        });
    }
}
