package com.example.redis;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author Real
 * @Data 2021/4/14 17:12
 */
@Service
@Log4j2
public class RedisLock {



    @Resource(name = "redisTemplateWrite")
    private RedisTemplate redisTemplateWrite;



    public boolean lock(String lockKey, String lockValue){
        log.info("开始加锁。。。");
        while(true){
            try {
                if (redisTemplateWrite.opsForValue().setIfAbsent(lockKey, lockValue)) {
                    return true;
                }
                //redis里存的过期时间
                String currValue = (String)redisTemplateWrite.opsForValue().get(lockKey);

                //判断锁是否过期
                if(!StringUtils.isNotBlank(currValue) && Long.parseLong(currValue)<System.currentTimeMillis()){

                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error("【redis分布式锁】sleep异常,lockKey:" + lockKey + ",error:" + e.getMessage(), e);
                    Thread.currentThread().interrupt();
                }
            }catch (Exception e){
                log.error("【redis分布式锁】加锁异常,lockKey:" + lockKey + ",error:" + e.getMessage());
            }
        }
    }

    public void unlock(String lockKey, String lockValue){

        try{
            log.info("开始解锁。。。");
            String value = (String)redisTemplateWrite.opsForValue().get(lockKey);
            if(!StringUtils.isBlank(value) && value.equals(lockValue)){
                redisTemplateWrite.delete(lockKey);
            }
        }catch (Exception e){
            log.info("解锁异常。。。");
        }
    }
}
