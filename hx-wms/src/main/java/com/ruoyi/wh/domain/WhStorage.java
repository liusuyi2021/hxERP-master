package com.ruoyi.wh.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库位(储位)设置对象 wms_wh_storage
 *
 * @author miki
 * @date 2021-05-23
 */
public class WhStorage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 库位(储位)设置id */
    private Long id;

    /** 库位编码 */
    @Excel(name = "库位编码")
    private String storageCode;

    /** 库位名称 */
    @Excel(name = "库位名称")
    private String storageName;

    /** 库位条码 */
    @Excel(name = "库位条码")
    private String storageBarcode;

    /** 所属库区 */
    @Excel(name = "所属库区")
    private Long reservoirId;

    /** 库位类型(数据字典：收货、发货、存储、暂存、良品、不良等) */
    @Excel(name = "库位类型(数据字典：收货、发货、存储、暂存、良品、不良等)")
    private String storageType;

    /** 库位属性(数据字典：冷藏、恒温、常温、大件等) */
    @Excel(name = "库位属性(数据字典：冷藏、恒温、常温、大件等)")
    private String storageAttribute;

    /** 库位尺寸主键 */
    @Excel(name = "库位尺寸主键")
    private Long storageSizeId;

    /** 出库口 */
    @Excel(name = "出库口")
    private String outbound;

    /** 空库位标识(Y是 N否 ) */
    @Excel(name = "空库位标识(Y是 N否 )")
    private String isEmpty;

    /** 是否停用(0:正常 1:停用) */
    @Excel(name = "是否停用(0:正常 1:停用)")
    private String isDisable;

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
    public void setStorageCode(String storageCode)
    {
        this.storageCode = storageCode;
    }

    public String getStorageCode()
    {
        return storageCode;
    }
    public void setStorageName(String storageName)
    {
        this.storageName = storageName;
    }

    public String getStorageName()
    {
        return storageName;
    }
    public void setStorageBarcode(String storageBarcode)
    {
        this.storageBarcode = storageBarcode;
    }

    public String getStorageBarcode()
    {
        return storageBarcode;
    }
    public void setReservoirId(Long reservoirId)
    {
        this.reservoirId = reservoirId;
    }

    public Long getReservoirId()
    {
        return reservoirId;
    }
    public void setStorageType(String storageType)
    {
        this.storageType = storageType;
    }

    public String getStorageType()
    {
        return storageType;
    }
    public void setStorageAttribute(String storageAttribute)
    {
        this.storageAttribute = storageAttribute;
    }

    public String getStorageAttribute()
    {
        return storageAttribute;
    }
    public void setStorageSizeId(Long storageSizeId)
    {
        this.storageSizeId = storageSizeId;
    }

    public Long getStorageSizeId()
    {
        return storageSizeId;
    }
    public void setOutbound(String outbound)
    {
        this.outbound = outbound;
    }

    public String getOutbound()
    {
        return outbound;
    }
    public void setIsEmpty(String isEmpty)
    {
        this.isEmpty = isEmpty;
    }

    public String getIsEmpty()
    {
        return isEmpty;
    }
    public void setIsDisable(String isDisable)
    {
        this.isDisable = isDisable;
    }

    public String getIsDisable()
    {
        return isDisable;
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
                .append("storageCode", getStorageCode())
                .append("storageName", getStorageName())
                .append("storageBarcode", getStorageBarcode())
                .append("reservoirId", getReservoirId())
                .append("storageType", getStorageType())
                .append("storageAttribute", getStorageAttribute())
                .append("storageSizeId", getStorageSizeId())
                .append("outbound", getOutbound())
                .append("isEmpty", getIsEmpty())
                .append("isDisable", getIsDisable())
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