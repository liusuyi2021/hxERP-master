package com.ruoyi.wh.model;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.wh.domain.WhOutboundOrderSeed;

import java.io.Serializable;
import java.util.List;

/**
 * 接收单据精灵对象
 *
 * @author miki
 * @date 2021-06-14
 */
public class OrderModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String sheetType;//单据类别

    /** 单位关系主键ID */
    private Long warehouseId;

    /** 对象单位主键ID */
    private Long desWarehouseId;

    /** 对象单位名称 */
    private String desWarehouseName;

    /** 相关单据ID主键 */
    private Long orderId;

    /** 相关单据编号 */
    private String orderCode;

    /** 订单号 */
    private String orderName;

    /** 备注 */
    private String remark;

    /** 据精灵子对象 */
    private List<OrderSeedModel> orderSeedModelList;

    public String getSheetType() {
        return sheetType;
    }

    public void setSheetType(String sheetType) {
        this.sheetType = sheetType;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getDesWarehouseId() {
        return desWarehouseId;
    }

    public void setDesWarehouseId(Long desWarehouseId) {
        this.desWarehouseId = desWarehouseId;
    }

    public String getDesWarehouseName() {
        return desWarehouseName;
    }

    public void setDesWarehouseName(String desWarehouseName) {
        this.desWarehouseName = desWarehouseName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OrderSeedModel> getOrderSeedModelList() {
        return orderSeedModelList;
    }

    public void setOrderSeedModelList(List<OrderSeedModel> orderSeedModelList) {
        this.orderSeedModelList = orderSeedModelList;
    }
}