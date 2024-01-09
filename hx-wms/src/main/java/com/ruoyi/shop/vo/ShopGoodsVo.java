package com.ruoyi.shop.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商品信息对象 wms_shop_goods
 *
 * @author miki
 * @date 2021-05-26
 */
public class ShopGoodsVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品类别ID */
    private Long goodsTypeId;

    /** 商品编码 */
    private String goodsCode;

    /** 商品名称 */
    private String goodsName;

    /** 商品条码 */
    private String goodsBarcode;

    /** 商品规格 */
    private String goodsSearchstandard;

    /** 商品颜色 */
    private String goodsColor;

    /** 所属库位主键 */
    private Long storageId;

    /** 商品单位【关联字典管理】 */
    private String goodsUnit;

    /** 商品重量(单位:千克) */
    private BigDecimal goodsWeight;

    /** 成本价 */
    private BigDecimal costPrice;

    /** 零售价 */
    private BigDecimal retailPrice;

    /** 销售价 */
    private BigDecimal sellingPrice;

    /** 批发价 */
    @Excel(name = "批发价")
    private BigDecimal wholesalePrice;

    /** 成本价锁定 */
    private String isCost;

    /** 部门ID */
    private Long deptId;

    /**-------------------------- 扩张字段 ---------------------------------*/
    /** 安全库存 */
    private Long safetyStock;

    /** 库存数量 */
    private Long stockNumber;

    /** 商品类别编码 */
    private String goodsTypeCode;

    /** 商品类别名称 */
    private String goodsTypeName;

    /** 库位编码 */
    private String storageCode;

    /** 库位名称 */
    private String storageName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Long goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsBarcode() {
        return goodsBarcode;
    }

    public void setGoodsBarcode(String goodsBarcode) {
        this.goodsBarcode = goodsBarcode;
    }

    public String getGoodsSearchstandard() {
        return goodsSearchstandard;
    }

    public void setGoodsSearchstandard(String goodsSearchstandard) {
        this.goodsSearchstandard = goodsSearchstandard;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public BigDecimal getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(BigDecimal goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getIsCost() {
        return isCost;
    }

    public void setIsCost(String isCost) {
        this.isCost = isCost;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getSafetyStock() {
        return safetyStock;
    }

    public void setSafetyStock(Long safetyStock) {
        this.safetyStock = safetyStock;
    }

    public Long getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Long stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getGoodsTypeCode() {
        return goodsTypeCode;
    }

    public void setGoodsTypeCode(String goodsTypeCode) {
        this.goodsTypeCode = goodsTypeCode;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }
}