package com.lujiatao.ims.server.service.impl;

import com.lujiatao.ims.common.entity.GoodsCategory;
import com.lujiatao.ims.server.repository.GoodsCategoryDAO;
import com.lujiatao.ims.service.GoodsCategoryService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private GoodsCategoryDAO goodsCategoryDAO;

    @Autowired
    public GoodsCategoryServiceImpl(GoodsCategoryDAO goodsCategoryDAO) {
        this.goodsCategoryDAO = goodsCategoryDAO;
    }

    @Override
    public boolean add(GoodsCategory goodsCategory) {
        return goodsCategoryDAO.insert(goodsCategory);
    }

    @Override
    public boolean modify(GoodsCategory goodsCategory) {
        return goodsCategoryDAO.update(goodsCategory);
    }

    @Override
    public boolean deleteById(int id) {
        return goodsCategoryDAO.deleteById(id);
    }

    @Override
    public GoodsCategory getById(int id) {
        return goodsCategoryDAO.selectById(id);
    }

    @Override
    public List<GoodsCategory> getAll() {
        return goodsCategoryDAO.selectAll();
    }

}
