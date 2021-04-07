package com.example.redis.config;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * @Author Real
 * @Data 2021/4/7 16:38
 */
public class CustomRedisSerializer implements RedisSerializer<Object> {

    private final Charset charset;

    public CustomRedisSerializer() {
        this(Charset.forName("UTF8"));
    }

    public CustomRedisSerializer(Charset charset) {
        Assert.notNull(charset);
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return new byte[0];
        } else if (object instanceof Boolean || object instanceof Character || object instanceof Integer || object instanceof Float
                || object instanceof Double || object instanceof Long || object instanceof String || object instanceof Byte) {
            return String.valueOf(object).getBytes(charset);
        } else {
            return new byte[0];
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return (bytes == null ? null : new String(bytes, charset));
    }
}
