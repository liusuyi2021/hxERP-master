package com.ruoyi.wh.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库位尺寸对象 wms_wh_storage_size
 * 
 * @author miki
 * @date 2021-05-23
 */
public class WhStorageSize extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 库位尺寸id */
    private Long id;

    /** 库位尺寸名称 */
    @Excel(name = "库位尺寸名称")
    private String storageSizeName;

    /** 库位长度(单位:毫米) */
    @Excel(name = "库位长度(单位:毫米)")
    private BigDecimal length;

    /** 库位宽度(单位:毫米) */
    @Excel(name = "库位宽度(单位:毫米)")
    private BigDecimal width;

    /** 库位高度(单位:毫米) */
    @Excel(name = "库位高度(单位:毫米)")
    private BigDecimal height;

    /** 最大重量(单位:千克) */
    @Excel(name = "最大重量(单位:千克)")
    private BigDecimal maxWeight;

    /** 最大托盘(单位:件) */
    @Excel(name = "最大托盘(单位:件)")
    private Long maxTray;

    /** 最大体积(单位:立方毫米) */
    @Excel(name = "最大体积(单位:立方毫米)")
    private BigDecimal maxVolume;

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
    public void setStorageSizeName(String storageSizeName) 
    {
        this.storageSizeName = storageSizeName;
    }

    public String getStorageSizeName() 
    {
        return storageSizeName;
    }
    public void setLength(BigDecimal length) 
    {
        this.length = length;
    }

    public BigDecimal getLength() 
    {
        return length;
    }
    public void setWidth(BigDecimal width) 
    {
        this.width = width;
    }

    public BigDecimal getWidth() 
    {
        return width;
    }
    public void setHeight(BigDecimal height) 
    {
        this.height = height;
    }

    public BigDecimal getHeight() 
    {
        return height;
    }
    public void setMaxWeight(BigDecimal maxWeight) 
    {
        this.maxWeight = maxWeight;
    }

    public BigDecimal getMaxWeight() 
    {
        return maxWeight;
    }
    public void setMaxTray(Long maxTray) 
    {
        this.maxTray = maxTray;
    }

    public Long getMaxTray() 
    {
        return maxTray;
    }
    public void setMaxVolume(BigDecimal maxVolume) 
    {
        this.maxVolume = maxVolume;
    }

    public BigDecimal getMaxVolume() 
    {
        return maxVolume;
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
            .append("storageSizeName", getStorageSizeName())
            .append("length", getLength())
            .append("width", getWidth())
            .append("height", getHeight())
            .append("maxWeight", getMaxWeight())
            .append("maxTray", getMaxTray())
            .append("maxVolume", getMaxVolume())
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
