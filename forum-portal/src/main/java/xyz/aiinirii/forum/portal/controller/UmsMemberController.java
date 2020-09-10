package xyz.aiinirii.forum.portal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.aiinirii.forum.common.api.CommonResult;
import xyz.aiinirii.forum.model.UmsMember;
import xyz.aiinirii.forum.portal.domain.UmsMemberLoginParam;
import xyz.aiinirii.forum.portal.domain.UmsMemberParam;
import xyz.aiinirii.forum.portal.domain.UmsTokenParam;
import xyz.aiinirii.forum.portal.domain.UpdateMemberPasswordParam;
import xyz.aiinirii.forum.portal.service.UmsMemberService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 关于用户的Controller类
 *
 * @author AIINIRII
 */
@RestController
@RequestMapping("/sso")
@Api(value = "UmsMemberController", tags = {"用户账号有关操作"})
public class UmsMemberController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberController.class);
    private UmsMemberService umsMemberService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public void setUmsMemberService(UmsMemberService umsMemberService) {
        this.umsMemberService = umsMemberService;
    }

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public CommonResult<UmsMember> register(@RequestBody UmsMemberParam umsMemberParam) {
        LOGGER.info("register user: " + umsMemberParam);
        UmsMember register = umsMemberService.register(umsMemberParam);
        return CommonResult.success(register, "注册成功");
    }

    @ApiOperation("会员登录")
    @PostMapping("/login")
    public CommonResult<UmsTokenParam> login(@RequestBody UmsMemberLoginParam memberLoginParam) {
        LOGGER.info("login user: " + memberLoginParam);
        String token = umsMemberService.login(memberLoginParam.getUsername(), memberLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        UmsTokenParam tokenMap = new UmsTokenParam();
        tokenMap.setToken(token);
        tokenMap.setTokenHead(tokenHead);
        return CommonResult.success(tokenMap, "登录成功");
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public CommonResult<?> updatePassword(@RequestBody UpdateMemberPasswordParam memberPasswordParam) {

        if (umsMemberService.updatePassword(memberPasswordParam) == 1) {
            return CommonResult.success(null, "密码修改成功");
        } else {
            return CommonResult.failed("密码修改失败");
        }

    }

    @ApiOperation("刷新Token")
    @PostMapping("/refreshToken")
    public CommonResult<UmsTokenParam> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = umsMemberService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已过期");
        }
        UmsTokenParam tokenParam = new UmsTokenParam();
        tokenParam.setToken(refreshToken);
        tokenParam.setTokenHead(tokenHead);
        return CommonResult.success(tokenParam, "成功更新token");
    }

    @ApiOperation("获取目前用户信息")
    @GetMapping("/info")
    public CommonResult<UmsMember> getInfo(@ApiParam(hidden = true) Principal principal) {
        String username = principal.getName();
        if (username != null) {
            UmsMember umsMember = umsMemberService.getMemberByUsername(username);
            umsMember.setPassword(null);
            return CommonResult.success(umsMember);
        } else {
            return CommonResult.unauthorized(null);
        }
    }

    @ApiOperation("注销用户")
    @PostMapping("/delete/{id}")
    public CommonResult<?> deleteUmsMember(@PathVariable Long id,
                                           @RequestBody UmsMemberLoginParam umsMemberLoginParam,
                                           Principal principal) {
        String username = principal.getName();
        if (username != null) {
            UmsMember umsMember = umsMemberService.getMemberByUsername(username);
            String login = umsMemberService.login(umsMemberLoginParam.getUsername(), umsMemberLoginParam.getPassword());
            if (umsMember.getId().equals(id) && !StringUtils.isEmpty(login)) {
                int delete = umsMemberService.delete(id);
                return delete == 1 ? CommonResult.success(null) : CommonResult.failed("无法验证身份，删除失败");
            }
        }
        return CommonResult.failed("删除失败");
    }

    @ApiOperation("更新用户信息(不要在此修改密码！！)")
    @PostMapping("/update/{id}")
    public CommonResult<?> updateUmsMember(@PathVariable Long id,
                                           @RequestBody UmsMemberParam umsMemberParam,
                                           Principal principal) {
        String username = principal.getName();
        if (username != null) {
            UmsMember umsMember = umsMemberService.getMemberByUsername(username);
            if (umsMember.getId().equals(id)) {
                umsMember.setUsername(umsMemberParam.getUsername());
                umsMember.setPhone(umsMemberParam.getPhone());
                umsMember.setEmail(umsMemberParam.getEmail());
                umsMember.setGender(umsMemberParam.getGender());
                umsMember.setNickname(umsMemberParam.getNickName());
                int update = umsMemberService.update(umsMember);
                if (update == 1) {
                    return CommonResult.success(null);
                } else {
                    return CommonResult.failed("更新失败");
                }
            } else {
                return CommonResult.unauthorized("无权更新此用户");
            }
        }
        return CommonResult.failed("更新失败");
    }

}