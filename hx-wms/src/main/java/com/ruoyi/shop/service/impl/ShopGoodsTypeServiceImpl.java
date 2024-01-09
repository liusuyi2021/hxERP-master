package com.ruoyi.shop.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.enums.Status;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.shop.mapper.ShopGoodsTypeMapper;
import com.ruoyi.shop.domain.ShopGoodsType;
import com.ruoyi.shop.service.IShopGoodsTypeService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品类别Service业务层处理
 * 
 * @author miki
 * @date 2021-05-25
 */
@Service
public class ShopGoodsTypeServiceImpl implements IShopGoodsTypeService 
{
    @Autowired
    private ShopGoodsTypeMapper shopGoodsTypeMapper;

    /**
     * 查询商品类别
     * 
     * @param id 商品类别ID
     * @return 商品类别
     */
    @Override
    public ShopGoodsType selectShopGoodsTypeById(Long id)
    {
        return shopGoodsTypeMapper.selectShopGoodsTypeById(id);
    }

    /**
     * 查询商品类别列表
     * 
     * @param shopGoodsType 商品类别
     * @return 商品类别
     */
    @Override
    public List<ShopGoodsType> selectShopGoodsTypeList(ShopGoodsType shopGoodsType)
    {
        return shopGoodsTypeMapper.selectShopGoodsTypeList(shopGoodsType);
    }

    /**
     * 新增商品类别
     * 
     * @param shopGoodsType 商品类别
     * @return 结果
     */
    @Override
    public int insertShopGoodsType(ShopGoodsType shopGoodsType)
    {
        if(StringUtils.isNotNull(shopGoodsType.getParentId())){
            ShopGoodsType info = shopGoodsTypeMapper.selectShopGoodsTypeById(shopGoodsType.getParentId());
            shopGoodsType.setAncestors(info.getAncestors() + "," + shopGoodsType.getParentId());
        }else{
            shopGoodsType.setParentId(0L);
            shopGoodsType.setAncestors("0");
        }
        shopGoodsType.setCreateTime(DateUtils.getNowDate());
        return shopGoodsTypeMapper.insertShopGoodsType(shopGoodsType);
    }

    /**
     * 修改商品类别
     * 
     * @param shopGoodsType 商品类别
     * @return 结果
     */
    @Override
    @Transactional
    public int updateShopGoodsType(ShopGoodsType shopGoodsType)
    {
        ShopGoodsType newShopGoodsType = shopGoodsTypeMapper.selectShopGoodsTypeById(shopGoodsType.getParentId());
        ShopGoodsType oldShopGoodsType = selectShopGoodsTypeById(shopGoodsType.getId());
        if (StringUtils.isNotNull(newShopGoodsType) && StringUtils.isNotNull(oldShopGoodsType))
        {
            String newAncestors = newShopGoodsType.getAncestors() + "," + newShopGoodsType.getId();
            String oldAncestors = oldShopGoodsType.getAncestors();
            shopGoodsType.setAncestors(newAncestors);
            updateShopGoodsTypeChildren(shopGoodsType.getId(), newAncestors, oldAncestors);
        }
        shopGoodsType.setUpdateTime(DateUtils.getNowDate());
        return shopGoodsTypeMapper.updateShopGoodsType(shopGoodsType);
    }


    /**
     * 修改子元素关系
     *
     * @param shopGoodsTypeId 被修改的商品类别ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateShopGoodsTypeChildren(Long shopGoodsTypeId, String newAncestors, String oldAncestors)
    {
        List<ShopGoodsType> children = shopGoodsTypeMapper.selectChildrenShopGoodsTypeById(shopGoodsTypeId);
        for (ShopGoodsType child : children)
        {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            shopGoodsTypeMapper.updateShopGoodsTypeChildren(children);
        }
    }

    /**
     * 删除商品类别对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteShopGoodsTypeByIds(String ids)
    {
        return shopGoodsTypeMapper.deleteShopGoodsTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品类别信息
     * 
     * @param id 商品类别ID
     * @return 结果
     */
    @Override
    public int deleteShopGoodsTypeById(Long id)
    {
        int result = 0;
        ShopGoodsType shopGoodsType = new ShopGoodsType();
        shopGoodsType.setId(id);
        shopGoodsType.setDelFlag(Status.DELETED.getCode());
        shopGoodsType.setUpdateBy(ShiroUtils.getLoginName());
        result = updateShopGoodsType(shopGoodsType);
        return result;
    }

    /**
     * 查询商品类别树列表
     * 
     * @return 所有商品类别信息
     */
    @Override
    public List<Ztree> selectShopGoodsTypeTree()
    {
        ShopGoodsType goodsType = new ShopGoodsType();
        goodsType.setDeptId(ShiroUtils.getDeptId());
        List<ShopGoodsType> shopGoodsTypeList = shopGoodsTypeMapper.selectShopGoodsTypeList(goodsType);
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (ShopGoodsType shopGoodsType : shopGoodsTypeList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(shopGoodsType.getId());
            ztree.setpId(shopGoodsType.getParentId());
            ztree.setName(shopGoodsType.getGoodsTypeName());
            ztree.setTitle(shopGoodsType.getGoodsTypeName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    @Override
    public String checkGoodsTypeCodeUnique(String goodsTypeCode) {
        int count = shopGoodsTypeMapper.checkGoodsTypeCodeUnique(goodsTypeCode);
        if (count > 0)
        {
            return Constants.NAME_NOT_UNIQUE;
        }
        return Constants.NAME_UNIQUE;
    }

    @Override
    public int selectShopGoodsTypeCount(Long parentId) {
        ShopGoodsType shopGoodsType = new ShopGoodsType();
        shopGoodsType.setParentId(parentId);
        return shopGoodsTypeMapper.selectShopGoodsTypeCount(shopGoodsType);
    }
}
