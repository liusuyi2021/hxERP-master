package com.ruoyi.wh.service;

import com.ruoyi.wh.domain.WhOutboundOrder;
import com.ruoyi.wh.domain.WhWarehousingOrder;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品出库单主表Service接口
 * 
 * @author miki
 * @date 2021-06-07
 */
public interface IWhOutboundOrderService 
{
    /**
     * 查询商品出库单主表
     * 
     * @param id 商品出库单主表ID
     * @return 商品出库单主表
     */
    public WhOutboundOrder selectWhOutboundOrderById(Long id);

    /**
     * 查询商品出库单主表列表
     * 
     * @param whOutboundOrder 商品出库单主表
     * @return 商品出库单主表集合
     */
    public List<WhOutboundOrder> selectWhOutboundOrderList(WhOutboundOrder whOutboundOrder);

    /**
     * 新增商品出库单主表
     * 
     * @param whOutboundOrder 商品出库单主表
     * @return 结果
     */
    public int insertWhOutboundOrder(WhOutboundOrder whOutboundOrder);

    /**
     * 修改商品出库单主表
     * 
     * @param whOutboundOrder 商品出库单主表
     * @return 结果
     */
    public int updateWhOutboundOrder(WhOutboundOrder whOutboundOrder);

    /**
     * 修改商品入库单主表【包含子表商品价格重新计算】
     *
     * @param whOutboundOrder 商品出库单主表
     * @return 结果
     */
    public int updateWhOutboundOrderShop(WhOutboundOrder whOutboundOrder);

    /**
     * 批量删除商品出库单主表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWhOutboundOrderByIds(String ids);

    /**
     * 删除商品出库单主表信息
     * 
     * @param id 商品出库单主表ID
     * @return 结果
     */
    public int deleteWhOutboundOrderById(Long id);

    /**
     *  修改商品入库单状态-确认作废
     *
     * @param whOutboundOrder 商品出库单主表
     * @return 结果
     */
    public int editRemove(WhOutboundOrder whOutboundOrder);

    /**
     * 修改商品出库单状态-确认待拣货
     *
     * @param whOutboundOrder 商品出库单主表
     * @return 结果
     */
    public int changeStatus(WhOutboundOrder whOutboundOrder);

    /**
     * 修改商品出库单主表-装车发货
     *
     * @param whOutboundOrder 商品出库单主表
     * @return 结果
     */
    public int editLoading(WhOutboundOrder whOutboundOrder);

    /**
     * 计算统计出库单主表各项价格
     * @param whOutboundOrder 商品出库单主表
     * @param rate 出库主表供应商税率
     * @return 结果
     */
    public WhOutboundOrder calculate(WhOutboundOrder whOutboundOrder, BigDecimal rate);
}
