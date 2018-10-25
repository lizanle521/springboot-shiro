package cn.wlcloudy.shiro.dao;

import cn.wlcloudy.shiro.entity.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserRepository
 * @Description 用户持久接口
 * @Author wangxiong
 * @Date 2018/10/25 11:15
 * @Version 1.0
 **/
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}
