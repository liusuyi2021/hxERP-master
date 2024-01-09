package com.ruoyi.wh.enums;

/**
 * 入库订单状态
 * 
 * @author miki
 */
public enum WarehousingOrderStatus
{
    //待到货、待卸货、待分拣、已分拣
    ARRIVAL("1", "待到货"), UNLOADED("2", "待卸货"), FORSORTING("3", "待分拣"), HASBEENSORTING("4", "已分拣");

    private final String code;
    private final String info;

    WarehousingOrderStatus(String code, String info)
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
