package com.ruoyi.shop.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.shop.domain.ShopGoodsType;
import org.apache.ibatis.annotations.Param;

/**
 * 商品类别Mapper接口
 * 
 * @author miki
 * @date 2021-05-25
 */
public interface ShopGoodsTypeMapper 
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
     * 删除商品类别
     * 
     * @param id 商品类别ID
     * @return 结果
     */
    public int deleteShopGoodsTypeById(Long id);

    /**
     * 批量删除商品类别
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteShopGoodsTypeByIds(String[] ids);

    /**
     * 校验商品类别编码是否唯一
     *
     * @param goodsTypeCode 商品类别编码
     * @return 结果
     */
    public int checkGoodsTypeCodeUnique(String goodsTypeCode);

    /**
     * 根据ID查询所有子商品类别
     *
     * @param id 商品类别ID
     * @return 商品类别列表
     */
    public List<ShopGoodsType> selectChildrenShopGoodsTypeById(Long id);

    /**
     * 修改子元素关系
     *
     * @param shopGoodsTypes 子元素
     * @return 结果
     */
    public int updateShopGoodsTypeChildren(@Param("shopGoodsTypes") List<ShopGoodsType> shopGoodsTypes);

    /**
     * 查询商品类别数
     *
     * @param shopGoodsType 商品类别
     * @return 结果
     */
    public int selectShopGoodsTypeCount(ShopGoodsType shopGoodsType);
}
