package com.ruoyi.wh.controller;

import java.util.List;

import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.wh.model.OutboundOrderSeedModel;
import com.ruoyi.wh.model.WarehousingOrderSeedModel;
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
import com.ruoyi.wh.domain.WhOutboundOrderSeed;
import com.ruoyi.wh.service.IWhOutboundOrderSeedService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品出库单子表Controller
 * 
 * @author miki
 * @date 2021-06-07
 */
@Controller
@RequestMapping("/wh/outboundOrderSeed")
public class WhOutboundOrderSeedController extends BaseController
{
    private String prefix = "wh/outboundOrderSeed";

    @Autowired
    private IWhOutboundOrderSeedService whOutboundOrderSeedService;

    @RequiresPermissions("wh:outboundOrderSeed:view")
    @GetMapping()
    public String outboundOrderSeed()
    {
        return prefix + "/outboundOrderSeed";
    }

    /**
     * 查询商品出库单子表列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WhOutboundOrderSeed whOutboundOrderSeed)
    {
        startPage();
        List<WhOutboundOrderSeed> list = whOutboundOrderSeedService.selectWhOutboundOrderSeedList(whOutboundOrderSeed);
        return getDataTable(list);
    }

    /**
     * 新增保存商品出库单子表
     */
    @Log(title = "商品出库单子表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WhOutboundOrderSeed whOutboundOrderSeed)
    {
        whOutboundOrderSeed.setDeptId(ShiroUtils.getDeptId());
        whOutboundOrderSeed.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(whOutboundOrderSeedService.insertWhOutboundOrderSeed(whOutboundOrderSeed));
    }

    /**
     * 修改保存商品出库单子表
     */
    @Log(title = "商品出库单子表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WhOutboundOrderSeed whOutboundOrderSeed)
    {
        whOutboundOrderSeed.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(whOutboundOrderSeedService.updateWhOutboundOrderSeedRecalculate(whOutboundOrderSeed));
    }

    /**
     * 删除商品出库单子表
     */
    @Log(title = "商品出库单子表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(whOutboundOrderSeedService.deleteWhOutboundOrderSeedByIds(ids));
    }

    /**
     * 确认拣货界面
     * @return
     */
    @GetMapping("/editComplete/{id}")
    public String editComplete(@PathVariable("id") Long outboundOrderId, ModelMap mmap)
    {
        mmap.put("outboundOrderId",outboundOrderId);
        return prefix + "/editComplete";
    }

    /**
     * 确认拣货
     * @return
     */
   @RequiresPermissions("wh:outboundOrderSeed::editComplete")
    @Log(title = "商品入库单子", businessType = BusinessType.UPDATE)
    @PostMapping( "/editCompleteSave")
    @ResponseBody
    public AjaxResult editCompleteSave(OutboundOrderSeedModel outboundOrderSeedModel)
    {
        outboundOrderSeedModel.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(whOutboundOrderSeedService.updateCompleteSave(outboundOrderSeedModel));
    }
}
