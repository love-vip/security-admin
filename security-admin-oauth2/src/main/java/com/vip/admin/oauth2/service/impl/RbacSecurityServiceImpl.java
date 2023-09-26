package com.vip.admin.oauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.vip.admin.commons.core.support.Objects;
import com.vip.admin.oauth2.mapper.RbacMapperColumnMapper;
import com.vip.admin.oauth2.mapper.RbacMapperSearchMapper;
import com.vip.admin.oauth2.mapper.RbacMappingMapperMapper;
import com.vip.admin.oauth2.mapper.RbacMappingUserConditionMapper;
import com.vip.admin.oauth2.model.domain.RbacMapperColumn;
import com.vip.admin.oauth2.model.domain.RbacMapperSearch;
import com.vip.admin.oauth2.model.domain.RbacMappingMapper;
import com.vip.admin.oauth2.model.domain.RbacMappingUserCondition;
import com.vip.admin.oauth2.request.SecurityConditionRequest;
import com.vip.admin.oauth2.request.SecurityDataScopeRequest;
import com.vip.admin.oauth2.service.RbacSecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author echo
 * @version 1.0
 * @date 2023/9/25 19:04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RbacSecurityServiceImpl implements RbacSecurityService {

    private final RbacMapperColumnMapper colMapper;
    private final RbacMapperSearchMapper searchMapper;
    private final RbacMappingMapperMapper mappingMapperMapper;
    private final RbacMappingUserConditionMapper conditionMapper;

    @Override
    public String attain(SecurityConditionRequest request) {
        LambdaQueryWrapper<RbacMappingUserCondition> wrapper = new LambdaQueryWrapper<RbacMappingUserCondition>().eq(RbacMappingUserCondition::getUserId, request.getUserId()).eq(RbacMappingUserCondition::getMapperId, request.getMapperId());
        RbacMappingUserCondition mappingUserCondition = conditionMapper.selectOne(wrapper);
        return Optional.ofNullable(mappingUserCondition).map(RbacMappingUserCondition::getCondition).orElse(null);
    }

    @Override
    public void sync(List<SecurityDataScopeRequest> request) {
        request.forEach(req -> {
            LambdaQueryWrapper<RbacMappingMapper> mapperWrapper = new LambdaQueryWrapper<RbacMappingMapper>().eq(RbacMappingMapper::getMapperId, req.getMapperId());
            RbacMappingMapper mappingMapper = mappingMapperMapper.selectOne(mapperWrapper);
            String mapperIds = String.join("|", req.getMapperIds());
            if(Objects.isEmpty(mappingMapper)){
                mappingMapperMapper.insert(new RbacMappingMapper(req.getMapperId(), mapperIds, req.isAop()));
            }else {
                mappingMapper.setCamel(req.isCamel());
                mappingMapper.setMapperIds(mapperIds);
                mappingMapperMapper.updateById(mappingMapper);
            }
            LambdaQueryWrapper<RbacMapperColumn> columnWrapper = new LambdaQueryWrapper<RbacMapperColumn>().eq(RbacMapperColumn::getMapperId, req.getMapperId());
            List<String> columns = colMapper.selectList(columnWrapper).stream().map(RbacMapperColumn::getActualColumn).toList();
            LambdaQueryWrapper<RbacMapperSearch> searchWrapper = new LambdaQueryWrapper<RbacMapperSearch>().eq(RbacMapperSearch::getMapperId, req.getMapperId());
            List<String> searches = searchMapper.selectList(searchWrapper).stream().map(RbacMapperSearch::getActualBox).toList();
            Arrays.stream(req.getMapperIds()).forEach(mapperId -> {
                req.getColumns().stream().filter(column -> !columns.contains(column.getActualColumn())).forEach(column -> {
                    RbacMapperColumn column_ = new RbacMapperColumn();
                    column_.setMapperId(mapperId);
                    column_.setActualColumn(column.getActualColumn());
                    column_.setDisplayColumn(column.getDisplayColumn());
                    column_.setChecked(column.isChecked());
                    column_.setMapping(String.join("|", column.getMapping()));
                    column_.setMulti(column.isMulti());
                    column_.setMask(column.isMask());
                    column_.setType(column.getType());
                    column_.setFilter(column.isFilter());
                    colMapper.insert(column_);
                });
                req.getColumns().stream().filter(column -> columns.contains(column.getActualColumn())).forEach(column -> {
                    LambdaQueryWrapper<RbacMapperColumn> wrapper = new LambdaQueryWrapper<RbacMapperColumn>().eq(RbacMapperColumn::getMapperId, req.getMapperId()).eq(RbacMapperColumn::getActualColumn, column.getActualColumn());
                    RbacMapperColumn column_ = colMapper.selectOne(wrapper);
                    column_.setDisplayColumn(column.getDisplayColumn());
                    column_.setChecked(column.isChecked());
                    column_.setMask(column.isMask());
                    column_.setType(column.getType());
                    column_.setFilter(column.isFilter());
                    colMapper.updateById(column_);
                });
                req.getSearches().stream().filter(search -> !searches.contains(search.getActualBox())).forEach(search -> {
                    RbacMapperSearch search_ = new RbacMapperSearch();
                    search_.setMapperId(mapperId);
                    search_.setActualBox(search.getActualBox());
                    search_.setDisplayBox(search.getDisplayBox());
                    search_.setChecked(search.isChecked());
                    search_.setMapping(String.join("|", search.getMapping()));
                    search_.setMulti(search.isMulti());
                    searchMapper.insert(search_);
                });
            });
        });
    }
}
