package com.ruoyi.basis.enums;

/**
 * 成本类别
 * 
 * @author miki
 */
public enum CostType
{
    //成本类别【销售价、批发价、成本价】
    SELLINGPRICE("1", "销售价"), WHOLESALEPRICE("2", "批发价"), COSTPRICE("3", "成本价"),RETAILPRICE("4", "零售价");

    private final String code;
    private final String info;

    CostType(String code, String info)
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
