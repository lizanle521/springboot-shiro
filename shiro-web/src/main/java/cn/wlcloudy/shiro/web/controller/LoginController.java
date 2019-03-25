package cn.wlcloudy.shiro.web.controller;

import cn.wlcloudy.shiro.config.Endpoints;
import cn.wlcloudy.shiro.entity.dto.JsonResult;
import cn.wlcloudy.shiro.entity.dto.ResultCode;
import cn.wlcloudy.shiro.entity.po.User;
import cn.wlcloudy.shiro.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName LoginController
 * @Description 登录/登出控制器
 * @Author wangxiong
 * @Date 2018/10/26 9:56
 * @Version 1.0
 **/
@Api(tags = "登录/登出接口")
@RestController
@RequestMapping(Endpoints.API_V1)
public class LoginController{
    @Resource
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public JsonResult login(String username, String password)
    {
        /*
            1、验证用户是否已经登录
            2.1、已登录则直接返回
            2.2、未登录则登录验证
         */
        Subject currentUser = SecurityUtils.getSubject();
        User user = (User) currentUser.getPrincipal();
        if(user != null) {
            if (!StringUtils.equals(user.getEmail(),username)){
                currentUser.logout();
            }else {
                return JsonResult.OK(ResultCode.SUCCESS.code());
            }
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            currentUser.login(token);
            return JsonResult.OK();
        }catch( UnknownAccountException uae ) {
            return JsonResult.Err(ResultCode.UNKNOWN_ACCOUNT);
        } catch ( IncorrectCredentialsException ice ) {
            return JsonResult.Err(ResultCode.INCORRECT_CREDENTIALS);
        } catch ( LockedAccountException lae ) {
            return JsonResult.Err(ResultCode.LOCKED_ACCOUNT);
        } catch ( ExcessiveAttemptsException eae ) {
            return JsonResult.Err(ResultCode.EXCESSIVE_ATTEMPTS);
        } catch ( AuthenticationException ae ) {
            return JsonResult.Err(ResultCode.LOGIN_FAIL);
        }
    }

    @ApiOperation("用户登出")
    @GetMapping(Endpoints.LOGOUT)
    public JsonResult logout()
    {
        Subject currentUser = SecurityUtils.getSubject();
        Object principal = currentUser.getPrincipal();
        if (principal != null) {
            currentUser.logout();
        }
        return JsonResult.OK();
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public JsonResult register(User param){
        String hashAlgorithmName = "MD5";
        Object crdentials = param.getPassword();
        Object salt = param.getUsername();
        int hashIterations = 1;
        // 这里这么加密的原因是 解密的时候调用的HashedCredentialsMatcher.doCredentialsMatch
        SimpleHash result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);

        User user = new User();
        user.setUsername(param.getUsername());
        user.setPassword(result.toString());
        user.setEmail(param.getEmail());
        user.setNickName(param.getNickName());
        user.setRealName(param.getRealName());
        // 用user service进行注册
        //user = userService.registerUser(user);
        return JsonResult.OK();
    }
}
