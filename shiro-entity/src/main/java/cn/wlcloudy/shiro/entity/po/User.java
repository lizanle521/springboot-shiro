package cn.wlcloudy.shiro.entity.po;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName User
 * @Description 用户实体
 * @Author wangxiong
 * @Date 2018/10/25 11:01
 * @Version 1.0
 **/
@Entity
@Table(name="user")
@ApiModel(description = "用户信息")
public class User extends BasePO implements Serializable {
    public User() {
    }

    public User(String username, String password, String email, String nickName, String realName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.realName = realName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", value = "用户ID", example = "1")
    private long id;

    @Column(columnDefinition = "varchar(100) COMMENT '用户名'")
    @ApiModelProperty(name = "username", value = "用户名", example = "wlcloudy")
    private String username;

    @Column(columnDefinition = "varchar(200) COMMENT '用户密码'")
    @JSONField(serialize = false)
    @ApiModelProperty(hidden = true)
    private String password;

    @Column(columnDefinition = "varchar(200) COMMENT '邮箱'")
    @ApiModelProperty(name = "email", value = "邮箱", example = "wlcloudy@163.com")
    private String email;

    @Column(name = "nick_name",columnDefinition = "varchar(200) COMMENT '用户昵称'")
    @ApiModelProperty(name = "nickName", value = "用户昵称", example = "子云")
    private String nickName;

    @Column(name = "real_name",columnDefinition = "varchar(100) COMMENT '用户姓名'")
    @ApiModelProperty(name = "realName", value = "用户姓名", example = "王雄")
    private String realName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
