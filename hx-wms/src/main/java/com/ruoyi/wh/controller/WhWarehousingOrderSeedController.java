package com.ruoyi.wh.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.wh.domain.WhWarehousingOrderSeed;
import com.ruoyi.wh.model.WarehousingOrderSeedModel;
import com.ruoyi.wh.service.IWhWarehousingOrderSeedService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品入库单子Controller
 *
 * @author miki
 * @date 2021-06-02
 */
@Controller
@RequestMapping("/wh/warehousingOrderSeed")
public class WhWarehousingOrderSeedController extends BaseController
{
    private String prefix = "wh/warehousingOrderSeed";

    @Autowired
    private IWhWarehousingOrderSeedService whWarehousingOrderSeedService;

    /**
     * 查询商品入库单子列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WhWarehousingOrderSeed whWarehousingOrderSeed)
    {
        startPage();
        List<WhWarehousingOrderSeed> list = whWarehousingOrderSeedService.selectWhWarehousingOrderSeedList(whWarehousingOrderSeed);
        return getDataTable(list);
    }

    /**
     * 新增保存商品入库单子
     */
    @Log(title = "商品入库单子", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WhWarehousingOrderSeed whWarehousingOrderSeed)
    {
        whWarehousingOrderSeed.setDeptId(ShiroUtils.getDeptId());
        whWarehousingOrderSeed.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(whWarehousingOrderSeedService.insertWhWarehousingOrderSeed(whWarehousingOrderSeed));
    }

    /**
     * 修改保存商品入库单子
     */
    @Log(title = "商品入库单子", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WhWarehousingOrderSeed whWarehousingOrderSeed)
    {
        whWarehousingOrderSeed.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(whWarehousingOrderSeedService.updateWhWarehousingOrderSeedRecalculate(whWarehousingOrderSeed));
    }

    /**
     * 删除商品入库单子
     */
    @Log(title = "商品入库单子", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(whWarehousingOrderSeedService.deleteWhWarehousingOrderSeedByIds(ids));
    }

    /**
     * 分拣(入库)完成界面
     * @return
     */
    @RequiresPermissions("wh:warehousingOrderSeed:editComplete")
    @GetMapping("/editComplete/{id}")
    public String editComplete(@PathVariable("id") Long outboundOrderId, ModelMap mmap)
    {
        mmap.put("warehousingOrderId",outboundOrderId);
        return prefix + "/editComplete";
    }

    /**
     * 分拣(入库)完成
     * @return
     */
    @RequiresPermissions("wh:warehousingOrderSeed:editCompleteSave")
    @Log(title = "商品入库单子", businessType = BusinessType.UPDATE)
    @PostMapping( "/editCompleteSave")
    @ResponseBody
    public AjaxResult editCompleteSave(WarehousingOrderSeedModel warehousingOrderSeedModel)
    {
        warehousingOrderSeedModel.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(whWarehousingOrderSeedService.updateCompleteSave(warehousingOrderSeedModel));
    }
}