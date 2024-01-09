package com.ruoyi.shop.mode;

import java.io.Serializable;

/**
 * 商品库存信息对象 wms_shop_goods_seed
 *
 * @author miki
 * @date 2021-06-01
 */
public class ShopGoodsSeedMode implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 商品信息id */
    private Long goodsId;

    /** 库存数量 */
    private Long stockNumber;

    /** 待拣货数量 */
    private Long forPickingNumber;

    /** 已拣货数量 */
    private Long beenPickingNumber;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Long stockNumber) {
        this.stockNumber = stockNumber;
    }

    public Long getForPickingNumber() {
        return forPickingNumber;
    }

    public void setForPickingNumber(Long forPickingNumber) {
        this.forPickingNumber = forPickingNumber;
    }

    public Long getBeenPickingNumber() {
        return beenPickingNumber;
    }

    public void setBeenPickingNumber(Long beenPickingNumber) {
        this.beenPickingNumber = beenPickingNumber;
    }
}