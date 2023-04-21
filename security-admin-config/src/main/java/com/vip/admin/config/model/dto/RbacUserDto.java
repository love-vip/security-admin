package com.vip.admin.config.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@Schema(title = "RbacUserDto", description = "新增用户请求对象")
public class RbacUserDto {

    @NotBlank(message = "用户名不能为空")
    @Schema(name = "username", title = "用户名", example = "echo")
    private String username;

    @NotBlank(message = "真实姓名不能为空")
    @Schema(name = "realName", title = "真实姓名", example = "丫丫")
    private String realName;

    @NotBlank(message = "工号不能为空")
    @Schema(name = "jobNum", description = "工号", example = "123")
    private String jobNum;

    @NotBlank(message = "手机号不能为空")
    @Schema(name = "mobile", title = "手机号", example = "18812341234")
    private String mobile;

    @Email
    @NotBlank(message = "邮箱不能为空")
    @Schema(name = "email", title = "邮箱", example = "echo@gmail.com")
    private String email;

}
