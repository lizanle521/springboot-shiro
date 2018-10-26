package cn.wlcloudy.shiro.web.controller;

import cn.wlcloudy.shiro.config.Endpoints;
import cn.wlcloudy.shiro.entity.po.User;
import cn.wlcloudy.shiro.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Description 用户控制器
 * @Author wangxiong
 * @Date 2018/10/25 11:46
 * @Version 1.0
 **/
@Api(tags = "用户业务接口")
@RestController
@RequestMapping(Endpoints.API_V1 + Endpoints.USER)
public class UserController{
  @Resource
  private UserService userService;

  @GetMapping(Endpoints.GET + Endpoints.ID)
  @ApiOperation("根据用户ID查询用户信息")

  public User getUserById(@PathVariable(value = "id") @ApiParam(name = "id", value = "用户ID", defaultValue = "1") Long userId){
      return userService.getUserById(userId);
  }
}
