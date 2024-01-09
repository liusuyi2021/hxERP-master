package com.ruoyi.wh.mapper;

import java.util.List;
import com.ruoyi.wh.domain.WhOutboundStockout;
import com.ruoyi.wh.vo.WhOutboundStockoutVo;

/**
 * 缺货管制订单Mapper接口
 * 
 * @author miki
 * @date 2021-06-09
 */
public interface WhOutboundStockoutMapper 
{
    /**
     * 查询缺货管制订单
     * 
     * @param id 缺货管制订单ID
     * @return 缺货管制订单
     */
    public WhOutboundStockout selectWhOutboundStockoutById(Long id);

    /**
     * 查询缺货管制订单列表
     * 
     * @param whOutboundStockout 缺货管制订单
     * @return 缺货管制订单集合
     */
    public List<WhOutboundStockout> selectWhOutboundStockoutList(WhOutboundStockout whOutboundStockout);

    /**
     * 查询缺货管制订单列表Vo
     *
     * @param whOutboundStockout 缺货管制订单
     * @return 缺货管制订单集合
     */
    public List<WhOutboundStockoutVo> selectWhOutboundStockoutListVo(WhOutboundStockout whOutboundStockout);

    /**
     * 新增缺货管制订单
     * 
     * @param whOutboundStockout 缺货管制订单
     * @return 结果
     */
    public int insertWhOutboundStockout(WhOutboundStockout whOutboundStockout);

    /**
     * 修改缺货管制订单
     * 
     * @param whOutboundStockout 缺货管制订单
     * @return 结果
     */
    public int updateWhOutboundStockout(WhOutboundStockout whOutboundStockout);

    /**
     * 删除缺货管制订单
     * 
     * @param id 缺货管制订单ID
     * @return 结果
     */
    public int deleteWhOutboundStockoutById(Long id);

    /**
     * 批量删除缺货管制订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWhOutboundStockoutByIds(String[] ids);
}
