package com.example.controller;

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

    @GetMapping("/getUserName")
    public String getUserNameById(@RequestParam("userId") String userId) {
        return redisService.getUserName("user_info:"+userId, "name");
    }
}
