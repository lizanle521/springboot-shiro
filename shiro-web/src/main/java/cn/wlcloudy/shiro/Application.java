package cn.wlcloudy.shiro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ClassName Application
 * @Description 启动类
 * @Author wangxiong
 * @Date 2018/10/25 11:18
 * @Version 1.0
 **/
@SpringBootApplication
@Slf4j
@EnableCaching
public class Application {
    public static void main(String[] args) {
      SpringApplication.run(Application.class,args);
    }
}
