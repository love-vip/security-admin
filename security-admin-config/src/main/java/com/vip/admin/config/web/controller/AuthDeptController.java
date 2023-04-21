package com.vip.admin.config.web.controller;

import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.config.model.dto.RbacDeptDto;
import com.vip.admin.config.model.dto.RbacDeptModifyDto;
import com.vip.admin.config.model.query.DeptPageQuery;
import com.vip.admin.config.model.vo.RbacDeptVo;
import com.vip.admin.config.model.vo.RbacMembersVo;
import com.vip.admin.config.service.RbacDeptService;
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
@RequestMapping("auth/dept")
@Tag(name = "AuthDeptController", description = "部门相关接口")
public class AuthDeptController {

    private final RbacDeptService deptService;

    @PostMapping("save")
    @Operation(method = "POST", description = "新增部门")
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Wrapper.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> save(@RequestBody @Valid RbacDeptDto deptDto) {
        deptService.save(deptDto);
        return WrapMapper.ok();
    }

    @PostMapping("edit")
    @Operation(method = "POST", description = "更新部门")
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Wrapper.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> edit(@RequestBody @Valid RbacDeptModifyDto deptModifyDto) {
        deptService.update(deptModifyDto);
        return WrapMapper.ok();
    }

    @GetMapping("page/query")
    @Operation(method = "GET", description = "分页查询部门")
    @Parameter(name = "query", description = "查询条件", required = true, in = ParameterIn.DEFAULT)
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RbacDeptVo.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> selectByPage(DeptPageQuery query) {
        return WrapMapper.success(deptService.selectByPage(query));
    }

    @GetMapping("page/members")
    @Operation(method = "GET", description = "分页查询人员清单")
    @Parameter(name = "pageNum", description = "当前页", required = true, example = "1", in = ParameterIn.DEFAULT)
    @Parameter(name = "pageSize", description = "每页条数", required = true, example = "10",  in = ParameterIn.DEFAULT)
    @Parameter(name = "deptId", description = "部门id", required = true, example = "1",  in = ParameterIn.DEFAULT)
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RbacMembersVo.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> selectByPage(int pageNum, int pageSize, int deptId) {
        return WrapMapper.success(deptService.selectByPage(pageNum, pageSize, deptId));
    }
}
