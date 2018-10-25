package cn.wlcloudy.shiro.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ClassName WeChatToken
 * @Description 微信企业号认证token
 * @Author wangxiong
 * @Date 2018/10/22 10:32
 * @Version 1.0
 **/
public class WeChatToken extends UsernamePasswordToken {

    private Integer userId;

    public WeChatToken(){
    }

    public WeChatToken(Integer userId){
        this.userId = userId;
    }

    @Override
    public Object getPrincipal() {
        return this.getUserId();
    }

    public Integer getUserId() {
        return userId;
    }
}
