package com.vip.admin.config.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/12 10:38
 */
@Data
@Schema(title = "RbacPermModifyDto", description = "修改菜单请求对象")
public class RbacPermModifyDto {

    @NotNull(message = "主键id不能为空")
    @Schema(name = "id", title = "主键id", example = "1")
    private Long id;

    @NotBlank(message = "系统不能为空")
    @Schema(name = "system", title = "系统", example = "admin-config-manager")
    private String system;

    @Schema(name = "pid", title = "父id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long pid;

    @NotBlank(message = "权限名称不能为空")
    @Schema(name = "permName", title = "权限名称", example = "用户管理")
    private String permName;

    @NotBlank(message = "权限路由不能为空")
    @Schema(name = "permRoute", title = "权限路由", example = "/systemPromise/users")
    private String permRoute;

    @Schema(name = "permIcon", title = "权限图标", example = "top", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String permIcon;

    @Schema(name = "sort", title = "排序", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer sort = 1;
}
