package com.ruoyi.common.enums;

/**
 * 入库类型
 */
public enum WhWarehousingOrderType
{
    OTHER("1", "其他单"), PURCHASE("2", "采购单"), TRANSFER("3", "请拔单"), SELLBACK("4", "销退单");

    private final String code;
    private final String info;

    WhWarehousingOrderType(String code, String info)
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
