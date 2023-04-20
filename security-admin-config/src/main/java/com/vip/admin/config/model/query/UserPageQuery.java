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
 * @title: UserPageQuery
 * @date 2023/3/16 13:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "UserPageQuery", description = "用户分页请求对象")
public class UserPageQuery extends BaseQuery {
    @Schema(title = "用户名", description = "用户名（可模糊查询）", example = "echo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String username;

    @Schema(name = "enabled", title = "在职状态", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer enabled;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(name = "beginTime", title = "开始时间", example = "2023-05-21 00:00:00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(name = "beginTime", title = "开始时间", example = "2023-05-21 00:00:00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime endTime;
}
