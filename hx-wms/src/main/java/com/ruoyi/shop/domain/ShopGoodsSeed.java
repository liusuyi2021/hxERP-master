package com.ruoyi.shop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品库存信息对象 wms_shop_goods_seed
 *
 * @author miki
 * @date 2021-06-01
 */
public class ShopGoodsSeed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品信息id */
    @Excel(name = "商品信息id")
    private Long goodsId;

    /** 安全库存 */
    @Excel(name = "安全库存")
    private Long safetyStock;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private Long stockNumber;

    /** 待拣货数量 */
    @Excel(name = "待拣货数量")
    private Long forPickingNumber;

    /** 已拣货数量 */
    @Excel(name = "已拣货数量")
    private Long beenPickingNumber;

    /** 在途数量(供应商已出货但本厂尚未点收的数量；弃用，因为点收入库需再次输入数量确认) */
    @Excel(name = "在途数量(供应商已出货但本厂尚未点收的数量；弃用，因为点收入库需再次输入数量确认)")
    private Long transitNumber;

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
    public void setGoodsId(Long goodsId)
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId()
    {
        return goodsId;
    }
    public void setSafetyStock(Long safetyStock)
    {
        this.safetyStock = safetyStock;
    }

    public Long getSafetyStock()
    {
        return safetyStock;
    }
    public void setStockNumber(Long stockNumber)
    {
        this.stockNumber = stockNumber;
    }

    public Long getStockNumber()
    {
        return stockNumber;
    }
    public void setForPickingNumber(Long forPickingNumber)
    {
        this.forPickingNumber = forPickingNumber;
    }

    public Long getForPickingNumber()
    {
        return forPickingNumber;
    }
    public void setBeenPickingNumber(Long beenPickingNumber)
    {
        this.beenPickingNumber = beenPickingNumber;
    }

    public Long getBeenPickingNumber()
    {
        return beenPickingNumber;
    }
    public void setTransitNumber(Long transitNumber)
    {
        this.transitNumber = transitNumber;
    }

    public Long getTransitNumber()
    {
        return transitNumber;
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
                .append("goodsId", getGoodsId())
                .append("safetyStock", getSafetyStock())
                .append("stockNumber", getStockNumber())
                .append("forPickingNumber", getForPickingNumber())
                .append("beenPickingNumber", getBeenPickingNumber())
                .append("transitNumber", getTransitNumber())
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