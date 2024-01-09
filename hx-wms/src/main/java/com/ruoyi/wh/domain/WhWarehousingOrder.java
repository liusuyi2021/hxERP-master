package com.ruoyi.wh.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.enums.WhWarehousingOrderType;
import com.ruoyi.wh.enums.WarehousingOrderStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品入库单主对象 wms_wh_warehousing_order
 *
 * @author miki
 * @date 2021-05-31
 */
public class WhWarehousingOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 入库类型(其他入库、采购入库) */
    @Excel(name = "入库类型(其他入库、采购入库)")
    private String orderType;

    /** 入库单编号 */
    @Excel(name = "入库单编号")
    private String orderCode;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderName;

    /** 订单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 供应商主键ID */
    @Excel(name = "供应商主键ID")
    private Long supplierId;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 单位关系主键ID */
    @Excel(name = "单位关系主键ID")
    private Long warehouseId;

    /** 对象单位主键ID */
    @Excel(name = "对象单位主键ID")
    private Long desWarehouseId;

    /** 对象单位名称 */
    @Excel(name = "对象单位名称")
    private String desWarehouseName;

    /** 税率 */
    @Excel(name = "税率")
    private BigDecimal rate;

    /** 税额 */
    @Excel(name = "税额")
    private BigDecimal tax;

    /** 折扣率 */
    @Excel(name = "折扣率")
    private BigDecimal discountRate;

    /** 折扣金额 */
    @Excel(name = "折扣金额")
    private BigDecimal discountAmount;

    /** 折让金额 */
    @Excel(name = "折让金额")
    private BigDecimal discountPrice;

    /** 其他费用 */
    @Excel(name = "其他费用")
    private BigDecimal otherFee;

    /** 未税金额 */
    @Excel(name = "未税金额")
    private BigDecimal untaxedAmount;

    /** 含税金额 */
    @Excel(name = "含税金额")
    private BigDecimal totalAmount;

    /** 状态(待到货、待卸货、待分拣、已分拣) */
    @Excel(name = "状态(待到货、待卸货、待分拣、已分拣)")
    private String status;

    /** 关联出库单主键 */
    @Excel(name = "关联出库单主键")
    private Long outboundOrderId;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 状态描述(待到货、待卸货、待分拣、已分拣) */
    private String statusDesc;

    /** 单据类型描述 */
    private String orderTypeDesc;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public String getOrderType()
    {
        return orderType;
    }
    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }
    public void setOrderName(String orderName)
    {
        this.orderName = orderName;
    }

    public String getOrderName()
    {
        return orderName;
    }
    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }
    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }
    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName()
    {
        return supplierName;
    }
    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }
    public void setDesWarehouseId(Long desWarehouseId)
    {
        this.desWarehouseId = desWarehouseId;
    }

    public Long getDesWarehouseId()
    {
        return desWarehouseId;
    }
    public void setDesWarehouseName(String desWarehouseName)
    {
        this.desWarehouseName = desWarehouseName;
    }

    public String getDesWarehouseName()
    {
        return desWarehouseName;
    }
    public void setRate(BigDecimal rate)
    {
        this.rate = rate;
    }

    public BigDecimal getRate()
    {
        return rate;
    }
    public void setTax(BigDecimal tax)
    {
        this.tax = tax;
    }

    public BigDecimal getTax()
    {
        return tax;
    }
    public void setDiscountRate(BigDecimal discountRate)
    {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountRate()
    {
        return discountRate;
    }
    public void setDiscountAmount(BigDecimal discountAmount)
    {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getDiscountAmount()
    {
        return discountAmount;
    }
    public void setDiscountPrice(BigDecimal discountPrice)
    {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getDiscountPrice()
    {
        return discountPrice;
    }
    public void setOtherFee(BigDecimal otherFee)
    {
        this.otherFee = otherFee;
    }

    public BigDecimal getOtherFee()
    {
        return otherFee;
    }
    public void setUntaxedAmount(BigDecimal untaxedAmount)
    {
        this.untaxedAmount = untaxedAmount;
    }

    public BigDecimal getUntaxedAmount()
    {
        return untaxedAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount()
    {
        return totalAmount;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setOutboundOrderId(Long outboundOrderId)
    {
        this.outboundOrderId = outboundOrderId;
    }

    public Long getOutboundOrderId()
    {
        return outboundOrderId;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderType", getOrderType())
                .append("orderCode", getOrderCode())
                .append("orderName", getOrderName())
                .append("orderDate", getOrderDate())
                .append("supplierId", getSupplierId())
                .append("supplierName", getSupplierName())
                .append("warehouseId", getWarehouseId())
                .append("desWarehouseId", getDesWarehouseId())
                .append("desWarehouseName", getDesWarehouseName())
                .append("rate", getRate())
                .append("tax", getTax())
                .append("discountRate", getDiscountRate())
                .append("discountAmount", getDiscountAmount())
                .append("discountPrice", getDiscountPrice())
                .append("otherFee", getOtherFee())
                .append("untaxedAmount", getUntaxedAmount())
                .append("totalAmount", getTotalAmount())
                .append("status", getStatus())
                .append("outboundOrderId", getOutboundOrderId())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("deptId", getDeptId())
                .toString();
    }

    public String getStatusDesc() {
        for (WarehousingOrderStatus warehousingOrderStatus :  WarehousingOrderStatus.values()) {
            if(warehousingOrderStatus.getCode().equals(this.getStatus())){
                statusDesc = warehousingOrderStatus.getInfo();
            }
        }
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getOrderTypeDesc() {
        for (WhWarehousingOrderType warehousingOrderType :  WhWarehousingOrderType.values()) {
            if(warehousingOrderType.getCode().equals(this.getOrderType())){
                orderTypeDesc = warehousingOrderType.getInfo();
            }
        }
        return orderTypeDesc;
    }

    public void setOrderTypeDesc(String orderTypeDesc) {
        this.orderTypeDesc = orderTypeDesc;
    }
}