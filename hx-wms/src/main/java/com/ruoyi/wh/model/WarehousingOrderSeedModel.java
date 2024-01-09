package com.ruoyi.wh.model;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.wh.domain.WhWarehousingOrderSeed;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

/**
 * 接收商品入库单子对象 wms_wh_warehousing_order_seed
 *
 * @author miki
 * @date 2021-06-04
 */
public class WarehousingOrderSeedModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long warehousingOrderId;//入库单主键

    private List<WhWarehousingOrderSeed> warehousingOrderSeed;//接收入库商品数量信息

    public Long getWarehousingOrderId() {
        return warehousingOrderId;
    }

    public void setWarehousingOrderId(Long warehousingOrderId) {
        this.warehousingOrderId = warehousingOrderId;
    }

    public List<WhWarehousingOrderSeed> getWarehousingOrderSeed() {
        return warehousingOrderSeed;
    }

    public void setWarehousingOrderSeed(List<WhWarehousingOrderSeed> warehousingOrderSeed) {
        this.warehousingOrderSeed = warehousingOrderSeed;
    }
}