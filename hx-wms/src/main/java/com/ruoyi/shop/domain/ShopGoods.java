package com.ruoyi.shop.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品信息对象 wms_shop_goods
 *
 * @author miki
 * @date 2021-05-26
 */
public class ShopGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品类别ID */
    @Excel(name = "商品类别ID")
    private Long goodsTypeId;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品条码 */
    @Excel(name = "商品条码")
    private String goodsBarcode;

    /** 商品规格 */
    @Excel(name = "商品规格")
    private String goodsSearchstandard;

    /** 商品颜色 */
    @Excel(name = "商品颜色")
    private String goodsColor;

    /** 所属库位主键 */
    @Excel(name = "所属库位主键")
    private Long storageId;

    /** 商品单位【关联字典管理】 */
    @Excel(name = "商品单位【关联字典管理】")
    private String goodsUnit;

    /** 商品重量(单位:千克) */
    @Excel(name = "商品重量(单位:千克)")
    private BigDecimal goodsWeight;

    /** 成本价 */
    @Excel(name = "成本价")
    private BigDecimal costPrice;

    /** 零售价 */
    @Excel(name = "零售价")
    private BigDecimal retailPrice;

    /** 销售价 */
    @Excel(name = "销售价")
    private BigDecimal sellingPrice;

    /** 批发价 */
    @Excel(name = "批发价")
    private BigDecimal wholesalePrice;

    /** 成本价锁定 */
    @Excel(name = "成本价锁定")
    private String isCost;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;


    /**-------------------------- 扩张字段 ---------------------------------*/
    /** 安全库存 */
    private Long safetyStock;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setGoodsTypeId(Long goodsTypeId)
    {
        this.goodsTypeId = goodsTypeId;
    }

    public Long getGoodsTypeId()
    {
        return goodsTypeId;
    }
    public void setGoodsCode(String goodsCode)
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode()
    {
        return goodsCode;
    }
    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName()
    {
        return goodsName;
    }
    public void setGoodsBarcode(String goodsBarcode)
    {
        this.goodsBarcode = goodsBarcode;
    }

    public String getGoodsBarcode()
    {
        return goodsBarcode;
    }
    public void setGoodsSearchstandard(String goodsSearchstandard)
    {
        this.goodsSearchstandard = goodsSearchstandard;
    }

    public String getGoodsSearchstandard()
    {
        return goodsSearchstandard;
    }
    public void setGoodsColor(String goodsColor)
    {
        this.goodsColor = goodsColor;
    }

    public String getGoodsColor()
    {
        return goodsColor;
    }
    public void setStorageId(Long storageId)
    {
        this.storageId = storageId;
    }

    public Long getStorageId()
    {
        return storageId;
    }
    public void setGoodsUnit(String goodsUnit)
    {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsUnit()
    {
        return goodsUnit;
    }
    public void setGoodsWeight(BigDecimal goodsWeight)
    {
        this.goodsWeight = goodsWeight;
    }

    public BigDecimal getGoodsWeight()
    {
        return goodsWeight;
    }
    public void setCostPrice(BigDecimal costPrice)
    {
        this.costPrice = costPrice;
    }

    public BigDecimal getCostPrice()
    {
        return costPrice;
    }
    public void setRetailPrice(BigDecimal retailPrice)
    {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getRetailPrice()
    {
        return retailPrice;
    }
    public void setSellingPrice(BigDecimal sellingPrice)
    {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getSellingPrice()
    {
        return sellingPrice;
    }
    public void setWholesalePrice(BigDecimal wholesalePrice)
    {
        this.wholesalePrice = wholesalePrice;
    }

    public BigDecimal getWholesalePrice()
    {
        return wholesalePrice;
    }
    public void setIsCost(String isCost)
    {
        this.isCost = isCost;
    }

    public String getIsCost()
    {
        return isCost;
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
                .append("goodsTypeId", getGoodsTypeId())
                .append("goodsCode", getGoodsCode())
                .append("goodsName", getGoodsName())
                .append("goodsBarcode", getGoodsBarcode())
                .append("goodsSearchstandard", getGoodsSearchstandard())
                .append("goodsColor", getGoodsColor())
                .append("storageId", getStorageId())
                .append("goodsUnit", getGoodsUnit())
                .append("goodsWeight", getGoodsWeight())
                .append("costPrice", getCostPrice())
                .append("retailPrice", getRetailPrice())
                .append("sellingPrice", getSellingPrice())
                .append("wholesalePrice", getWholesalePrice())
                .append("isCost", getIsCost())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("deptId", getDeptId())
                .toString();
    }

    public Long getSafetyStock() {
        return safetyStock;
    }

    public void setSafetyStock(Long safetyStock) {
        this.safetyStock = safetyStock;
    }
}