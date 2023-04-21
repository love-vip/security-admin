package com.vip.admin.config.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/12 10:33
 */
@Data
@Schema(title = "RbacDeptDto", description = "新增部门请求对象")
public class RbacDeptDto {

    @Schema(name = "pid", title = "父id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long pid;

    @NotNull(message = "部门分级不能为空")
    @Schema(name = "level", title = "部门分级", example = "1")
    private Integer level;

    @NotBlank(message = "部门名称不能为空")
    @Schema(name = "deptName", title = "部门名称", example = "丫丫")
    private String deptName;
}
