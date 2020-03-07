package com.lujiatao.ims.common.entity;


public class Goods extends BasePO {

    private int id;
    private String brand;
    private String model;
    private String desc;
    private boolean isInStock;
    private int goodsCategoryId;

    public Goods() {
    }

    public Goods(int id, String brand, String model, String desc, boolean isInStock, int goodsCategoryId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.desc = desc;
        this.isInStock = isInStock;
        this.goodsCategoryId = goodsCategoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(boolean isInStock) {
        this.isInStock = isInStock;
    }

    public int getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(int goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

}
