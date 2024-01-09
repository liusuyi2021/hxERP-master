package com.ruoyi.shop.mapper;

import java.util.List;
import com.ruoyi.shop.domain.ShopGoods;
import com.ruoyi.shop.vo.ShopGoodsStockVo;
import com.ruoyi.shop.vo.ShopGoodsVo;

/**
 * 商品信息Mapper接口
 * 
 * @author miki
 * @date 2021-05-25
 */
public interface ShopGoodsMapper 
{
    /**
     * 查询商品信息
     * 
     * @param id 商品信息ID
     * @return 商品信息
     */
    public ShopGoods selectShopGoodsById(Long id);

    /**
     * 查询商品信息列表
     * 
     * @param shopGoods 商品信息
     * @return 商品信息集合
     */
    public List<ShopGoods> selectShopGoodsList(ShopGoods shopGoods);

    /**
     * 新增商品信息
     * 
     * @param shopGoods 商品信息
     * @return 结果
     */
    public int insertShopGoods(ShopGoods shopGoods);

    /**
     * 修改商品信息
     * 
     * @param shopGoods 商品信息
     * @return 结果
     */
    public int updateShopGoods(ShopGoods shopGoods);

    /**
     * 删除商品信息
     * 
     * @param id 商品信息ID
     * @return 结果
     */
    public int deleteShopGoodsById(Long id);

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteShopGoodsByIds(String[] ids);

    /**
     * 校验商品编码是否唯一
     *
     * @param goodsCode 商品编码
     * @return 结果
     */
    public int checkGoodsCodeUnique(String goodsCode);

    /**
     * 查询商品信息列表VO
     *
     * @param shopGoods 商品信息
     * @return 商品信息集合
     */
    public List<ShopGoodsVo> selectShopGoodsVoList(ShopGoods shopGoods);

    /**
     * 查询商品数
     *
     * @param shopGoods 商品信息
     * @return 结果
     */
    public int selectShopGoodsCount(ShopGoods shopGoods);

    /**
     * 根据商品主键查询库位主键集合
     *
     * @param ids 商品主键集合
     * @return 库位集合
     */
    public List<Long> selectShopGoodsStorageIds(List<Long> ids);

    /**
     * 查询库存商品信息列表VO【库存查询】
     *
     * @param shopGoods 商品信息
     * @return 商品信息集合
     */
    public List<ShopGoodsStockVo> selectShopGoodsStockVoList(ShopGoods shopGoods);
}
