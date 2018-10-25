package cn.wlcloudy.shiro.entity.po;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Service;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    private long id;

    @Column(columnDefinition = "varchar(100) COMMENT '用户名'")
    private String username;

    @Column(columnDefinition = "varchar(200) COMMENT '用户密码'")
    private String password;

    @Column(columnDefinition = "varchar(200) COMMENT '邮箱'")
    private String email;

    @Column(name = "nick_name",columnDefinition = "varchar(200) COMMENT '用户昵称'")
    private String nickName;

    @Column(name = "real_name",columnDefinition = "varchar(100) COMMENT '用户姓名'")
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
