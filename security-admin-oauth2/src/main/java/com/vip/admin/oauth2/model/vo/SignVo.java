package com.vip.admin.oauth2.model.vo;

import com.vip.admin.oauth2.support.security.core.principal.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/6 19:15
 */
@Data
@Schema(title = "SignVo", description = "谷歌验证响应对象")
public class SignVo {
    @Schema(name = "sub", title = "主题", example = "echo")
    private String sub;
    @Schema(name = "iss", title = "发行人", example = "http://192.168.3.199:8001")
    private String iss;
    @Schema(name = "token_type", title = "token类型", example = "Bearer")
    private String token_type;
    @Schema(name = "client_id", title = "客户端ID", example = "oauth2")
    private String client_id;
    @Schema(name = "access_token", title = "请求token", example = "QFJdyCh-lnz")
    private String access_token;
    @Schema(name = "refresh_token", title = "刷新token", example = "ceBIFdtIa-MGWcB91T")
    private String refresh_token;
    @Schema(name = "aud", title = "用户", example = "echo")
    private List<String> aud;
    @Schema(name = "nbf", title = "在此之前不可用", example = "1680842159.107000000")
    private String nbf;
    @Schema(name = "grant_type", title = "授权类型", example = "")
    private GrantType grant_type;
    @Data
    public static class GrantType{
        @Schema(name = "value", title = "授权类型", example = "google")
        private String value;
    }
    @Schema(name = "user_info", title = "用户对象", example = "")
    private UserInfo user_info;
    @Schema(name = "scope", title = "授权范围", example = "/auth/dept")
    private String scope;
    @Schema(name = "exp", title = "到期时间", example = "1680885359.107000000")
    private String exp;
    @Schema(name = "expires_in", title = "过期时间「单位秒」", example = "43199")
    private String expires_in;
    @Schema(name = "iat", title = "发布时间", example = "1680842159.107000000")
    private String iat;
    @Schema(name = "jti", title = "JWT ID用于标识该JWT", example = "215b32e2-d48d-42a3-aa52-1fd1032cfced")
    private String jti;

}
