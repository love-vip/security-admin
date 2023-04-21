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
@Schema(title = "Oauth2LoginDto", description = "密码模式登录请求对象")
public class Oauth2LoginDto {
    @Schema(name = "client_id", title = "客户端id", hidden = true, defaultValue = "client")
    private String client_id = "client";
    @Schema(name = "client_secret", title = "客户端密钥", hidden = true,  defaultValue = "secret")
    private String client_secret = "secret";
    @Schema(name = "grant_type", title = "授权类型", hidden = true,  defaultValue = "password")
    private String grant_type = "password";
    @NotBlank(message = "用户名不能为空")
    @Schema(name = "username", title = "用户名", example = "echo")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Schema(name = "password", title = "密码", example = "123456")
    private String password;
}
