package com.ruoyi.wh.mapper;

import java.util.List;
import com.ruoyi.wh.domain.WhWarehousingOrder;

/**
 * 商品入库单主表Mapper接口
 * 
 * @author miki
 * @date 2021-05-26
 */
public interface WhWarehousingOrderMapper 
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
     * 删除商品入库单主表
     * 
     * @param id 商品入库单主表ID
     * @return 结果
     */
    public int deleteWhWarehousingOrderById(Long id);

    /**
     * 批量删除商品入库单主表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWhWarehousingOrderByIds(String[] ids);
}
