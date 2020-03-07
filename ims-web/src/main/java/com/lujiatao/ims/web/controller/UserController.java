package com.lujiatao.ims.web.controller;

import com.lujiatao.ims.common.entity.User;
import com.lujiatao.ims.common.model.BaseVO;
import com.lujiatao.ims.service.UserService;
import com.lujiatao.ims.web.util.Paginationer;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    //引用Dubbo接口
    @Reference(version = "1.0.0")
    private UserService userService;
    private Paginationer paginationer = new Paginationer();

    @GetMapping
    public String user(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "user";
    }

    @GetMapping("/all")
    @ResponseBody
    public BaseVO all() {
        List<User> list = userService.getAll();
        Object[] objects = paginationer.getTargetPageData(list, 1);
        return new BaseVO(0, "成功！", objects);
    }


    @GetMapping("/page")
    @ResponseBody
    public BaseVO page(@RequestParam("targetPage") int targetPage) {
        List<User> list = userService.getAll();
        Object[] objects = paginationer.getTargetPageData(list, targetPage);
        return new BaseVO(0, "成功！", objects);
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO add(@RequestBody User user) {
        String username = user.getUsername();
        User tmp = userService.getByUsername(username);
        if (tmp == null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userService.add(user) ? new BaseVO(0, "成功！") : new BaseVO(1, "失败！");
        } else {
            return new BaseVO(3, "该记录已存在！");
        }
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO modify(@RequestBody User user) {
        String username = user.getUsername();
        User tmp = userService.getByUsername(username);
        if (tmp != null) {
            if (!user.getPassword().equals(tmp.getPassword())) {//未修改密码就不做加密
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }
            return userService.modify(user) ? new BaseVO(0, "成功！") : new BaseVO(1, "失败！");
        } else {
            return new BaseVO(4, "该记录不存在！");
        }
    }

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO delete(@RequestParam("username") String username) {
        User tmp = userService.getByUsername(username);
        if (tmp != null) {
            return userService.deleteByUsername(username) ? new BaseVO(0, "成功！") : new BaseVO(1, "失败！");
        } else {
            return new BaseVO(4, "该记录不存在！");
        }
    }

}
