package com.ruoyi.wh.service;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.wh.domain.WhOutboundOrder;
import com.ruoyi.wh.domain.WhOutboundOrderSeed;
import com.ruoyi.wh.domain.WhWarehousingOrderSeed;
import com.ruoyi.wh.model.OutboundOrderSeedModel;
import com.ruoyi.wh.model.WarehousingOrderSeedModel;

/**
 * 商品出库单子表Service接口
 * 
 * @author miki
 * @date 2021-06-07
 */
public interface IWhOutboundOrderSeedService 
{
    /**
     * 查询商品出库单子表
     * 
     * @param id 商品出库单子表ID
     * @return 商品出库单子表
     */
    public WhOutboundOrderSeed selectWhOutboundOrderSeedById(Long id);

    /**
     * 查询商品出库单子表列表
     * 
     * @param whOutboundOrderSeed 商品出库单子表
     * @return 商品出库单子表集合
     */
    public List<WhOutboundOrderSeed> selectWhOutboundOrderSeedList(WhOutboundOrderSeed whOutboundOrderSeed);

    /**
     * 新增商品出库单子表
     * 
     * @param whOutboundOrderSeed 商品出库单子表
     * @return 结果
     */
    public int insertWhOutboundOrderSeed(WhOutboundOrderSeed whOutboundOrderSeed);

    /**
     * 修改商品出库单子表
     * 
     * @param whOutboundOrderSeed 商品出库单子表
     * @return 结果
     */
    public int updateWhOutboundOrderSeed(WhOutboundOrderSeed whOutboundOrderSeed);

    /**
     * 修改商品出库单子表【并且重新计算主表中的价格信息】
     *
     * @param whOutboundOrderSeed 商品出库单子表
     * @return 结果
     */
    public int updateWhOutboundOrderSeedRecalculate(WhOutboundOrderSeed whOutboundOrderSeed);

    /**
     * 批量删除商品出库单子表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWhOutboundOrderSeedByIds(String ids);

    /**
     * 删除商品出库单子表信息
     * 
     * @param id 商品出库单子表ID
     * @return 结果
     */
    public int deleteWhOutboundOrderSeedById(Long id);

    /**
     *
     * 根据商品出库主表ID 查询合计含税金额
     * @param outboundOrderId 商品出库主表ID
     * @return 结果
     */
    public BigDecimal getSumAmount(Long outboundOrderId);

    /**
     *
     * 根据商品出库主表ID 查询商品数量
     * @param outboundOrderId 商品出库主表ID
     * @return 结果
     */
    public int getOrderSeedShopCount(Long outboundOrderId);

    /**
     * 商品确认拣货Service
     *
     * @param outboundOrderSeedModel 商品出库单子Mode
     * @return 结果
     */
    public int updateCompleteSave(OutboundOrderSeedModel outboundOrderSeedModel);


    /**
     * 计算统计子表各项价格
     * @param whOutboundOrderSeed 商品出库单子表
     * @param rate rate 出库主表供应商税率
     * @return whOutboundOrderSeed 商品出库单子
     */
    public WhOutboundOrderSeed calculate(WhOutboundOrder whOutboundOrder, WhOutboundOrderSeed whOutboundOrderSeed, BigDecimal rate);

    /**
     * 批量新增商品出库单子表
     *
     * @param whOutboundOrderSeedList 商品出库单子表集合
     * @return 结果
     */
    public int insertWhOutboundOrderSeedBatch(List<WhOutboundOrderSeed> whOutboundOrderSeedList);
}
