package com.ruoyi.shop.service;

import java.util.List;
import com.ruoyi.shop.domain.ShopGoodsType;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 商品类别Service接口
 * 
 * @author miki
 * @date 2021-05-25
 */
public interface IShopGoodsTypeService 
{
    /**
     * 查询商品类别
     * 
     * @param id 商品类别ID
     * @return 商品类别
     */
    public ShopGoodsType selectShopGoodsTypeById(Long id);

    /**
     * 查询商品类别列表
     * 
     * @param shopGoodsType 商品类别
     * @return 商品类别集合
     */
    public List<ShopGoodsType> selectShopGoodsTypeList(ShopGoodsType shopGoodsType);

    /**
     * 新增商品类别
     * 
     * @param shopGoodsType 商品类别
     * @return 结果
     */
    public int insertShopGoodsType(ShopGoodsType shopGoodsType);

    /**
     * 修改商品类别
     * 
     * @param shopGoodsType 商品类别
     * @return 结果
     */
    public int updateShopGoodsType(ShopGoodsType shopGoodsType);

    /**
     * 批量删除商品类别
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteShopGoodsTypeByIds(String ids);

    /**
     * 删除商品类别信息
     * 
     * @param id 商品类别ID
     * @return 结果
     */
    public int deleteShopGoodsTypeById(Long id);

    /**
     * 查询商品类别树列表
     * 
     * @return 所有商品类别信息
     */
    public List<Ztree> selectShopGoodsTypeTree();

    /**
     * 校验商品类别编码是否唯一
     *
     * @param goodsTypeCode 商品类别编码
     * @return 结果
     */
    public String checkGoodsTypeCodeUnique(String goodsTypeCode);

    /**
     * 查询商品类别数
     *
     * @param parentId 父部门ID
     * @return 结果
     */
    public int selectShopGoodsTypeCount(Long parentId);

}
