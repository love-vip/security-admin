package com.vip.admin.oauth2.service.impl;

import com.vip.admin.oauth2.mapper.OperationLogMapper;
import com.vip.admin.oauth2.model.domain.OperationLog;
import com.vip.admin.oauth2.request.OperationLogRequest;
import com.vip.admin.oauth2.service.OperationLogService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/21 17:27
 */
@Service
@RequiredArgsConstructor
public class OperationLogServiceImpl implements OperationLogService {

    private final OperationLogMapper operationLogMapper;

    @Override
    public void save(OperationLogRequest request) {
        OperationLog operationLog = OperationLogConverter.INSTANCE.convert(request);
        operationLogMapper.insert(operationLog);
    }

    @Mapper
    public interface OperationLogConverter {
        OperationLogConverter INSTANCE = Mappers.getMapper(OperationLogConverter.class);

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "creator", ignore = true)
        @Mapping(target = "createTime", ignore = true)
        @Mapping(target = "updateTime", ignore = true)
        @Mapping(target = "lastOperator", ignore = true)
        OperationLog convert(OperationLogRequest request);
    }
}
