package com.ruoyi.wh.mapper;

import java.util.List;
import com.ruoyi.wh.domain.WhOutboundOrder;

/**
 * 商品出库单主表Mapper接口
 * 
 * @author miki
 * @date 2021-06-07
 */
public interface WhOutboundOrderMapper 
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
     * 删除商品出库单主表
     * 
     * @param id 商品出库单主表ID
     * @return 结果
     */
    public int deleteWhOutboundOrderById(Long id);

    /**
     * 批量删除商品出库单主表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWhOutboundOrderByIds(String[] ids);
}
