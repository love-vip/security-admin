package com.vip.admin.config.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vip.admin.commons.base.BaseQuery;
import com.vip.admin.commons.core.support.IService;
import com.vip.admin.config.model.domain.RbacSystem;
import com.vip.admin.config.model.dto.RbacSystemDto;
import com.vip.admin.config.model.dto.RbacSystemModifyDto;
import com.vip.admin.config.model.vo.RbacSystemVo;

/**
 * @author echo
 * @date 2023/4/3 15:47
 */
public interface RbacSystemService extends IService<RbacSystem> {

    /**
     * <p>新增系统</p>
     * @param systemDto 部门信息
     */
    void save(RbacSystemDto systemDto);

    /**
     * <p>修改系统信息</p>
     * @param systemModifyDto 部门信息
     */
    void update(RbacSystemModifyDto systemModifyDto);

    IPage<RbacSystemVo> selectByPage(BaseQuery query);

}
