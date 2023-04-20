package com.vip.admin.config.model.vo;

import com.vip.admin.commons.base.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/12 10:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "RbacDeptVo", description = "部门实体响应对象")
public class RbacDeptVo extends BaseVo {

    @Schema(name = "pid", title = "父id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long pid;

    @Schema(name = "parentDeptName", title = "上级部门", example = "产研中心", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String parentDeptName;

    @NotBlank(message = "部门分级不能为空")
    @Schema(name = "level", title = "部门分级", example = "1")
    private Integer level;

    @NotBlank(message = "部门名称不能为空")
    @Schema(name = "deptName", title = "部门名称", example = "后台管理组")
    private String deptName;
}
