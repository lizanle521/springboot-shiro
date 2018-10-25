package cn.wlcloudy.shiro.service;

import cn.wlcloudy.shiro.dao.UserRepository;
import cn.wlcloudy.shiro.entity.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author wangxiong
 * @Date 2018/10/25 11:17
 * @Version 1.0
 **/
@Service
@Slf4j
public class UserService {

    @Resource
    private UserRepository userRepository;

    public User getUserById(Long userId){
        log.info("进入根据用户ID查询用户信息业务方法，入参：{}",userId);
        User user = userRepository.getOne(userId);
        if(user == null){
            return new User();
        }
        return user;
    }

}
