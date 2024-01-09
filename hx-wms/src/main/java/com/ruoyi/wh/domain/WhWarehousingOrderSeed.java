package com.ruoyi.wh.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品入库单子对象 wms_wh_warehousing_order_seed
 *
 * @author miki
 * @date 2021-06-04
 */
public class WhWarehousingOrderSeed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品入库主表ID */
    @Excel(name = "商品入库主表ID")
    private Long warehousingOrderId;

    /** 商品主键ID */
    @Excel(name = "商品主键ID")
    private Long shopGoodsId;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String shopGoodsCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String shopGoodsName;

    /** 单位【关联字典管理】 */
    @Excel(name = "单位【关联字典管理】")
    private String goodsUnit;

    /** 单位描述 */
    @Excel(name = "单位描述")
    private String goodsUnitDesc;

    /** 计划数量 */
    @Excel(name = "计划数量")
    private Long planNumber;

    /** 实际数量 */
    @Excel(name = "实际数量")
    private Long warehousingNumber;

    /** 多到货数量 */
    @Excel(name = "多到货数量")
    private Long moreNumber;

    /** 到货短少数量 */
    @Excel(name = "到货短少数量")
    private Long shortageNumber;

    /** 未税单价 */
    @Excel(name = "未税单价")
    private BigDecimal unitPrice;

    /** 含税单价 */
    @Excel(name = "含税单价")
    private BigDecimal taxUnitPrice;

    /** 税率 */
    @Excel(name = "税率")
    private BigDecimal rate;

    /** 合计税额 */
    @Excel(name = "合计税额")
    private BigDecimal tax;

    /** 未税金额 */
    @Excel(name = "未税金额")
    private BigDecimal untaxedAmount;

    /** 折让金额 */
    @Excel(name = "折让金额")
    private BigDecimal discountPrice;

    /** 含税金额 */
    @Excel(name = "含税金额")
    private BigDecimal amount;

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
    public void setWarehousingOrderId(Long warehousingOrderId)
    {
        this.warehousingOrderId = warehousingOrderId;
    }

    public Long getWarehousingOrderId()
    {
        return warehousingOrderId;
    }
    public void setShopGoodsId(Long shopGoodsId)
    {
        this.shopGoodsId = shopGoodsId;
    }

    public Long getShopGoodsId()
    {
        return shopGoodsId;
    }
    public void setShopGoodsCode(String shopGoodsCode)
    {
        this.shopGoodsCode = shopGoodsCode;
    }

    public String getShopGoodsCode()
    {
        return shopGoodsCode;
    }
    public void setShopGoodsName(String shopGoodsName)
    {
        this.shopGoodsName = shopGoodsName;
    }

    public String getShopGoodsName()
    {
        return shopGoodsName;
    }
    public void setGoodsUnit(String goodsUnit)
    {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsUnit()
    {
        return goodsUnit;
    }
    public void setGoodsUnitDesc(String goodsUnitDesc)
    {
        this.goodsUnitDesc = goodsUnitDesc;
    }

    public String getGoodsUnitDesc()
    {
        return goodsUnitDesc;
    }
    public void setPlanNumber(Long planNumber)
    {
        this.planNumber = planNumber;
    }

    public Long getPlanNumber()
    {
        return planNumber;
    }
    public void setWarehousingNumber(Long warehousingNumber)
    {
        this.warehousingNumber = warehousingNumber;
    }

    public Long getWarehousingNumber()
    {
        return warehousingNumber;
    }
    public void setMoreNumber(Long moreNumber)
    {
        this.moreNumber = moreNumber;
    }

    public Long getMoreNumber()
    {
        return moreNumber;
    }
    public void setShortageNumber(Long shortageNumber)
    {
        this.shortageNumber = shortageNumber;
    }

    public Long getShortageNumber()
    {
        return shortageNumber;
    }
    public void setUnitPrice(BigDecimal unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }
    public void setTaxUnitPrice(BigDecimal taxUnitPrice)
    {
        this.taxUnitPrice = taxUnitPrice;
    }

    public BigDecimal getTaxUnitPrice()
    {
        return taxUnitPrice;
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
    public void setUntaxedAmount(BigDecimal untaxedAmount)
    {
        this.untaxedAmount = untaxedAmount;
    }

    public BigDecimal getUntaxedAmount()
    {
        return untaxedAmount;
    }
    public void setDiscountPrice(BigDecimal discountPrice)
    {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getDiscountPrice()
    {
        return discountPrice;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
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
                .append("warehousingOrderId", getWarehousingOrderId())
                .append("shopGoodsId", getShopGoodsId())
                .append("shopGoodsCode", getShopGoodsCode())
                .append("shopGoodsName", getShopGoodsName())
                .append("goodsUnit", getGoodsUnit())
                .append("goodsUnitDesc", getGoodsUnitDesc())
                .append("planNumber", getPlanNumber())
                .append("warehousingNumber", getWarehousingNumber())
                .append("moreNumber", getMoreNumber())
                .append("shortageNumber", getShortageNumber())
                .append("unitPrice", getUnitPrice())
                .append("taxUnitPrice", getTaxUnitPrice())
                .append("rate", getRate())
                .append("tax", getTax())
                .append("untaxedAmount", getUntaxedAmount())
                .append("discountPrice", getDiscountPrice())
                .append("amount", getAmount())
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