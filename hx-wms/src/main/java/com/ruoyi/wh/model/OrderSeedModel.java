package com.ruoyi.wh.model;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;

/**
 * 接收单据精灵子对象
 *
 * @author miki
 * @date 2021-06-14
 */
public class OrderSeedModel implements Serializable
{
    /** id */
    private Long id;

    /** 商品主键ID */
    private Long shopGoodsId;

    /** 计划数量 */
    private Long planNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopGoodsId() {
        return shopGoodsId;
    }

    public void setShopGoodsId(Long shopGoodsId) {
        this.shopGoodsId = shopGoodsId;
    }

    public Long getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(Long planNumber) {
        this.planNumber = planNumber;
    }
}