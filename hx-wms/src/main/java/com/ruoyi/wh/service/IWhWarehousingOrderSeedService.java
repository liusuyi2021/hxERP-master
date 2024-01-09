package com.ruoyi.wh.service;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.wh.domain.WhWarehousingOrder;
import com.ruoyi.wh.domain.WhWarehousingOrderSeed;
import com.ruoyi.wh.model.WarehousingOrderSeedModel;

/**
 * 商品入库单子Service接口
 * 
 * @author miki
 * @date 2021-05-26
 */
public interface IWhWarehousingOrderSeedService 
{
    /**
     * 查询商品入库单子
     * 
     * @param id 商品入库单子ID
     * @return 商品入库单子
     */
    public WhWarehousingOrderSeed selectWhWarehousingOrderSeedById(Long id);

    /**
     * 查询商品入库单子列表
     * 
     * @param whWarehousingOrderSeed 商品入库单子
     * @return 商品入库单子集合
     */
    public List<WhWarehousingOrderSeed> selectWhWarehousingOrderSeedList(WhWarehousingOrderSeed whWarehousingOrderSeed);

    /**
     * 新增商品入库单子
     * 
     * @param whWarehousingOrderSeed 商品入库单子
     * @return 结果
     */
    public int insertWhWarehousingOrderSeed(WhWarehousingOrderSeed whWarehousingOrderSeed);

    /**
     * 修改商品入库单子
     * 
     * @param whWarehousingOrderSeed 商品入库单子
     * @return 结果
     */
    public int updateWhWarehousingOrderSeed(WhWarehousingOrderSeed whWarehousingOrderSeed);

    /**
     * 修改商品入库单子【并且重新计算主表中的价格信息】
     *
     * @param whWarehousingOrderSeed 商品入库单子
     * @return 结果
     */
    public int updateWhWarehousingOrderSeedRecalculate(WhWarehousingOrderSeed whWarehousingOrderSeed);

    /**
     * 批量删除商品入库单子
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWhWarehousingOrderSeedByIds(String ids);

    /**
     * 删除商品入库单子信息
     * 
     * @param id 商品入库单子ID
     * @return 结果
     */
    public int deleteWhWarehousingOrderSeedById(Long id);

    /**
     *
     * 根据商品入库主表ID 查询合计含税金额
     * @param warehousingOrderId 商品入库主表ID
     * @return 结果
     */
    public BigDecimal getSumAmount(Long warehousingOrderId);

    /**
     * 计算统计子表各项价格
     * @param whWarehousingOrderSeed 商品入库单子
     * @param rate whWarehousingOrder入库主表供应商税率
     * @return whWarehousingOrderSeed 商品入库单子
     */
    public  WhWarehousingOrderSeed calculate(WhWarehousingOrder whWarehousingOrder, WhWarehousingOrderSeed whWarehousingOrderSeed, BigDecimal rate);

    /**
     *
     * 根据商品入库主表ID 查询商品数量
     * @param warehousingOrderId 商品入库主表ID
     * @return 结果
     */
    public int getOrderSeedShopCount(Long warehousingOrderId);

    /**
     * 商品确认分拣完成入库Service
     *
     * @param warehousingOrderSeedModel 商品入库单子Mode
     * @return 结果
     */
    public int updateCompleteSave(WarehousingOrderSeedModel warehousingOrderSeedModel);

    /**
     * 批量新增商品入库单子
     *
     * @param whWarehousingOrderSeedList 商品入库单子表集合
     * @return 结果
     */
    public int insertWhWarehousingOrderSeedBatch(List<WhWarehousingOrderSeed> whWarehousingOrderSeedList);

}
