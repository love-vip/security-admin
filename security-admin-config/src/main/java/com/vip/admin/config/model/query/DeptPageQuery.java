package com.vip.admin.config.model.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vip.admin.commons.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/12 10:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "DeptPageQuery", description = "用户分页请求对象")
public class DeptPageQuery extends BaseQuery {
    @Schema(title = "部门名称", description = "部门名称（可模糊查询）", example = "技术中心", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String deptName;

    @Schema(name = "level", title = "部门分级", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer level;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(name = "beginTime", title = "开始时间", example = "2023-05-21 00:00:00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(name = "beginTime", title = "开始时间", example = "2023-05-21 00:00:00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime endTime;
}
