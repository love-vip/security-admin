package com.vip.admin.config.web.controller;

import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.config.model.vo.RbacPermissionVo;
import com.vip.admin.config.service.RbacPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("auth/perm")
@Tag(name = "AuthPermController", description = "权限相关接口")
public class AuthPermController {

    private final RbacPermissionService permissionService;

    @GetMapping("menus")
    @Operation(method = "GET", description = "查询用户访问系统的可见菜单列表")
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RbacPermissionVo.class), examples = {@ExampleObject("")})
            }
    )
    public Wrapper<?> menus(Authentication authentication) {
        List<RbacPermissionVo> menus =  permissionService.query(authentication);
        return WrapMapper.success(menus);
    }

}
