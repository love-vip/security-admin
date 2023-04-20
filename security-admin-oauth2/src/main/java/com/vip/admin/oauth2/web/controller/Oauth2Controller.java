package com.vip.admin.oauth2.web.controller;

import cn.hutool.core.bean.BeanUtil;
import com.vip.admin.commons.base.wrapper.WrapMapper;
import com.vip.admin.commons.base.wrapper.Wrapper;
import com.vip.admin.oauth2.model.dto.Oauth2LoginDto;
import com.vip.admin.oauth2.model.dto.Oauth2PasswordDto;
import com.vip.admin.oauth2.model.dto.Oauth2SignDto;
import com.vip.admin.oauth2.model.dto.Oauth2VerifyDto;
import com.vip.admin.oauth2.model.vo.SignVo;
import com.vip.admin.oauth2.service.RbacUserService;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("oauth2")
@Tag(name = "Oauth2Controller", description = "登录界面相关接口")
public class Oauth2Controller {

    private final RbacUserService userService;

    @PostMapping("login")
    @Operation(method = "POST", description = "密码模式登录")
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SignVo.class), examples = {@ExampleObject("{\n" +
                            "    \"status\": \"ok\",\n" +
                            "    \"msg\": \"success\",\n" +
                            "    \"result\": {\n" +
                            "        \"sub\": \"echo\",\n" +
                            "        \"iss\": \"http://192.168.3.199:8001\",\n" +
                            "        \"token_type\": \"Bearer\",\n" +
                            "        \"client_id\": \"oauth2\",\n" +
                            "        \"access_token\": \"M67O_3YmMJmgKPYx8XGxGNGVOL3EnPNjWR1wR80OhWfNm2X13QqcRIrXcGMIkGlFT0g6yYRImMaCHcnWbmDYbOP2vU_rWO8JjabZLbeiHSY8r-Od3K7fwFdsGdOKN5xl\",\n" +
                            "        \"refresh_token\": \"GfAekNI9VW7xH00Oa3N-tOBywBXgS9gg6JYIEELVAVjliPTpXZ0wECEiSMmns1yLdp8kAUjz9USKIOn9Yw8IloSXfGPJgDewUwzlvwzHRHwcOyAg3xoFcA7rZZQzozZy\",\n" +
                            "        \"aud\": [\n" +
                            "            \"oauth2\"\n" +
                            "        ],\n" +
                            "        \"nbf\": \"1680852489.332000000\",\n" +
                            "        \"grant_type\": {\n" +
                            "            \"value\": \"password\"\n" +
                            "        },\n" +
                            "        \"scope\": \"/auth/update/password\",\n" +
                            "        \"exp\": \"1680895689.332000000\",\n" +
                            "        \"expires_in\": \"43199\",\n" +
                            "        \"iat\": \"1680852489.332000000\",\n" +
                            "        \"jti\": \"fafe166c-95cc-49fb-a368-7069d5961d70\"\n" +
                            "    }\n" +
                            "}")})
            }
    )
    public Wrapper<?> login(@RequestBody @Valid Oauth2LoginDto dto) {
        Map<String, Object> args = BeanUtil.beanToMap(dto, new HashMap<>(), false, true);
        return userService.sign(args);
    }

    @PostMapping("sign")
    @Operation(method = "POST", description = "授权码模式登录", hidden = true)
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SignVo.class), examples = {@ExampleObject("{\n" +
                            "    \"status\": \"ok\",\n" +
                            "    \"msg\": \"success\",\n" +
                            "    \"result\": {\n" +
                            "        \"sub\": null,\n" +
                            "        \"iss\": null,\n" +
                            "        \"token_type\": \"Bearer\",\n" +
                            "        \"client_id\": null,\n" +
                            "        \"access_token\": \"L2DEnBOKz9bmWxzKtsN4nzczfkORMHHnozhEId63vGbuVufWIXAfgS3c27V9d-NafkHT1LROb8WA4r6x6KFLMIZ59Fdp2-pYtji2BxYn1K0ARjogWq156XTd9eTiCG9h\",\n" +
                            "        \"refresh_token\": \"qL644uCzTIzbnOyr43y1jdiPShgaEwWK2gti3jG2XAMldcgXDYtq3Tf0dEV-YuM7MDGas00x3P-AlSCRY_Wl0tqUsu--RkAwv7SbjCdkS55-KCBPOvBcPTD-0vu28aZu\",\n" +
                            "        \"aud\": null,\n" +
                            "        \"nbf\": null,\n" +
                            "        \"grant_type\": null,\n" +
                            "        \"user_info\": null,\n" +
                            "        \"scope\": \"profile\",\n" +
                            "        \"exp\": null,\n" +
                            "        \"expires_in\": \"43199\",\n" +
                            "        \"iat\": null,\n" +
                            "        \"jti\": null\n" +
                            "    }\n" +
                            "}")})
            }
    )
    public Wrapper<?> sign(@RequestBody @Valid Oauth2SignDto dto) {
        Map<String, Object> args = BeanUtil.beanToMap(dto, new HashMap<>(), false, true);
        return userService.sign(args);
    }

    @PostMapping("verify")
    @Operation(method = "POST", description = "谷歌二次验证")
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SignVo.class), examples = {@ExampleObject("{\n" +
                            "    \"status\": \"ok\",\n" +
                            "    \"msg\": \"success\",\n" +
                            "    \"result\": {\n" +
                            "        \"sub\": \"echo\",\n" +
                            "        \"iss\": \"http://192.168.3.199:8001\",\n" +
                            "        \"token_type\": \"Bearer\",\n" +
                            "        \"client_id\": \"oauth2\",\n" +
                            "        \"access_token\": \"M67O_3YmMJmgKPYx8XGxGNGVOL3EnPNjWR1wR80OhWfNm2X13QqcRIrXcGMIkGlFT0g6yYRImMaCHcnWbmDYbOP2vU_rWO8JjabZLbeiHSY8r-Od3K7fwFdsGdOKN5xl\",\n" +
                            "        \"refresh_token\": \"GfAekNI9VW7xH00Oa3N-tOBywBXgS9gg6JYIEELVAVjliPTpXZ0wECEiSMmns1yLdp8kAUjz9USKIOn9Yw8IloSXfGPJgDewUwzlvwzHRHwcOyAg3xoFcA7rZZQzozZy\",\n" +
                            "        \"aud\": [\n" +
                            "            \"oauth2\"\n" +
                            "        ],\n" +
                            "        \"nbf\": \"1680852489.332000000\",\n" +
                            "        \"grant_type\": {\n" +
                            "            \"value\": \"google\"\n" +
                            "        },\n" +
                            "        \"user_info\": {\n" +
                            "            \"id\": 2,\n" +
                            "            \"mobile\": \"13812348888\",\n" +
                            "            \"email\": \"10@gmail.com\",\n" +
                            "            \"realName\": \"七七\",\n" +
                            "            \"initial\": false,\n" +
                            "            \"bind\": false,\n" +
                            "            \"qrCodeUrl\": \"https://www.google.com/chart?chs=200x200&cht=qr&chl=otpauth://totp/oidc:echo?secret=S77BJIKKI3N33P6FFQGJNIQIEZA4LZ3Q&issuer=odic\"\n" +
                            "        },\n" +
                            "        \"scope\": \"/auth/update/password\",\n" +
                            "        \"exp\": \"1680895689.332000000\",\n" +
                            "        \"expires_in\": \"43199\",\n" +
                            "        \"iat\": \"1680852489.332000000\",\n" +
                            "        \"jti\": \"fafe166c-95cc-49fb-a368-7069d5961d70\"\n" +
                            "    }\n" +
                            "}")})
            }
    )
    public Wrapper<?> verify(@RequestBody @Valid Oauth2VerifyDto dto) {
        Map<String, Object> args = BeanUtil.beanToMap(dto, new HashMap<>(), false, true);
        return userService.sign(args);
    }
    @PostMapping("password")
    @Operation(method = "POST", description = "初次登录修改密码")
    @ApiResponse(responseCode = "200", description = "success",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SignVo.class), examples = {@ExampleObject("{\n" +
                            "    \"status\": \"ok\",\n" +
                            "    \"msg\": \"success\",\n" +
                            "}")})
            }
    )
    public Wrapper<?> password(@RequestBody @Valid Oauth2PasswordDto passwordDto) {
        userService.updatePassword(passwordDto);
        return WrapMapper.ok();
    }

}
