package com.vip.admin.oauth2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/6 19:10
 */
@Data
@Schema(title = "Oauth2PasswordDto", description = "修改密码请求对象")
public class Oauth2PasswordDto {
    @NotBlank(message = "访问令牌不能为空")
    @Schema(name = "access_token", title = "访问令牌", example = "QFJdyCh-lnz")
    private String access_token;
    @NotBlank(message = "密码不能为空")
    @Schema(name = "password", title = "密码", example = "078565")
    private String password;
}
