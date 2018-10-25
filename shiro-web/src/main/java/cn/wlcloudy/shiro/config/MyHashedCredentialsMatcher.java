package cn.wlcloudy.shiro.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyHashedCredentialsMatcher
 * @Description 自定义密码匹配器
 * @Author wangxiong
 * @Date 2018/10/22 19:33
 * @Version 1.0
 **/
@Component
public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if (WeChatToken.class.isAssignableFrom(token.getClass())) {
            return true;
        }
        return super.doCredentialsMatch(token, info);
    }

}
