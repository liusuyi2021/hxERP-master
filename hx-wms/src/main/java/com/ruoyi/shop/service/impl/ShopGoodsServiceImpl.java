package com.ruoyi.shop.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.Status;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.shop.domain.ShopGoods;
import com.ruoyi.shop.domain.ShopGoodsSeed;
import com.ruoyi.shop.domain.ShopGoodsType;
import com.ruoyi.shop.mapper.ShopGoodsMapper;
import com.ruoyi.shop.service.IShopGoodsSeedService;
import com.ruoyi.shop.service.IShopGoodsService;
import com.ruoyi.shop.vo.ShopGoodsStockVo;
import com.ruoyi.shop.vo.ShopGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息Service业务层处理
 * 
 * @author miki
 * @date 2021-05-25
 */
@Service
public class ShopGoodsServiceImpl implements IShopGoodsService 
{
    @Autowired
    private ShopGoodsMapper shopGoodsMapper;

    @Autowired
    private IShopGoodsSeedService shopGoodsSeedService;

    /**
     * 查询商品信息
     * 
     * @param id 商品信息ID
     * @return 商品信息
     */
    @Override
    public ShopGoods selectShopGoodsById(Long id)
    {
        return shopGoodsMapper.selectShopGoodsById(id);
    }

    /**
     * 查询商品信息列表
     * 
     * @param shopGoods 商品信息
     * @return 商品信息
     */
    @Override
    public List<ShopGoods> selectShopGoodsList(ShopGoods shopGoods)
    {
        return shopGoodsMapper.selectShopGoodsList(shopGoods);
    }

    /**
     * 新增商品信息
     * 
     * @param shopGoods 商品信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertShopGoods(ShopGoods shopGoods)
    {
        int result = 0;
        shopGoods.setCreateTime(DateUtils.getNowDate());
        result = shopGoodsMapper.insertShopGoods(shopGoods);
        ShopGoodsSeed shopGoodsSeed = new ShopGoodsSeed();
        shopGoodsSeed.setGoodsId(shopGoods.getId());
        shopGoodsSeed.setSafetyStock(shopGoods.getSafetyStock());
        shopGoodsSeed.setCreateBy(shopGoods.getCreateBy());
        shopGoodsSeed.setDeptId(shopGoods.getDeptId());
        result = shopGoodsSeedService.insertShopGoodsSeed(shopGoodsSeed);
        return result;
    }

    /**
     * 修改商品信息
     * 
     * @param shopGoods 商品信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateShopGoods(ShopGoods shopGoods)
    {
        int result = 0;
        shopGoods.setUpdateTime(DateUtils.getNowDate());
        result = shopGoodsMapper.updateShopGoods(shopGoods);

        ShopGoodsSeed shopGoodsSeed = shopGoodsSeedService.selectShopGoodsSeedBygoodsId(shopGoods.getId());
        shopGoodsSeed.setSafetyStock(shopGoods.getSafetyStock());
        shopGoodsSeed.setUpdateBy(shopGoods.getUpdateBy());
        result = shopGoodsSeedService.updateShopGoodsSeed(shopGoodsSeed);
        return result;
    }

    /**
     * 删除商品信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteShopGoodsByIds(String ids)
    {
        int result = 0;
        Long[] shopGoodsids = Convert.toLongArray(ids);
        for (Long shopGoodsid : shopGoodsids ) {
            ShopGoods shopGoods = new ShopGoods();
            //查询商品是否有库存;如有库存则商品信息不能删除
            ShopGoodsSeed shopGoodsSeed = shopGoodsSeedService.selectShopGoodsSeedBygoodsId(shopGoodsid);
            if(shopGoodsSeed.getStockNumber()>0){
              shopGoods  = selectShopGoodsById(shopGoodsid);
              throw new BusinessException(shopGoods.getGoodsName()+"有库存，不允许删除");
            }
            shopGoods.setId(shopGoodsid);
            shopGoods.setDelFlag(Status.DELETED.getCode());
            shopGoods.setUpdateBy(ShiroUtils.getLoginName());
            result = updateShopGoods(shopGoods);

            shopGoodsSeed.setDelFlag(Status.DELETED.getCode());
            shopGoodsSeed.setUpdateBy(shopGoods.getUpdateBy());
            result = shopGoodsSeedService.updateShopGoodsSeed(shopGoodsSeed);
        }
        return result;
    }

    /**
     * 删除商品信息信息
     * 
     * @param id 商品信息ID
     * @return 结果
     */
    @Override
    public int deleteShopGoodsById(Long id)
    {
        return shopGoodsMapper.deleteShopGoodsById(id);
    }

    @Override
    public String checkGoodsCodeUnique(String goodsCode) {
        int count = shopGoodsMapper.checkGoodsCodeUnique(goodsCode);
        if (count > 0)
        {
            return Constants.NAME_NOT_UNIQUE;
        }
        return Constants.NAME_UNIQUE;
    }

    @Override
    public List<ShopGoodsVo> selectShopGoodsVoList(ShopGoods shopGoods) {
        return shopGoodsMapper.selectShopGoodsVoList(shopGoods);
    }

    @Override
    public int selectShopGoodsCount(ShopGoods shopGoods) {
        return shopGoodsMapper.selectShopGoodsCount(shopGoods);
    }

    @Override
    public List<Long> selectShopGoodsStorageIds(List<Long> ids) {
        return shopGoodsMapper.selectShopGoodsStorageIds(ids);
    }

    @Override
    public List<ShopGoodsStockVo> selectShopGoodsStockVoList(ShopGoods shopGoods) {
        return shopGoodsMapper.selectShopGoodsStockVoList(shopGoods);
    }
}
