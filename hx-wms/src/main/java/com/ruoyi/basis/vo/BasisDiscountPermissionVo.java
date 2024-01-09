package com.ruoyi.basis.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 折扣权限设定对象 wms_basis_discount_permission
 * 
 * @author miki
 * @date 2021-06-15
 */
public class BasisDiscountPermissionVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户主键 */
    private Long userId;

    /** 最低折扣率 */
    private BigDecimal minDiscountRate;

    /** 最大折让金额 */
    private BigDecimal maxDiscountPrice;

    /** 用户名称 */
    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
