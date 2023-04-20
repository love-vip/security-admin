package com.vip.admin.config.assist.exception;

import com.vip.admin.commons.base.exception.BusinessException;
import com.vip.admin.config.assist.enums.ConfigApiCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author echo
 * @date 2023/3/28 15:54
 */
@Slf4j
public class ConfigBizException extends BusinessException {

    private static final long serialVersionUID = 5926842866052951590L;

    /**
     * Instantiates a new Mdc rpc exception.
     */
    public ConfigBizException() {
    }

    /**
     * Instantiates a new Mdc rpc exception.
     *
     * @param code      the code
     * @param msgFormat the msg format
     * @param args      the args
     */
    public ConfigBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== MdcRpcException, code:" + this.code + ", message:" + super.getMessage());

    }

    /**
     * Instantiates a new Mdc rpc exception.
     *
     * @param code the code
     * @param msg  the msg
     */
    public ConfigBizException(int code, String msg) {
        super(code, msg);
        log.info("<== MdcRpcException, code:" + this.code + ", message:" + super.getMessage());
    }

    /**
     * Instantiates a new Mdc rpc exception.
     *
     * @param codeEnum the code enum
     */
    public ConfigBizException(ConfigApiCode codeEnum, Object... args) {
        super(codeEnum.getCode(), codeEnum.getMessage(), args);
        log.info("<== MdcRpcException, code:" + this.code + ", message:" + super.getMessage());
    }

}