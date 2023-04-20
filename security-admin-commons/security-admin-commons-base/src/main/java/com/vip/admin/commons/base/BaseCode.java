package com.vip.admin.commons.base;

import com.vip.admin.commons.base.api.ApiCode;
import lombok.RequiredArgsConstructor;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/19 00:23
 */
@RequiredArgsConstructor
public enum BaseCode implements ApiCode {

    // 统一成功
    SUCCESS(200, "成功!"),

    // 请求错误
    ERROR_PARAMETER(400, "参数有错误!"),
    ERROR_AUTHORIZE(401, "请重新登录!"),
    ERROR_FORBIDDEN(403, "无操作权限!"),
    ERROR_NOTFOUND(404, "资源不存在!"),

    // 系统错误
    SERVER_ERROR(500, "系统异常，请稍后重试!"),
    SERVER_LIMIT(503, "请求频繁，请休息片刻!"),
    SERVER_TIMEOUT(504, "网关超时!")

    ;

    private final int code;
    private final String message;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}