package com.vip.admin.oauth2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/6 19:10
 */
@Data
@Schema(title = "LogoutDto", description = "token销毁请求对象")
public class Oauth2LogoutDto {

    @Schema(name = "client_id", title = "客户端id", hidden = true, defaultValue = "client")
    private String client_id = "client";
    @Schema(name = "client_secret", title = "客户端密钥", hidden = true,  defaultValue = "secret")
    private String client_secret = "secret";
    @Schema(name = "token", title = "访问令牌", example = "QFJdyCh-lnz")
    @NotBlank(message = "访问令牌不能为空")
    private String token;
}
