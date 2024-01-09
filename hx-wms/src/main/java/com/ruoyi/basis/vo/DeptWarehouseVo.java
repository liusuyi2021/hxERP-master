package com.ruoyi.basis.vo;

import com.ruoyi.basis.enums.CostType;
import com.ruoyi.basis.enums.OrderRelationship;
import com.ruoyi.basis.enums.WarehouseType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 单位(部门)与客户或供应商关联对象 oms_basis_dept_warehouse
 *
 * @author miki
 * @date 2021-06-16
 */
public class DeptWarehouseVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 单位绑定部门主键 */
    private Long warehouseDeptId;

    /** 单位关联表主键 */
    private Long warehouseId;

    /** 订单关系【采购、调拨】 */
    private String orderRelationship;

    /** 成本类别【销售价、批发价、成本价】 */
    private String costType;

    /** 部门ID */
    private Long deptId;

    /** 单位绑定部门名称*/
    private String warehouseDeptName;

    /** 类型(1.客户2.供应商3.承运商) */
    private String warehouseType;

    /** 类型描述 (1.客户2.供应商3.承运商) */
    private String warehouseTypeDesc;

    /** 客户主键ID*/
    private String customerId;

    /** 客户名称*/
    private String customerName;

    /** 供应商主键ID*/
    private String supplierId;

    /** 供应商名称*/
    private String supplierName;

    /** 客户发票税率 */
    private BigDecimal customerInvoiceTax;

    /** 供应商发票税率 */
    private BigDecimal supplierInvoiceTax;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWarehouseDeptId() {
        return warehouseDeptId;
    }

    public void setWarehouseDeptId(Long warehouseDeptId) {
        this.warehouseDeptId = warehouseDeptId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getOrderRelationship() {
        return orderRelationship;
    }

    public void setOrderRelationship(String orderRelationship) {
        this.orderRelationship = orderRelationship;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(String warehouseType) {
        this.warehouseType = warehouseType;
    }

    public String getWarehouseTypeDesc() {
        for (WarehouseType warehouseType : WarehouseType.CUSTOMER.values()) {
            if(warehouseType.getCode().equals(this.getWarehouseType())){
                warehouseTypeDesc = warehouseType.getInfo();
            }
        }
        return warehouseTypeDesc;
    }

    public void setWarehouseTypeDesc(String warehouseTypeDesc) {
        this.warehouseTypeDesc = warehouseTypeDesc;
    }

    public String getWarehouseDeptName() {
        return warehouseDeptName;
    }

    public void setWarehouseDeptName(String warehouseDeptName) {
        this.warehouseDeptName = warehouseDeptName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getCustomerInvoiceTax() {
        return customerInvoiceTax;
    }

    public void setCustomerInvoiceTax(BigDecimal customerInvoiceTax) {
        this.customerInvoiceTax = customerInvoiceTax;
    }

    public BigDecimal getSupplierInvoiceTax() {
        return supplierInvoiceTax;
    }

    public void setSupplierInvoiceTax(BigDecimal supplierInvoiceTax) {
        this.supplierInvoiceTax = supplierInvoiceTax;
    }
}