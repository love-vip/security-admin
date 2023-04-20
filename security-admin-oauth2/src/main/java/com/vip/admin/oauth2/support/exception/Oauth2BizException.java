package com.vip.admin.oauth2.support.exception;

import com.vip.admin.commons.base.exception.BusinessException;
import com.vip.admin.oauth2.support.enums.Oauth2ApiCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author echo
 * @date 2023/3/28 15:54
 */
@Slf4j
public class Oauth2BizException extends BusinessException {

    private static final long serialVersionUID = 5926842866052951590L;

    /**
     * Instantiates a new Mdc rpc exception.
     */
    public Oauth2BizException() {
    }

    /**
     * Instantiates a new Mdc rpc exception.
     *
     * @param code      the code
     * @param msgFormat the msg format
     * @param args      the args
     */
    public Oauth2BizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== MdcRpcException, code:" + this.code + ", message:" + super.getMessage());

    }

    /**
     * Instantiates a new Mdc rpc exception.
     *
     * @param code the code
     * @param msg  the msg
     */
    public Oauth2BizException(int code, String msg) {
        super(code, msg);
        log.info("<== MdcRpcException, code:" + this.code + ", message:" + super.getMessage());
    }

    /**
     * Instantiates a new Mdc rpc exception.
     *
     * @param codeEnum the code enum
     */
    public Oauth2BizException(Oauth2ApiCode codeEnum, Object... args) {
        super(codeEnum.getCode(), codeEnum.getMessage());
        log.info("<== MdcRpcException, code:" + this.code + ", message:" + super.getMessage());
    }

}