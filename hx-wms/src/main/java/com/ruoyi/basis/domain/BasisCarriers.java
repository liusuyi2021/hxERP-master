package com.ruoyi.basis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 承运商管理对象 wms_basis_carriers
 * 
 * @author miki
 * @date 2021-05-20
 */
public class BasisCarriers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 承运商管理id */
    private Long id;

    /** 单位关联主键 */
    @Excel(name = "单位关联主键")
    private Long warehouseId;

    /** 承运商编码 */
    @Excel(name = "承运商编码")
    private String carriersCode;

    /** 承运商名称 */
    @Excel(name = "承运商名称")
    private String carriersName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPerson;

    /** 电话 */
    @Excel(name = "电话")
    private String telephone;

    /** 手机 */
    @Excel(name = "手机")
    private String mobilePhone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 级别(关联字典) */
    @Excel(name = "级别(关联字典)")
    private String carriersLevel;

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
    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }
    public void setCarriersCode(String carriersCode)
    {
        this.carriersCode = carriersCode;
    }

    public String getCarriersCode()
    {
        return carriersCode;
    }
    public void setCarriersName(String carriersName)
    {
        this.carriersName = carriersName;
    }

    public String getCarriersName()
    {
        return carriersName;
    }
    public void setContactPerson(String contactPerson)
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson()
    {
        return contactPerson;
    }
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getTelephone()
    {
        return telephone;
    }
    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setCarriersLevel(String carriersLevel)
    {
        this.carriersLevel = carriersLevel;
    }

    public String getCarriersLevel()
    {
        return carriersLevel;
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
                .append("warehouseId", getWarehouseId())
                .append("carriersCode", getCarriersCode())
                .append("carriersName", getCarriersName())
                .append("contactPerson", getContactPerson())
                .append("telephone", getTelephone())
                .append("mobilePhone", getMobilePhone())
                .append("email", getEmail())
                .append("address", getAddress())
                .append("carriersLevel", getCarriersLevel())
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
