package com.ruoyi.wh.service.impl;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.shop.domain.ShopGoodsSeed;
import com.ruoyi.shop.service.IShopGoodsSeedService;
import com.ruoyi.wh.domain.WhOutboundOrderSeed;
import com.ruoyi.wh.service.IWhOutboundOrderSeedService;
import com.ruoyi.wh.vo.WhOutboundStockoutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wh.mapper.WhOutboundStockoutMapper;
import com.ruoyi.wh.domain.WhOutboundStockout;
import com.ruoyi.wh.service.IWhOutboundStockoutService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 缺货管制订单Service业务层处理
 * 
 * @author miki
 * @date 2021-06-09
 */
@Service
public class WhOutboundStockoutServiceImpl implements IWhOutboundStockoutService 
{
    @Autowired
    private WhOutboundStockoutMapper whOutboundStockoutMapper;

    @Autowired
    private IShopGoodsSeedService shopGoodsSeedService;

    @Autowired
    private IWhOutboundOrderSeedService whOutboundOrderSeedService;

    /**
     * 查询缺货管制订单
     * 
     * @param id 缺货管制订单ID
     * @return 缺货管制订单
     */
    @Override
    public WhOutboundStockout selectWhOutboundStockoutById(Long id)
    {
        return whOutboundStockoutMapper.selectWhOutboundStockoutById(id);
    }

    /**
     * 查询缺货管制订单列表
     * 
     * @param whOutboundStockout 缺货管制订单
     * @return 缺货管制订单
     */
    @Override
    public List<WhOutboundStockout> selectWhOutboundStockoutList(WhOutboundStockout whOutboundStockout)
    {
        return whOutboundStockoutMapper.selectWhOutboundStockoutList(whOutboundStockout);
    }

    @Override
    public List<WhOutboundStockoutVo> selectWhOutboundStockoutListVo(WhOutboundStockout whOutboundStockout) {
        return whOutboundStockoutMapper.selectWhOutboundStockoutListVo(whOutboundStockout);
    }

    /**
     * 新增缺货管制订单
     * 
     * @param whOutboundStockout 缺货管制订单
     * @return 结果
     */
    @Override
    public int insertWhOutboundStockout(WhOutboundStockout whOutboundStockout)
    {
        whOutboundStockout.setCreateTime(DateUtils.getNowDate());
        return whOutboundStockoutMapper.insertWhOutboundStockout(whOutboundStockout);
    }

    /**
     * 修改缺货管制订单
     * 
     * @param whOutboundStockout 缺货管制订单
     * @return 结果
     */
    @Override
    public int updateWhOutboundStockout(WhOutboundStockout whOutboundStockout)
    {
        whOutboundStockout.setUpdateTime(DateUtils.getNowDate());
        return whOutboundStockoutMapper.updateWhOutboundStockout(whOutboundStockout);
    }

    /**
     * 删除缺货管制订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWhOutboundStockoutByIds(String ids)
    {
        return whOutboundStockoutMapper.deleteWhOutboundStockoutByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除缺货管制订单信息
     * 
     * @param id 缺货管制订单ID
     * @return 结果
     */
    @Override
    public int deleteWhOutboundStockoutById(Long id)
    {
        return whOutboundStockoutMapper.deleteWhOutboundStockoutById(id);
    }

    @Override
    @Transactional
    public int editStockout(String ids) {
        int result = 0;
       Long[] idArray = Convert.toLongArray(ids);
        for (Long id :idArray ) {
            WhOutboundStockout whOutboundStockout = selectWhOutboundStockoutById(id);
            //根据商品ID 查询库存数量，判断是否满足解欠要求
            ShopGoodsSeed shopGoodsSeed = shopGoodsSeedService.selectShopGoodsSeedById(whOutboundStockout.getShopGoodsId());
            Long kyNumber = shopGoodsSeed.getStockNumber()-shopGoodsSeed.getBeenPickingNumber();//库存可用量=库存数量-已拣货数量
            if(kyNumber >= whOutboundStockout.getOweNumber().intValue()){
                whOutboundStockout.setStatus(UserConstants.YES);
                whOutboundStockout.setUpdateBy(ShiroUtils.getLoginName());
                updateWhOutboundStockout(whOutboundStockout);

                shopGoodsSeed.setForPickingNumber(shopGoodsSeed.getForPickingNumber()- whOutboundStockout.getOweNumber());//待拣货数量
                shopGoodsSeed.setBeenPickingNumber(shopGoodsSeed.getBeenPickingNumber()+ whOutboundStockout.getOweNumber());//已拣货数量
                shopGoodsSeed.setUpdateBy(whOutboundStockout.getUpdateBy());
                result = shopGoodsSeedService.updateShopGoodsSeed(shopGoodsSeed);

                WhOutboundOrderSeed whOutboundOrderSeed = whOutboundOrderSeedService.selectWhOutboundOrderSeedById(whOutboundStockout.getOutboundOrderSeedId());
                whOutboundOrderSeed.setActualNumber(whOutboundOrderSeed.getActualNumber()+whOutboundStockout.getOweNumber());//实际数量的值等于计划数量才正确
                whOutboundOrderSeed.setOweNumber(whOutboundOrderSeed.getOweNumber()-whOutboundStockout.getOweNumber());//欠货数量的值等于0才正确
                whOutboundOrderSeed.setUpdateBy(whOutboundStockout.getUpdateBy());
                result = whOutboundOrderSeedService.updateWhOutboundOrderSeed(whOutboundOrderSeed);
            }else{
                throw new BusinessException("库存数量不足，解欠操作失败");
            }
        }
        return result;
    }
}
