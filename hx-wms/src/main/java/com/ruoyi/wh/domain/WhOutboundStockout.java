package com.ruoyi.wh.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 缺货管制订单对象 wms_wh_outbound_stockout
 * 
 * @author miki
 * @date 2021-06-09
 */
public class WhOutboundStockout extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品出库主表ID */
    @Excel(name = "商品出库主表ID")
    private Long outboundOrderId;

    /** 商品出库子表ID */
    @Excel(name = "商品出库子表ID")
    private Long outboundOrderSeedId;

    /** 商品主键ID */
    @Excel(name = "商品主键ID")
    private Long shopGoodsId;

    /** 欠货数量 */
    @Excel(name = "欠货数量")
    private Long oweNumber;

    /** 状态(是否解欠) */
    @Excel(name = "状态(是否解欠)")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOutboundOrderId(Long outboundOrderId) 
    {
        this.outboundOrderId = outboundOrderId;
    }

    public Long getOutboundOrderId() 
    {
        return outboundOrderId;
    }
    public void setOutboundOrderSeedId(Long outboundOrderSeedId) 
    {
        this.outboundOrderSeedId = outboundOrderSeedId;
    }

    public Long getOutboundOrderSeedId() 
    {
        return outboundOrderSeedId;
    }
    public void setShopGoodsId(Long shopGoodsId) 
    {
        this.shopGoodsId = shopGoodsId;
    }

    public Long getShopGoodsId() 
    {
        return shopGoodsId;
    }
    public void setOweNumber(Long oweNumber) 
    {
        this.oweNumber = oweNumber;
    }

    public Long getOweNumber() 
    {
        return oweNumber;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("outboundOrderId", getOutboundOrderId())
            .append("outboundOrderSeedId", getOutboundOrderSeedId())
            .append("shopGoodsId", getShopGoodsId())
            .append("oweNumber", getOweNumber())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("deptId", getDeptId())
            .toString();
    }
}
