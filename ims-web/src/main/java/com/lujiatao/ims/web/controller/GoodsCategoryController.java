package com.lujiatao.ims.web.controller;

import com.lujiatao.ims.common.entity.GoodsCategory;
import com.lujiatao.ims.common.model.BaseVO;
import com.lujiatao.ims.service.GoodsCategoryService;
import com.lujiatao.ims.web.util.Paginationer;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/goods-category")
public class GoodsCategoryController {

    //引用Dubbo接口
    @Reference(version = "1.0.0")
    private GoodsCategoryService goodsCategoryService;
    private Paginationer paginationer = new Paginationer();

    @GetMapping
    public String goods(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "goods-category";
    }

    @GetMapping("/all")
    @ResponseBody
    public BaseVO all() {
        List<GoodsCategory> list = goodsCategoryService.getAll();
        Object[] objects = paginationer.getTargetPageData(list, 1);
        return new BaseVO(0, "成功！", objects);
    }

    @GetMapping("/all-without-page")
    @ResponseBody
    public BaseVO allWithoutPage() {
        return new BaseVO(0, "成功！", goodsCategoryService.getAll());
    }

    @GetMapping("/page")
    @ResponseBody
    public BaseVO page(@RequestParam("targetPage") int targetPage) {
        List<GoodsCategory> list = goodsCategoryService.getAll();
        Object[] objects = paginationer.getTargetPageData(list, targetPage);
        return new BaseVO(0, "成功！", objects);
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO add(@RequestBody GoodsCategory goodsCategory) {
        int id = goodsCategory.getId();
        GoodsCategory tmp = goodsCategoryService.getById(id);
        if (tmp == null) {
            return goodsCategoryService.add(goodsCategory) ? new BaseVO(0, "成功！") : new BaseVO(1, "失败！");
        } else {
            return new BaseVO(3, "该记录已存在！");
        }
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO modify(@RequestBody GoodsCategory goodsCategory) {
        int id = goodsCategory.getId();
        GoodsCategory tmp = goodsCategoryService.getById(id);
        if (tmp != null) {
            return goodsCategoryService.modify(goodsCategory) ? new BaseVO(0, "成功！") : new BaseVO(1, "失败！");
        } else {
            return new BaseVO(4, "该记录不存在！");
        }
    }

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO delete(@RequestParam("id") int id) {
        GoodsCategory tmp = goodsCategoryService.getById(id);
        if (tmp != null) {
            return goodsCategoryService.deleteById(id) ? new BaseVO(0, "成功！") : new BaseVO(1, "失败！");
        } else {
            return new BaseVO(4, "该记录不存在！");
        }
    }

}
