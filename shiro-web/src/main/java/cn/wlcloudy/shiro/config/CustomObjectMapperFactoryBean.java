package cn.wlcloudy.shiro.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName CustomObjectMapperFactoryBean
 * @Description 扩展FactoryBean
 * @Author wangxiong
 * @Date 2018/10/25 14:26
 * @Version 1.0
 **/
public class CustomObjectMapperFactoryBean implements FactoryBean<ObjectMapper> {

    SerializerFeature[] features;

    public CustomObjectMapperFactoryBean(SerializerFeature[] features) {
        this.features = features;
    }

    @Override
    public ObjectMapper getObject() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new FastJsonSerializerFeatureCompatibleForJackson(features))).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return ObjectMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
