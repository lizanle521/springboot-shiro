package cn.wlcloudy.shiro.entity.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

/**
 * rest请求的响应实体类
 */
@Data
public class JsonResult {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 错误代码
     * 0：未出错
     */

    private Integer code;
    /**
     * 错误消息
     * 默认为空字符串
     */
    private String msg;
    /**
     * 返回的数据
     */
    private Object data;

    private JsonResult() {
    }

    private JsonResult(Integer code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.data = obj;
    }

    public static JsonResult OK() {
        return new JsonResult(0, "success", null);
    }

    public static JsonResult OK(Object obj) {
        return new JsonResult(0, "success", obj);
    }

    public static JsonResult Err(Integer err, String msg, Object obj) {
        return new JsonResult(err, msg, obj);
    }

    public static JsonResult Err(Integer err, String msg) {
        return new JsonResult(err, msg, null);
    }

    public static JsonResult Err(ResultCode resultCode) {
        return new JsonResult(resultCode.code(), resultCode.msg(), null);
    }

}
