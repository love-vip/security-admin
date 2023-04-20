package com.vip.admin.config.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vip.admin.commons.core.support.IService;
import com.vip.admin.config.model.domain.RbacUser;
import com.vip.admin.config.model.dto.RbacUserDto;
import com.vip.admin.config.model.dto.RbacUserModifyDto;
import com.vip.admin.config.model.query.UserPageQuery;
import com.vip.admin.config.model.vo.RbacUserVo;

import java.util.Optional;

/**
 * @author echo
 * @title: RbacUserService
 * @date 2023/3/16 15:47
 */
public interface RbacUserService extends IService<RbacUser> {

    String DEFAULT_PASSWORD = "123456";

    /**
     * <p>根据唯一用户名获取用户信息</p>
     * @param username 用户名
     * @return 用户
     */
    Optional<RbacUser> getByUsername(String username);

    /**
     * <p>根据唯一手机号获取用户信息</p>
     * @param mobile 手机号
     * @return 用户
     */
    Optional<RbacUser> getByMobile(String mobile);

    /**
     * <p>新增用户</p>
     * @param userDto 用户信息
     */
    void save(RbacUserDto userDto);

    /**
     * <p>修改用户信息</p>
     * @param userModifyDto 用户信息
     */
    void update(RbacUserModifyDto userModifyDto);

    /**
     * <p>锁定账户</p>
     * @param username 用户名
     */
    void locked(String username);

    /**
     * <p>解锁账户</p>
     * @param username 用户名
     */
    void unlock(String username);

    /**
     * <p>分页查询</p>
     * @param query 查询条件
     * @return 分页数据
     */
    IPage<RbacUserVo> selectByPage(UserPageQuery query);

    /**
     * <p>重置用户密码</p>
     * @param id 用户id
     */
    void reset(Long id);

    /**
     * <p>离职</p>
     * @param ids 用户id
     */
    void resign(Long[] ids);

}
