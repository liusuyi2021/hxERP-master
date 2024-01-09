package com.ruoyi.shop.controller;

import java.util.List;
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
import com.ruoyi.shop.domain.ShopGoodsSeed;
import com.ruoyi.shop.service.IShopGoodsSeedService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品库存信息Controller
 * 
 * @author miki
 * @date 2021-05-25
 */
@Controller
@RequestMapping("/shop/goodsSeed")
public class ShopGoodsSeedController extends BaseController
{
    private String prefix = "shop/goodsSeed";

    @Autowired
    private IShopGoodsSeedService shopGoodsSeedService;

    @RequiresPermissions("shop:goodsSeed:view")
    @GetMapping()
    public String goodsSeed()
    {
        return prefix + "/goodsSeed";
    }

    /**
     * 查询商品库存信息列表
     */
    @RequiresPermissions("shop:goodsSeed:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ShopGoodsSeed shopGoodsSeed)
    {
        startPage();
        List<ShopGoodsSeed> list = shopGoodsSeedService.selectShopGoodsSeedList(shopGoodsSeed);
        return getDataTable(list);
    }

    /**
     * 导出商品库存信息列表
     */
    @RequiresPermissions("shop:goodsSeed:export")
    @Log(title = "商品库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ShopGoodsSeed shopGoodsSeed)
    {
        List<ShopGoodsSeed> list = shopGoodsSeedService.selectShopGoodsSeedList(shopGoodsSeed);
        ExcelUtil<ShopGoodsSeed> util = new ExcelUtil<ShopGoodsSeed>(ShopGoodsSeed.class);
        return util.exportExcel(list, "商品库存信息数据");
    }

    /**
     * 新增商品库存信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商品库存信息
     */
    @RequiresPermissions("shop:goodsSeed:add")
    @Log(title = "商品库存信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ShopGoodsSeed shopGoodsSeed)
    {
        return toAjax(shopGoodsSeedService.insertShopGoodsSeed(shopGoodsSeed));
    }

    /**
     * 修改商品库存信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ShopGoodsSeed shopGoodsSeed = shopGoodsSeedService.selectShopGoodsSeedById(id);
        mmap.put("shopGoodsSeed", shopGoodsSeed);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品库存信息
     */
    @RequiresPermissions("shop:goodsSeed:edit")
    @Log(title = "商品库存信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ShopGoodsSeed shopGoodsSeed)
    {
        return toAjax(shopGoodsSeedService.updateShopGoodsSeed(shopGoodsSeed));
    }

    /**
     * 删除商品库存信息
     */
    @RequiresPermissions("shop:goodsSeed:remove")
    @Log(title = "商品库存信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(shopGoodsSeedService.deleteShopGoodsSeedByIds(ids));
    }
}
