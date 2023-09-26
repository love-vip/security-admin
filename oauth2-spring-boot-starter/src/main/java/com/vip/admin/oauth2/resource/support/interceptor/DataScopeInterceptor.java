package com.vip.admin.oauth2.resource.support.interceptor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ParameterUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.api.SecurityDubboService;
import com.vip.admin.oauth2.request.SecurityConditionRequest;
import com.vip.admin.oauth2.resource.support.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

/**
 * @author echo
 * @version 1.0
 * @date 2023/9/25 16:53
 */
@Slf4j
@RequiredArgsConstructor
public class DataScopeInterceptor implements InnerInterceptor {

    private final SecurityDubboService securityDubboService;

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        IPage<?> page = ParameterUtils.findPage(parameter).orElse(null);
        if (null == page) return;
        //如果用户有配置查询条件
        Wrapper<String> wrapper = securityDubboService.attain(new SecurityConditionRequest(SecurityUtils.getUser().getId(),  ms.getId()));
        if(wrapper.success() && wrapper.getData() != null){
            // 执行的SQL语句
            String originalSql = boundSql.getSql();
            // SQL语句的参数
            String wrapSql = "select * from (" + originalSql + ") temp_data_scope ";
            String whereSql = wrapper.success() && wrapper.getData() != null ? wrapper.getData() : " ";
            String newSql = wrapSql + whereSql;
            PluginUtils.MPBoundSql mpBoundSql = PluginUtils.mpBoundSql(boundSql);
            List<ParameterMapping> mappings = mpBoundSql.parameterMappings();
            //数据权限～「行列过滤」处理后的新sql
            mpBoundSql.sql(newSql);
            mpBoundSql.parameterMappings(mappings);
        }
    }

}