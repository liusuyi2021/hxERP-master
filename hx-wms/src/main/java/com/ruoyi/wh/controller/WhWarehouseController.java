package com.ruoyi.wh.controller;

import java.util.List;

import com.ruoyi.basis.domain.BasisCarriers;
import com.ruoyi.common.utils.ShiroUtils;
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
import com.ruoyi.wh.domain.WhWarehouse;
import com.ruoyi.wh.service.IWhWarehouseService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 仓库设置Controller
 * 
 * @author miki
 * @date 2021-05-20
 */
@Controller
@RequestMapping("/wh/warehouse")
public class WhWarehouseController extends BaseController
{
    private String prefix = "wh/warehouse";

    @Autowired
    private IWhWarehouseService whWarehouseService;

    @RequiresPermissions("wh:warehouse:view")
    @GetMapping()
    public String warehouse()
    {
        return prefix + "/warehouse";
    }

    /**
     * 查询仓库设置列表
     */
    @RequiresPermissions("wh:warehouse:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WhWarehouse whWarehouse)
    {
        whWarehouse.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<WhWarehouse> list = whWarehouseService.selectWhWarehouseList(whWarehouse);
        return getDataTable(list);
    }

    /**
     * 导出仓库设置列表
     */
    @RequiresPermissions("wh:warehouse:export")
    @Log(title = "仓库设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WhWarehouse whWarehouse)
    {
        List<WhWarehouse> list = whWarehouseService.selectWhWarehouseList(whWarehouse);
        ExcelUtil<WhWarehouse> util = new ExcelUtil<WhWarehouse>(WhWarehouse.class);
        return util.exportExcel(list, "仓库设置数据");
    }

    /**
     * 新增仓库设置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存仓库设置
     */
    @RequiresPermissions("wh:warehouse:add")
    @Log(title = "仓库设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WhWarehouse whWarehouse)
    {
        whWarehouse.setDeptId(ShiroUtils.getDeptId());
        whWarehouse.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(whWarehouseService.insertWhWarehouse(whWarehouse));
    }

    /**
     * 修改仓库设置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WhWarehouse whWarehouse = whWarehouseService.selectWhWarehouseById(id);
        mmap.put("whWarehouse", whWarehouse);
        return prefix + "/edit";
    }

    /**
     * 修改保存仓库设置
     */
    @RequiresPermissions("wh:warehouse:edit")
    @Log(title = "仓库设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WhWarehouse whWarehouse)
    {
        whWarehouse.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(whWarehouseService.updateWhWarehouse(whWarehouse));
    }

    /**
     * 删除仓库设置
     */
    @RequiresPermissions("wh:warehouse:remove")
    @Log(title = "仓库设置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(whWarehouseService.deleteWhWarehouseByIds(ids));
    }

    /**
     * 校验仓库编码
     */
    @PostMapping("/checkWarehouseCodeUnique")
    @ResponseBody
    public String checkWarehouseCodeUnique(WhWarehouse whWarehouse)
    {
        return whWarehouseService.checkWarehouseCodeUnique(whWarehouse.getWarehouseCode());
    }


    /**
     * ajax查询仓库设置列表
     */
    @PostMapping("/ajaxList")
    @ResponseBody
    public AjaxResult ajaxList(WhWarehouse whWarehouse)
    {
        whWarehouse.setDeptId(ShiroUtils.getDeptId());
        List<WhWarehouse> list = whWarehouseService.selectWhWarehouseList(whWarehouse);
        return AjaxResult.success(list);
    }
}
