package com.vip.admin.commons.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author echo
 * @title: LoginAuthDto
 * @date 2023/3/16 15:27
 */
@Schema
@Data
public class BaseVo implements Serializable {
	
	private static final long serialVersionUID = -1695850022460957581L;

	@NotNull(message = "主键id不能为空")
	@Schema(name = "id", title = "主键id", example = "1")
	private Long id;

	/**
	 * 创建人
	 */
	@NotBlank(message = "创建人不能为空")
	@Schema(name = "creator", title = "创建人", example = "admin")
	private String creator;

	/**
	 * 创建时间
	 */
	@NotNull(message = "创建时间不能为空")
	@Schema(name = "createTime", title = "创建时间", example = "2023-05-21 00:00:00")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createTime;

	/**
	 * 最近操作人
	 */
	@Schema(name = "lastOperator", title = "最近操作人", example = "admin", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String lastOperator;

	/**
	 * 更新时间
	 */
	@Schema(name = "updateTime", title = "更新时间", example = "2023-05-21 00:00:00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime updateTime;
}
