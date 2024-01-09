package com.ruoyi.shop.controller;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.shop.domain.ShopGoodsSeed;
import com.ruoyi.shop.domain.ShopGoodsType;
import com.ruoyi.shop.service.IShopGoodsSeedService;
import com.ruoyi.shop.service.IShopGoodsTypeService;
import com.ruoyi.shop.vo.ShopGoodsStockVo;
import com.ruoyi.shop.vo.ShopGoodsVo;
import com.ruoyi.wh.domain.WhStorage;
import com.ruoyi.wh.service.IWhStorageService;
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
import com.ruoyi.shop.domain.ShopGoods;
import com.ruoyi.shop.service.IShopGoodsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品信息Controller
 * 
 * @author miki
 * @date 2021-05-25
 */
@Controller
@RequestMapping("/shop/goods")
public class ShopGoodsController extends BaseController
{
    private String prefix = "shop/goods";

    @Autowired
    private IShopGoodsService shopGoodsService;

    @Autowired
    private IShopGoodsTypeService shopGoodsTypeService;

    @Autowired
    private IShopGoodsSeedService shopGoodsSeedService;

    @Autowired
    private IWhStorageService whStorageService;

    @RequiresPermissions("shop:goods:view")
    @GetMapping()
    public String goods()
    {
        return prefix + "/goods";
    }

    /**
     * 查询商品信息列表
     */
    @RequiresPermissions("shop:goods:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ShopGoods shopGoods)
    {
        shopGoods.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<ShopGoodsVo> list = shopGoodsService.selectShopGoodsVoList(shopGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @RequiresPermissions("shop:goods:export")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ShopGoods shopGoods)
    {
        List<ShopGoods> list = shopGoodsService.selectShopGoodsList(shopGoods);
        ExcelUtil<ShopGoods> util = new ExcelUtil<ShopGoods>(ShopGoods.class);
        return util.exportExcel(list, "商品信息数据");
    }

    /**
     * 新增商品信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商品信息
     */
    @RequiresPermissions("shop:goods:add")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ShopGoods shopGoods)
    {
        shopGoods.setDeptId(ShiroUtils.getDeptId());
        shopGoods.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(shopGoodsService.insertShopGoods(shopGoods));
    }

    /**
     * 修改商品信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ShopGoods shopGoods = shopGoodsService.selectShopGoodsById(id);
        ShopGoodsType shopGoodsType = shopGoodsTypeService.selectShopGoodsTypeById(shopGoods.getGoodsTypeId());
        ShopGoodsSeed shopGoodsSeed = shopGoodsSeedService.selectShopGoodsSeedBygoodsId(shopGoods.getId());
        WhStorage whStorage = whStorageService.selectWhStorageById(shopGoods.getStorageId());
        mmap.put("shopGoods", shopGoods);
        mmap.put("shopGoodsType", shopGoodsType);
        mmap.put("shopGoodsSeed", shopGoodsSeed);
        mmap.put("whStorage", whStorage);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品信息
     */
    @RequiresPermissions("shop:goods:edit")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ShopGoods shopGoods)
    {
        shopGoods.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(shopGoodsService.updateShopGoods(shopGoods));
    }

    /**
     * 删除商品信息
     */
    @RequiresPermissions("shop:goods:remove")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(shopGoodsService.deleteShopGoodsByIds(ids));
    }

    /**
     * 校验商品编码
     */
    @PostMapping("/checkGoodsCodeUnique")
    @ResponseBody
    public String checkGoodsCodeUnique(ShopGoods shopGoods)
    {
        return shopGoodsService.checkGoodsCodeUnique(shopGoods.getGoodsCode());
    }

    /**
     * 商品信息成本价是否锁定修改
     */
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @RequiresPermissions("shop:goods:changeIsCost")
    @PostMapping("/changeIsCost")
    @ResponseBody
    public AjaxResult changeIsCost(ShopGoods shopGoods)
    {
        return toAjax(shopGoodsService.updateShopGoods(shopGoods));
    }


    /**
     * 选择商品信息
     */
    @GetMapping("/selectGoods")
    public String selectGoods()
    {
        return prefix + "/selectGoods";
    }

    /**
     * 查询商品信息列表(选择页面)
     */
    @PostMapping("/selectList")
    @ResponseBody
    public TableDataInfo selectList(ShopGoods shopGoods)
    {
        shopGoods.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<ShopGoodsVo> list = shopGoodsService.selectShopGoodsVoList(shopGoods);
        return getDataTable(list);
    }


    /**
     * 库存查询界面
     * @return
     */
    @RequiresPermissions("shop:goods:listStock")
    @GetMapping("/goodsStock")
    public String goodsStock()
    {
        return prefix + "/goodsStock";
    }

    /**
     * 查询库存查询列表功能
     */
    @RequiresPermissions("shop:goods:listStock")
    @PostMapping("/listStock")
    @ResponseBody
    public TableDataInfo listStock(ShopGoods shopGoods)
    {
        shopGoods.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<ShopGoodsStockVo> list = shopGoodsService.selectShopGoodsStockVoList(shopGoods);
        return getDataTable(list);
    }

    /**
     * 跳转商品价格调整功能
     */
    @GetMapping("/editPrice/{id}")
    public String editPrice(@PathVariable("id") Long id, ModelMap mmap)
    {
        ShopGoods shopGoods = shopGoodsService.selectShopGoodsById(id);
        ShopGoodsType shopGoodsType = shopGoodsTypeService.selectShopGoodsTypeById(shopGoods.getGoodsTypeId());
        WhStorage whStorage = whStorageService.selectWhStorageById(shopGoods.getStorageId());
        mmap.put("shopGoods", shopGoods);
        mmap.put("shopGoodsType", shopGoodsType);
        mmap.put("whStorage", whStorage);
        return prefix + "/editPrice";
    }
    /**
     * 修改保存商品价格信息
     */
    @RequiresPermissions("shop:goods:editPrice")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/editSavePrice")
    @ResponseBody
    public AjaxResult editSaveTPrice(ShopGoods shopGoods)
    {
        shopGoods.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(shopGoodsService.updateShopGoods(shopGoods));
    }

    /**
     * 跳转商品移库功能
     */
    @GetMapping("/editTransfer/{id}")
    public String editTransfer(@PathVariable("id") Long id, ModelMap mmap)
    {
        ShopGoods shopGoods = shopGoodsService.selectShopGoodsById(id);
        ShopGoodsType shopGoodsType = shopGoodsTypeService.selectShopGoodsTypeById(shopGoods.getGoodsTypeId());
        WhStorage whStorage = whStorageService.selectWhStorageById(shopGoods.getStorageId());
        mmap.put("shopGoods", shopGoods);
        mmap.put("shopGoodsType", shopGoodsType);
        mmap.put("whStorage", whStorage);
        return prefix + "/editTransfer";
    }

    /**
     * 修改保存商品移库信息
     */
    @RequiresPermissions("shop:goods:editSaveTransfer")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/editSaveTransfer")
    @ResponseBody
    public AjaxResult editSaveTransfer(ShopGoods shopGoods)
    {
        shopGoods.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(shopGoodsService.updateShopGoods(shopGoods));
    }
}
