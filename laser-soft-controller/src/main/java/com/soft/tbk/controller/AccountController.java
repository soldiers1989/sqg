package com.soft.tbk.controller;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.soft.tbk.model.TbkUser;

@Controller
@RequestMapping("/web/account")
public class AccountController {

    @RequestMapping("/index")
    public String test(ModelMap model) {

        TbkUser user = new TbkUser();
        user.setUserNickname("testzjx");

        model.put("user", user);
        return "/h5/account/index";
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
