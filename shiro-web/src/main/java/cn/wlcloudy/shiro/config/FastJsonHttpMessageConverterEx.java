package cn.wlcloudy.shiro.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FastJsonHttpMessageConverterEx
 * @Description 自定义文本json转换器类
 * @Author wangxiong
 * @Date 2018/10/22 19:33
 * @Version 1.0
 **/
public class FastJsonHttpMessageConverterEx extends FastJsonHttpMessageConverter {
    public FastJsonHttpMessageConverterEx() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 自定义时间格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // 正常转换 null 值
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        this.setSupportedMediaTypes(fastMediaTypes);
        this.setFastJsonConfig(fastJsonConfig);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return super.supports(clazz);
    }
}
