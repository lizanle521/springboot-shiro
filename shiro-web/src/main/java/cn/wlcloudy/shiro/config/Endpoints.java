package cn.wlcloudy.shiro.config;

/**
 * @ClassName Endpoints
 * @Description API配置
 * @Author wangxiong
 * @Date 2018/10/26 10:13
 * @Version 1.0
 **/
public interface Endpoints {
    String API_V1 = "/api/v1/";

    String ID = "/{id}";
    String GET = "/get";
    String LIST = "/list";
    String CREATE = "/create";
    String UPDATE = "/update";
    String DELETE = "/delete";

    String LOGIN = "/login";
    String LOGOUT = "/logout";

    String USER ="/user";
}
