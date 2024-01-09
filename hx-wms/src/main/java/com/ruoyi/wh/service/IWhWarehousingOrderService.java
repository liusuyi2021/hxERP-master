package com.ruoyi.wh.service;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.wh.domain.WhWarehousingOrder;

/**
 * 商品入库单主表Service接口
 * 
 * @author miki
 * @date 2021-05-26
 */
public interface IWhWarehousingOrderService 
{
    /**
     * 查询商品入库单主表
     * 
     * @param id 商品入库单主表ID
     * @return 商品入库单主表
     */
    public WhWarehousingOrder selectWhWarehousingOrderById(Long id);

    /**
     * 查询商品入库单主表列表
     * 
     * @param whWarehousingOrder 商品入库单主表
     * @return 商品入库单主表集合
     */
    public List<WhWarehousingOrder> selectWhWarehousingOrderList(WhWarehousingOrder whWarehousingOrder);

    /**
     * 新增商品入库单主表
     * 
     * @param whWarehousingOrder 商品入库单主表
     * @return 结果
     */
    public int insertWhWarehousingOrder(WhWarehousingOrder whWarehousingOrder);

    /**
     * 修改商品入库单主表
     * 
     * @param whWarehousingOrder 商品入库单主表
     * @return 结果
     */
    public int updateWhWarehousingOrder(WhWarehousingOrder whWarehousingOrder);

    /**
     * 修改商品入库单主表【包含子表商品价格重新计算】
     *
     * @param whWarehousingOrder 商品入库单主表
     * @return 结果
     */
    public int updateWhWarehousingOrderShop(WhWarehousingOrder whWarehousingOrder);

    /**
     * 批量删除商品入库单主表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWhWarehousingOrderByIds(String ids);

    /**
     * 删除商品入库单主表信息
     * 
     * @param id 商品入库单主表ID
     * @return 结果
     */
    public int deleteWhWarehousingOrderById(Long id);

    /**
     * 计算统计入库单主表各项价格
     * @param whWarehousingOrder 商品入库单主表
     * @param rate 入库主表供应商税率
     * @return 结果
     */
    public WhWarehousingOrder calculate(WhWarehousingOrder whWarehousingOrder, BigDecimal rate);

}
