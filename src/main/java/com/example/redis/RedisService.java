package com.example.redis;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Real
 * @Data 2021/4/7 17:05
 */
@Log4j2
@Service
public class RedisService {

    @Resource(name = "redisTemplateRead")
    private RedisTemplate redisTemplateRead;

    @Resource(name = "redisTemplateWrite")
    private RedisTemplate redisTemplateWrite;

    public RedisTemplate getRedisTemplateByType(int type){
        switch (type){
            case 1:
                return redisTemplateRead;
            case 2:
                return redisTemplateWrite;
            default:
                break;

        }
        return null;
    }

    public String getUserName(String key, String hashkey){
        RedisTemplate redisTemplate = getRedisTemplateByType(1);
        return (String) redisTemplate.opsForHash().get(key, hashkey);
    }
}
