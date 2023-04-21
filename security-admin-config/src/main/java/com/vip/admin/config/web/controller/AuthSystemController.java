package com.vip.admin.config.web.controller;

import com.vip.admin.commons.base.BaseQuery;
import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.config.model.dto.RbacSystemDto;
import com.vip.admin.config.model.dto.RbacSystemModifyDto;
import com.vip.admin.config.model.vo.RbacSystemVo;
import com.vip.admin.config.service.RbacSystemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/12 10:25
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("auth/system")
@Tag(name = "AuthSystemController", description = "系统相关接口")
public class AuthSystemController {

    private final RbacSystemService systemService;

    @PostMapping("save")
    @Operation(method = "POST", description = "新增系统")
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Wrapper.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> save(@RequestBody @Valid RbacSystemDto systemDto) {
        systemService.save(systemDto);
        return WrapMapper.ok();
    }

    @PostMapping("edit")
    @Operation(method = "POST", description = "更新系统")
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Wrapper.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> edit(@RequestBody @Valid RbacSystemModifyDto systemModifyDto) {
        systemService.update(systemModifyDto);
        return WrapMapper.ok();
    }

    @GetMapping("page/query")
    @Operation(method = "GET", description = "分页查询系统")
    @Parameter(name = "query", description = "查询条件", required = true, in = ParameterIn.DEFAULT)
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RbacSystemVo.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> selectByPage(BaseQuery query) {
        return WrapMapper.success(systemService.selectByPage(query));
    }

}
