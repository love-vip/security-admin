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
@Schema(title = "RbacMembersVo", description = "部门人员实体响应对象")
public class RbacMembersVo extends BaseVo {

    @Schema(name = "jobNum", description = "工号", example = "123")
    private String jobNum;

    @Schema(name = "realName", description = "真实姓名", example = "七七")
    private String realName;

    @Schema(name = "enabled", description = "在职状态", example = "true")
    private boolean enabled;

    @NotBlank(message = "部门名称不能为空")
    @Schema(name = "deptName", title = "部门名称", example = "后台管理组")
    private String deptName;
}
