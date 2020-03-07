package com.lujiatao.ims.web.controller;

import com.lujiatao.ims.common.entity.Goods;
import com.lujiatao.ims.common.model.BaseVO;
import com.lujiatao.ims.service.GoodsService;
import com.lujiatao.ims.web.util.Paginationer;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    //引用Dubbo接口
    @Reference(version = "1.0.0")
    private GoodsService goodsService;
    private Paginationer paginationer = new Paginationer();

    @GetMapping
    public String goods(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "goods";
    }

    @GetMapping("/all")
    @ResponseBody
    public BaseVO all() {
        List<Goods> list = goodsService.getAll();
        Object[] objects = paginationer.getTargetPageData(list, 1);
        return new BaseVO(0, "成功！", objects);
    }

    @GetMapping("/page")
    @ResponseBody
    public BaseVO page(@RequestParam("targetPage") int targetPage) {
        List<Goods> list = goodsService.getAll();
        Object[] objects = paginationer.getTargetPageData(list, targetPage);
        return new BaseVO(0, "成功！", objects);
    }

    @GetMapping("/search")
    @ResponseBody
    public BaseVO search(@RequestParam("goodsCategory") int goodsCategory, @RequestParam("brand") String brand, @RequestParam("model") String model) {
        List<Goods> list = goodsService.search(goodsCategory, brand, model);
        Object[] objects = paginationer.getTargetPageData(list, 1);
        return new BaseVO(0, "成功！", objects);
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO add(@RequestBody Goods goods) {
        int id = goods.getId();
        Goods tmp = goodsService.getById(id);
        if (tmp == null) {
            return goodsService.add(goods) ? new BaseVO(0, "成功！") : new BaseVO(1, "失败！");
        } else {
            return new BaseVO(3, "该记录已存在！");
        }
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO modify(@RequestBody Goods goods) {
        int id = goods.getId();
        Goods tmp = goodsService.getById(id);
        if (tmp != null) {
            return goodsService.modify(goods) ? new BaseVO(0, "成功！") : new BaseVO(1, "失败！");
        } else {
            return new BaseVO(4, "该记录不存在！");
        }
    }

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO delete(@RequestParam("id") int id) {
        Goods tmp = goodsService.getById(id);
        if (tmp != null) {
            return goodsService.deleteById(id) ? new BaseVO(0, "成功！") : new BaseVO(1, "失败！");
        } else {
            return new BaseVO(4, "该记录不存在！");
        }
    }

}
