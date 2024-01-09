package com.ruoyi.wh.enums;

/**
 * 出库订单状态
 * 
 * @author miki
 */
public enum OutboundOrderStatus
{
    //新发货单、待拣货、已拣货、已发货、签收回单
    THEDELIVERY("1", "新发货单"), FORPICKING("2", "待拣货"), HASBEENPICKING("3", "已拣货"), HASBEENSHIPPED("4", "已发货"), SIGNFOR("5", "签收回单");

    private final String code;
    private final String info;

    OutboundOrderStatus(String code, String info)
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
