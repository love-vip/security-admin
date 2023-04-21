package com.vip.admin.config.model.vo;

import com.vip.admin.commons.base.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/12 10:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "RbacSystemVo", description = "系统实体响应对象")
public class RbacSystemVo extends BaseVo {

    @NotBlank(message = "系统不能为空")
    @Schema(name = "system", title = "系统", example = "admin-config")
    private String system;

    @NotBlank(message = "系统名称不能为空")
    @Schema(name = "systemName", title = "系统名称", example = "后台管理组")
    private String systemName;
}
