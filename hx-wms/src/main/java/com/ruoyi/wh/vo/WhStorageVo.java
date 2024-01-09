package com.ruoyi.wh.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 库位(储位)设置对象 wms_wh_storage
 *
 * @author miki
 * @date 2021-05-23
 */
public class WhStorageVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 库位(储位)设置id */
    private Long id;

    /** 库位编码 */
    private String storageCode;

    /** 库位名称 */
    private String storageName;

    /** 库位条码 */
    private String storageBarcode;

    /** 所属库区 */
    private Long reservoirId;

    /** 库位类型(数据字典：收货、发货、存储、暂存、良品、不良等) */
    private String storageType;

    /** 库位属性(数据字典：冷藏、恒温、常温、大件等) */
    private String storageAttribute;

    /** 库位尺寸主键 */
    private Long storageSizeId;

    /** 出库口 */
    private String outbound;

    /** 空库位标识(0: true 是 1:false 否 ) */
    private String isEmpty;

    /** 是否停用(0:正常 1:停用) */
    private String isDisable;

    /** 部门ID */
    private Long deptId;

    /*********扩展字段************/

    /** 库区编码 */
    private String reservoirCode;

    /** 库区名称 */
    private String reservoirName;

    /** 库位尺寸名称 */
    private String storageSizeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStorageBarcode() {
        return storageBarcode;
    }

    public void setStorageBarcode(String storageBarcode) {
        this.storageBarcode = storageBarcode;
    }

    public Long getReservoirId() {
        return reservoirId;
    }

    public void setReservoirId(Long reservoirId) {
        this.reservoirId = reservoirId;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getStorageAttribute() {
        return storageAttribute;
    }

    public void setStorageAttribute(String storageAttribute) {
        this.storageAttribute = storageAttribute;
    }

    public Long getStorageSizeId() {
        return storageSizeId;
    }

    public void setStorageSizeId(Long storageSizeId) {
        this.storageSizeId = storageSizeId;
    }

    public String getOutbound() {
        return outbound;
    }

    public void setOutbound(String outbound) {
        this.outbound = outbound;
    }

    public String getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(String isEmpty) {
        this.isEmpty = isEmpty;
    }

    public String getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(String isDisable) {
        this.isDisable = isDisable;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getReservoirCode() {
        return reservoirCode;
    }

    public void setReservoirCode(String reservoirCode) {
        this.reservoirCode = reservoirCode;
    }

    public String getReservoirName() {
        return reservoirName;
    }

    public void setReservoirName(String reservoirName) {
        this.reservoirName = reservoirName;
    }

    public String getStorageSizeName() {
        return storageSizeName;
    }

    public void setStorageSizeName(String storageSizeName) {
        this.storageSizeName = storageSizeName;
    }
}