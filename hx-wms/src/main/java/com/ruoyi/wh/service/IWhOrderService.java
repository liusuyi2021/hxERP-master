package com.ruoyi.wh.service;

import com.ruoyi.wh.domain.WhOutboundOrderSeed;
import com.ruoyi.wh.model.OrderModel;

/**
 * 单据精灵Service类
 */
public interface IWhOrderService {

    /**
     * 新增编辑单据精灵
     * @param orderModel 单据信息数据
     * @return
     */
    public int edit(OrderModel orderModel);

}
