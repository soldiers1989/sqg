package com.soft.sh.test;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.soft.sh.core.redis.IRedisClientKValue;

@RestController
@RequestMapping("/backReturn")
public class TestController {

    @Autowired
    IRedisClientKValue<String> redisClient;

    @RequestMapping("/test")
    public String test() {

        return "hello test!";
    }

    @RequestMapping("/hello")
    public String Hello() {

        return "hello everyone!";
    }

    @RequestMapping(value = "/cybs",method = RequestMethod.POST)
    public String SayHello(@RequestParam LinkedHashMap<String, String> cybsReturnParams) {

        String returnStr = JSON.toJSONString(cybsReturnParams);
        System.out.println(returnStr);
        return returnStr;
    }
}
