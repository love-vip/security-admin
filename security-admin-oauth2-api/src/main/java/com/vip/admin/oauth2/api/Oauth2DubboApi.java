package com.vip.admin.oauth2.api;

import com.vip.admin.commons.base.wrapper.Wrapper;

/**
 * <p></p>
 * @author echo
 * @version 1.0
 * @date 2023/3/30 15:21
 */
public interface Oauth2DubboApi {

    Wrapper<?> notifyRefreshAuthority(String system);
}
