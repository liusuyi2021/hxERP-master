package com.ruoyi.wh.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 缺货管制订单对象 wms_wh_outbound_stockout
 * 
 * @author miki
 * @date 2021-06-09
 */
public class WhOutboundStockoutVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品出库主表ID */
    private Long outboundOrderId;

    /** 商品出库子表ID */
    private Long outboundOrderSeedId;

    /** 商品主键ID */
    private Long shopGoodsId;

    /** 欠货数量 */
    private Long oweNumber;

    /** 状态(是否解欠) */
    private String status;

    /** 部门ID */
    private Long deptId;

    /** 出库单编号 */
    private String orderCode;

    /** 订单号 */
    private String orderName;

    /** 商品编码 */
    private String shopGoodsCode;

    /** 商品名称 */
    private String shopGoodsName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOutboundOrderId() {
        return outboundOrderId;
    }

    public void setOutboundOrderId(Long outboundOrderId) {
        this.outboundOrderId = outboundOrderId;
    }

    public Long getOutboundOrderSeedId() {
        return outboundOrderSeedId;
    }

    public void setOutboundOrderSeedId(Long outboundOrderSeedId) {
        this.outboundOrderSeedId = outboundOrderSeedId;
    }

    public Long getShopGoodsId() {
        return shopGoodsId;
    }

    public void setShopGoodsId(Long shopGoodsId) {
        this.shopGoodsId = shopGoodsId;
    }

    public Long getOweNumber() {
        return oweNumber;
    }

    public void setOweNumber(Long oweNumber) {
        this.oweNumber = oweNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getShopGoodsCode() {
        return shopGoodsCode;
    }

    public void setShopGoodsCode(String shopGoodsCode) {
        this.shopGoodsCode = shopGoodsCode;
    }

    public String getShopGoodsName() {
        return shopGoodsName;
    }

    public void setShopGoodsName(String shopGoodsName) {
        this.shopGoodsName = shopGoodsName;
    }
}
