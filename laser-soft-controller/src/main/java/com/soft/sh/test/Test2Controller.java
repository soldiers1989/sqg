package com.soft.sh.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class Test2Controller {

    @RequestMapping("/hello")
    public String Hello() {

        System.out.println("aaa");
        return "index";
    }

    @RequestMapping(value = "/sayhello",method = RequestMethod.GET)
    public String SayHello(String name) {

        return "hellp:" + name;
    }
}
