package com.vip.admin.config.web.controller;

import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.config.model.dto.RbacUserDto;
import com.vip.admin.config.model.dto.RbacUserModifyDto;
import com.vip.admin.config.model.query.UserPageQuery;
import com.vip.admin.config.model.vo.RbacUserVo;
import com.vip.admin.config.service.RbacUserService;
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

import javax.validation.Valid;

/**
 * @author echo
 * @version 1.0
 * @date 2023/4/10 18:45
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("auth/user")
@Tag(name = "AuthUserController", description = "用户相关接口")
public class AuthUserController {

    private final RbacUserService userService;

    @PostMapping("save")
    @Operation(method = "POST", description = "新增用户")
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Wrapper.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> save(@RequestBody @Valid RbacUserDto userDto) {
        userService.save(userDto);
        return WrapMapper.ok();
    }

    @PostMapping("edit")
    @Operation(method = "POST", description = "更新用户")
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Wrapper.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> edit(@RequestBody @Valid RbacUserModifyDto userModifyDto) {
        userService.update(userModifyDto);
        return WrapMapper.ok();
    }

    @GetMapping("page/query")
    @Operation(method = "GET", description = "分页查询用户")
    @Parameter(name = "query", description = "查询条件", required = true, in = ParameterIn.DEFAULT)
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RbacUserVo.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> selectByPage(UserPageQuery query) {
        return WrapMapper.success(userService.selectByPage(query));
    }

    @GetMapping("reset/{id}")
    @Operation(method = "GET", description = "重置密码")
    @Parameter(name = "id", description = "用户id", required = true, in = ParameterIn.PATH)
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RbacUserVo.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> reset(@PathVariable("id") Long id) {
        userService.reset(id);
        return WrapMapper.ok();
    }

    @PostMapping("resign")
    @Operation(method = "POST", description = "离职")
    @Parameter(name = "ids", description = "用户id", required = true, in = ParameterIn.DEFAULT)
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RbacUserVo.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> resign(@RequestBody Long[] ids) {
        userService.resign(ids);
        return WrapMapper.ok();
    }
}
