package com.soft.tbk.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.service.TbkUserService;

@RestController
@RequestMapping("/backReturn")
public class TestController {

    @Autowired
    TbkUserService tbkUserService;

    @RequestMapping("/test")
    public String test() {

        TbkUser user = new TbkUser();
        user.setParentId(10);
        user.setUserOpenid("o01fz1P6fq6U7HO5kBNY7PK2Fsi0");
        tbkUserService.saveTbkUserWithOpenId(user);
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
