package com.lujiatao.ims.server.service.impl;

import com.lujiatao.ims.common.entity.Goods;
import com.lujiatao.ims.server.repository.GoodsDAO;
import com.lujiatao.ims.service.GoodsService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class GoodsServiceImpl implements GoodsService {

    private GoodsDAO goodsDAO;

    @Autowired
    public GoodsServiceImpl(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    @Override
    public boolean add(Goods goods) {
        return goodsDAO.insert(goods);
    }

    @Override
    public boolean modify(Goods goods) {
        return goodsDAO.update(goods);
    }

    @Override
    public boolean deleteById(int id) {
        return goodsDAO.deleteById(id);
    }

    @Override
    public Goods getById(int id) {
        return goodsDAO.selectById(id);
    }

    @Override
    public List<Goods> search(int goodsCategoryId, String brand, String model) {
        return goodsDAO.select(goodsCategoryId, brand, model);
    }

    @Override
    public List<Goods> getAll() {
        return goodsDAO.selectAll();
    }

}
