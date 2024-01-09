package com.ruoyi.basis.enums;

/**
 * 订单关系
 * 
 * @author miki
 */
public enum OrderRelationship
{
    //订单关系【采购、调拨】
    PURCHASE("1", "采购"), TRANSFER("2", "调拔");

    private final String code;
    private final String info;

    OrderRelationship(String code, String info)
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
