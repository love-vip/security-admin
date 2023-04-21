package com.vip.admin.config.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "RbacUserModifyDto", description = "更新用户请求对象")
public class RbacUserModifyDto extends RbacUserDto{

    @NotNull(message = "主键id不能为空")
    @Schema(name = "id", title = "主键id", example = "1")
    private Long id;

}
