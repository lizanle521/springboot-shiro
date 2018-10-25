package cn.wlcloudy.shiro.controller;

import cn.wlcloudy.shiro.entity.po.User;
import cn.wlcloudy.shiro.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
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
@RestController
@RequestMapping("/user")
public class UserController {
  @Resource
  private UserService userService;

  @GetMapping("/get")
  public User getUserById(Long userId){
      return userService.getUserById(userId);
  }
}
