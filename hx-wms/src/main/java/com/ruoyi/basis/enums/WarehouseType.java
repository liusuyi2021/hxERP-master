package com.ruoyi.basis.enums;

/**
 * 关联类型
 * 
 * @author miki
 */
public enum WarehouseType
{
    CUSTOMER("1", "客户"), SUPPLIER("2", "供应商"), CARRIERS("3", "承运商");

    private final String code;
    private final String info;

    WarehouseType(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
