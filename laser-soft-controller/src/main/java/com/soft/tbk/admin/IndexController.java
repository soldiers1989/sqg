package com.soft.tbk.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soft.tbk.base.BaseController;

@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController{
    
    @RequestMapping("/index")
    public String page(ModelMap model) {

        return "/admin/index";
    }
}
