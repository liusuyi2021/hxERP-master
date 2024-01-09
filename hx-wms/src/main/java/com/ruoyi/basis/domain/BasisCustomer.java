package com.ruoyi.basis.domain;

import java.math.BigDecimal;

import com.ruoyi.basis.enums.CostType;
import com.ruoyi.basis.enums.OrderRelationship;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;


/**
 * 客户资料对象 wms_basis_customer
 *
 * @author miki
 * @date 2021-05-31
 */
public class BasisCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户资料id */
    private Long id;

    /** 单位关联主键 */
    @Excel(name = "单位关联主键")
    private Long warehouseId;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String customerCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 营业执照(税号) */
    @Excel(name = "营业执照(税号)")
    private String businessLicense;

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
    private String customerLevel;

    /** 联系人1 */
    @Excel(name = "联系人1")
    private String contactPersonOne;

    /** 电话1 */
    @Excel(name = "电话1")
    private String telephoneOne;

    /** 发票抬头 */
    @Excel(name = "发票抬头")
    private String invoiceLookedUp;

    /** 发票税率 */
    @Excel(name = "发票税率")
    private BigDecimal invoiceTax;

    /** 发票种类【数据字典】 */
    @Excel(name = "发票种类【数据字典】")
    private String invoiceType;

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
    public void setCustomerCode(String customerCode)
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode()
    {
        return customerCode;
    }
    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerName()
    {
        return customerName;
    }
    public void setBusinessLicense(String businessLicense)
    {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense()
    {
        return businessLicense;
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
    public void setCustomerLevel(String customerLevel)
    {
        this.customerLevel = customerLevel;
    }

    public String getCustomerLevel()
    {
        return customerLevel;
    }
    public void setContactPersonOne(String contactPersonOne)
    {
        this.contactPersonOne = contactPersonOne;
    }

    public String getContactPersonOne()
    {
        return contactPersonOne;
    }
    public void setTelephoneOne(String telephoneOne)
    {
        this.telephoneOne = telephoneOne;
    }

    public String getTelephoneOne()
    {
        return telephoneOne;
    }

    public void setInvoiceLookedUp(String invoiceLookedUp)
    {
        this.invoiceLookedUp = invoiceLookedUp;
    }

    public String getInvoiceLookedUp()
    {
        return invoiceLookedUp;
    }
    public void setInvoiceTax(BigDecimal invoiceTax)
    {
        this.invoiceTax = invoiceTax;
    }

    public BigDecimal getInvoiceTax()
    {
        return invoiceTax;
    }
    public void setInvoiceType(String invoiceType)
    {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceType()
    {
        return invoiceType;
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
                .append("customerCode", getCustomerCode())
                .append("customerName", getCustomerName())
                .append("businessLicense", getBusinessLicense())
                .append("contactPerson", getContactPerson())
                .append("telephone", getTelephone())
                .append("mobilePhone", getMobilePhone())
                .append("email", getEmail())
                .append("address", getAddress())
                .append("customerLevel", getCustomerLevel())
                .append("contactPersonOne", getContactPersonOne())
                .append("telephoneOne", getTelephoneOne())
                .append("invoiceLookedUp", getInvoiceLookedUp())
                .append("invoiceTax", getInvoiceTax())
                .append("invoiceType", getInvoiceType())
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