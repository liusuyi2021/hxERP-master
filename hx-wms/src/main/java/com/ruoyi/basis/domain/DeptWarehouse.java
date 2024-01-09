package com.ruoyi.basis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 单位(部门)与客户或供应商关联对象 oms_basis_dept_warehouse
 *
 * @author miki
 * @date 2021-06-16
 */
public class DeptWarehouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 单位绑定部门主键 */
    @Excel(name = "单位绑定部门主键")
    private Long warehouseDeptId;

    /** 单位关联表主键 */
    @Excel(name = "单位关联表主键")
    private Long warehouseId;

    /** 订单关系【采购、调拨】 */
    @Excel(name = "订单关系【采购、调拨】")
    private String orderRelationship;

    /** 成本类别【销售价、批发价、成本价】 */
    @Excel(name = "成本类别【销售价、批发价、成本价】")
    private String costType;

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
    public void setWarehouseDeptId(Long warehouseDeptId)
    {
        this.warehouseDeptId = warehouseDeptId;
    }

    public Long getWarehouseDeptId()
    {
        return warehouseDeptId;
    }
    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }
    public void setOrderRelationship(String orderRelationship)
    {
        this.orderRelationship = orderRelationship;
    }

    public String getOrderRelationship()
    {
        return orderRelationship;
    }
    public void setCostType(String costType)
    {
        this.costType = costType;
    }

    public String getCostType()
    {
        return costType;
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
                .append("warehouseDeptId", getWarehouseDeptId())
                .append("warehouseId", getWarehouseId())
                .append("orderRelationship", getOrderRelationship())
                .append("costType", getCostType())
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