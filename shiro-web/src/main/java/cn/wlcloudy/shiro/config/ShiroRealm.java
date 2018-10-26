package cn.wlcloudy.shiro.config;

import cn.wlcloudy.shiro.dao.UserRepository;
import cn.wlcloudy.shiro.entity.po.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * @ClassName ShiroRealm
 * @Description TODO
 * @Author wangxiong
 * @Date 2018/10/25 18:34
 * @Version 1.0
 **/
@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private UserRepository userRepository;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken || token instanceof WeChatToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("授权");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("身份认证");
        User userInfo = null;
        //若为微信用户token，免密码登录
        if(token instanceof WeChatToken){
            Long userId = (Long) token.getPrincipal();
            userInfo = userRepository.getOne(userId);
            if (null ==userInfo){
                return null;
            }
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getUsername()), getName());
            return info;
        }
        // 获取用户登录账号
        String username = (String) token.getPrincipal();
        userInfo = userRepository.findUserByUsername(username);
        if (null ==userInfo){
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getUsername()), getName());
        return info;
    }
}
