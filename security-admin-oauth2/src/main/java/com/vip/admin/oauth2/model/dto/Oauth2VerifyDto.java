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
@Schema(title = "VerifyDto", description = "验证请求对象")
public class Oauth2VerifyDto {

    @Schema(name = "client_id", title = "客户端id", hidden = true, defaultValue = "client")
    private String client_id = "client";
    @Schema(name = "client_secret", title = "客户端密钥", hidden = true,  defaultValue = "secret")
    private String client_secret = "secret";
    @Schema(name = "grant_type", title = "授权类型", hidden = true,  defaultValue = "google")
    private String grant_type = "google";
    @Schema(name = "access_token", title = "访问令牌", example = "QFJdyCh-lnz")
    @NotBlank(message = "访问令牌不能为空")
    private String access_token;
    @NotBlank(message = "验证码不能为空")
    @Schema(name = "code", title = "验证码", example = "078565")
    private String code;
}
