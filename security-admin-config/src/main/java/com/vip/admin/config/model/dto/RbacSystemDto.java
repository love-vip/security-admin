package com.vip.admin.config.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/12 10:33
 */
@Data
@Schema(title = "RbacSystemDto", description = "新增系统请求对象")
public class RbacSystemDto {

    @NotBlank(message = "部门名称不能为空")
    @Schema(name = "deptName", title = "部门名称", example = "丫丫")
    private String deptName;
}
