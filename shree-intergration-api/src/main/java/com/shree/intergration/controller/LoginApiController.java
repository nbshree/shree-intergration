package com.shree.intergration.controller;

import com.shree.intergration.common.base.lang.convert.ObjectConverter;
import com.shree.intergration.common.web.api.ApiStatus;
import com.shree.intergration.common.web.api.ApiStatusResult;
import com.shree.intergration.common.web.rest.RestResult;
import com.shree.intergration.model.custom.entity.StdUserOutInfo;
import com.shree.intergration.model.custom.serivce.UserService;
import com.shree.intergration.model.major.entity.IdpUserInfo;
import com.shree.intergration.util.CommonFailCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "公开登录接口")
@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/api/open", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginApiController {

    @Resource
    UserService userService;

    @ApiOperation(value = "对外用户登录接口", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userPwd", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public RestResult<StdUserOutInfo> doUserLogin(@RequestParam String userName, @RequestParam String userPwd) {
        try {
            IdpUserInfo userInfo = this.userService.webUserLogin(userName, userPwd);
            if (null != userInfo) {
                StdUserOutInfo user = ObjectConverter.toEntity(userInfo, StdUserOutInfo.class);
                // Shiro登录
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);
                subject.login(token);
                user.setSessionId(subject.getSession().getId().toString());
                return RestResult.createSuccessResult(ApiStatus.OK.getCode(), user);
            } else {
                return RestResult.createFailedResult(CommonFailCode.LOGIN_FAILED, null, CommonFailCode.Message.LOGIN_FAILED);
            }
        } catch (Exception ex) {
            log.error("用户登录接口错误！", ex);
            return ApiStatusResult.ERROR;
        }
    }
}
