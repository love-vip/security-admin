package com.vip.admin.config.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/12 10:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "RbacDeptModifyDto", description = "修改部门请求对象")
public class RbacDeptModifyDto extends RbacDeptDto{

    @NotNull(message = "主键id不能为空")
    @Schema(name = "id", title = "主键id", example = "1")
    private Long id;
}
