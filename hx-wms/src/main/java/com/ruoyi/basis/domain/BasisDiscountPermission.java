package com.ruoyi.basis.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 折扣权限设定对象 wms_basis_discount_permission
 *
 * @author miki
 * @date 2021-06-16
 */
public class BasisDiscountPermission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户主键 */
    @Excel(name = "用户主键")
    private Long userId;

    /** 最低折扣率 */
    @Excel(name = "最低折扣率")
    private BigDecimal minDiscountRate;

    /** 最大折让金额 */
    @Excel(name = "最大折让金额")
    private BigDecimal maxDiscountPrice;

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
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setMinDiscountRate(BigDecimal minDiscountRate)
    {
        this.minDiscountRate = minDiscountRate;
    }

    public BigDecimal getMinDiscountRate()
    {
        return minDiscountRate;
    }
    public void setMaxDiscountPrice(BigDecimal maxDiscountPrice)
    {
        this.maxDiscountPrice = maxDiscountPrice;
    }

    public BigDecimal getMaxDiscountPrice()
    {
        return maxDiscountPrice;
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
                .append("userId", getUserId())
                .append("minDiscountRate", getMinDiscountRate())
                .append("maxDiscountPrice", getMaxDiscountPrice())
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