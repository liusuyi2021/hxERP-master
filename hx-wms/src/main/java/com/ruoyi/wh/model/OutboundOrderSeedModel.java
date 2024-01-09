package com.ruoyi.wh.model;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.wh.domain.WhOutboundOrderSeed;
import com.ruoyi.wh.domain.WhWarehousingOrderSeed;

import java.util.List;

/**
 * 接收商品入库单子对象 wms_wh_warehousing_order_seed
 *
 * @author miki
 * @date 2021-06-04
 */
public class OutboundOrderSeedModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long outboundOrderId;//商品出库主表ID

    private List<WhOutboundOrderSeed> outboundOrderSeedList;//接收出库商品数量信息

    public Long getOutboundOrderId() {
        return outboundOrderId;
    }

    public void setOutboundOrderId(Long outboundOrderId) {
        this.outboundOrderId = outboundOrderId;
    }

    public List<WhOutboundOrderSeed> getOutboundOrderSeedList() {
        return outboundOrderSeedList;
    }

    public void setOutboundOrderSeedList(List<WhOutboundOrderSeed> outboundOrderSeedList) {
        this.outboundOrderSeedList = outboundOrderSeedList;
    }
}