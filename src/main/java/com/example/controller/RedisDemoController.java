package com.example.controller;

import com.example.redis.RedisLock;
import com.example.redis.RedisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Real
 * @Data 2021/4/7 17:24
 */
@RestController
public class RedisDemoController {

    @Resource
    private RedisService redisService;

    @Resource
    private RedisLock lock;

    /**
     * 锁超时时间1分1毫秒
     */
    public final static int LOCK_EXPIRE_TIME = 60001;

    @GetMapping("/getUserName")
    public String getUserNameById(@RequestParam("userId") String userId) {
        return redisService.getUserName("user_info:"+userId, "name");
    }

    @GetMapping("/testLock")
    public String testLock() {

        String expireStr = String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE_TIME);
        try {
            lock.lock("reallockkey", expireStr);
            System.out.println("业务开始执行");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock("reallockkey", expireStr);
        }
        return "test lock finish";
    }


}
