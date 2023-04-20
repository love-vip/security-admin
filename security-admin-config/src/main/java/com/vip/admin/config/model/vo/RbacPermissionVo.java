package com.vip.admin.config.model.vo;

import com.vip.admin.commons.base.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/3 18:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "RbacPermissionVo", description = "权限实体响应对象")
public class RbacPermissionVo extends BaseVo {

    /**
     * 父id
     */
    @Schema(name = "pid", title = "父id", example = "1")
    private Long pid;

    /**
     * 所属系统
     */
    @Schema(name = "system", title = "所属系统", example = "oauth2")
    private String system;

    /**
     * 系统名称
     */
    @Schema(name = "systemName", title = "系统名称", example = "oauth2")
    private String systemName;

    /**
     * 权限路由
     */
    @Schema(name = "permRoute", title = "权限路由", example = "deptManage")
    private String permRoute;

    /**
     * 权限名称
     */
    @Schema(name = "permName", title = "权限名称", example = "系统管理")
    private String permName;

    /**
     * 权限图标
     */
    @Schema(name = "permIcon", title = "权限图标", example = "abc")
    private String permIcon;

    /**
     * 权限类型
     */
    @Schema(name = "permType", title = "权限类型", example = "MENU")
    private String permType;
}
