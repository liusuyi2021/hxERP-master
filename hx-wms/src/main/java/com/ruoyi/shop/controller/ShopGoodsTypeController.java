package com.ruoyi.shop.controller;

import java.util.List;

import com.ruoyi.basis.domain.BasisCarriers;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.shop.domain.ShopGoods;
import com.ruoyi.shop.service.IShopGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.shop.domain.ShopGoodsType;
import com.ruoyi.shop.service.IShopGoodsTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 商品类别Controller
 * 
 * @author miki
 * @date 2021-05-25
 */
@Controller
@RequestMapping("/shop/goodsType")
public class ShopGoodsTypeController extends BaseController
{
    private String prefix = "shop/goodsType";

    @Autowired
    private IShopGoodsTypeService shopGoodsTypeService;

    @Autowired
    private IShopGoodsService shopGoodsService;

    @RequiresPermissions("shop:goodsType:view")
    @GetMapping()
    public String goodsType()
    {
        return prefix + "/goodsType";
    }

    /**
     * 查询商品类别树列表
     */
    @RequiresPermissions("shop:goodsType:list")
    @PostMapping("/list")
    @ResponseBody
    public List<ShopGoodsType> list(ShopGoodsType shopGoodsType)
    {
        shopGoodsType.setDeptId(ShiroUtils.getDeptId());
        List<ShopGoodsType> list = shopGoodsTypeService.selectShopGoodsTypeList(shopGoodsType);
        return list;
    }

    /**
     * 导出商品类别列表
     */
    @RequiresPermissions("shop:goodsType:export")
    @Log(title = "商品类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ShopGoodsType shopGoodsType)
    {
        List<ShopGoodsType> list = shopGoodsTypeService.selectShopGoodsTypeList(shopGoodsType);
        ExcelUtil<ShopGoodsType> util = new ExcelUtil<ShopGoodsType>(ShopGoodsType.class);
        return util.exportExcel(list, "商品类别数据");
    }

    /**
     * 新增商品类别
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("shopGoodsType", shopGoodsTypeService.selectShopGoodsTypeById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存商品类别
     */
    @RequiresPermissions("shop:goodsType:add")
    @Log(title = "商品类别", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ShopGoodsType shopGoodsType)
    {
        shopGoodsType.setDeptId(ShiroUtils.getDeptId());
        shopGoodsType.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(shopGoodsTypeService.insertShopGoodsType(shopGoodsType));
    }

    /**
     * 修改商品类别
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ShopGoodsType shopGoodsType = shopGoodsTypeService.selectShopGoodsTypeById(id);
        mmap.put("shopGoodsType", shopGoodsType);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品类别
     */
    @RequiresPermissions("shop:goodsType:edit")
    @Log(title = "商品类别", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ShopGoodsType shopGoodsType)
    {
        if (shopGoodsType.getParentId().equals(shopGoodsType.getId()))
        {
            return error("修改商品类别'" + shopGoodsType.getGoodsTypeName() + "'失败，上级商品类别不能是自己");
        }
        shopGoodsType.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(shopGoodsTypeService.updateShopGoodsType(shopGoodsType));
    }

    /**
     * 删除
     */
    @RequiresPermissions("shop:goodsType:remove")
    @Log(title = "商品类别", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        if (shopGoodsTypeService.selectShopGoodsTypeCount(id) > 0)
        {
            return AjaxResult.warn("存在下级商品类别,不允许删除");
        }
        ShopGoods shopGoods = new ShopGoods();
        shopGoods.setGoodsTypeId(id);
        if(shopGoodsService.selectShopGoodsCount(shopGoods) > 0){
            return AjaxResult.warn("存在商品,不允许删除");
        }
        return toAjax(shopGoodsTypeService.deleteShopGoodsTypeById(id));
    }

    /**
     * 选择商品类别树
     */
    @GetMapping(value = { "/selectGoodsTypeTree/{id}", "/selectGoodsTypeTree/" })
    public String selectGoodsTypeTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("shopGoodsType", shopGoodsTypeService.selectShopGoodsTypeById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载商品类别树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = shopGoodsTypeService.selectShopGoodsTypeTree();
        return ztrees;
    }

    /**
     * 校验商品类别编码
     */
    @PostMapping("/checkGoodsTypeCodeUnique")
    @ResponseBody
    public String checkGoodsTypeCodeUnique(ShopGoodsType shopGoodsType)
    {
        return shopGoodsTypeService.checkGoodsTypeCodeUnique(shopGoodsType.getGoodsTypeCode());
    }
}
