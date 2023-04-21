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
@Schema(title = "Oauth2SignDto", description = "授权码模式登录请求对象")
public class Oauth2SignDto {
    @Schema(name = "client_id", title = "客户端id", hidden = true, defaultValue = "client")
    private String client_id = "client";
    @Schema(name = "client_secret", title = "客户端密钥", hidden = true,  defaultValue = "secret")
    private String client_secret = "secret";
    @Schema(name = "grant_type", title = "授权类型", hidden = true,  defaultValue = "authorization_code")
    private String grant_type = "authorization_code";
    @NotBlank(message = "授权码不能为空")
    @Schema(name = "code", title = "授权码", example = "FOCnI84JnCXB8l6FB")
    private String code;
    @NotBlank(message = "重定向地址不能为空")
    @Schema(name = "redirect_uri", title = "重定向地址", example = "https://www.baidu.com")
    private String redirect_uri;
}
