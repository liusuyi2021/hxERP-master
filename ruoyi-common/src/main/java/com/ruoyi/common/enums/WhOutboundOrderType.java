package com.ruoyi.common.enums;

/**
 * 出库类型
 */
public enum WhOutboundOrderType
{
    OTHER("1", "其他单"), SALES("2", "销售单"), TRANSFER("3", "受拔单"), RETURN("4", "退货单");

    private final String code;
    private final String info;

    WhOutboundOrderType(String code, String info)
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
