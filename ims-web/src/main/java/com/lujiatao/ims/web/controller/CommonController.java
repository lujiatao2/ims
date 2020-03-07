package com.lujiatao.ims.web.controller;

import com.lujiatao.ims.common.model.BaseVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonController {

    @GetMapping
    public String main() {
        return "redirect:/goods";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    @ResponseBody
    public BaseVO accessDenied() {
        return new BaseVO(2, "您无权限进行该操作！");
    }

}
