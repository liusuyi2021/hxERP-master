package com.ruoyi.basis.controller;

import java.util.List;

import com.ruoyi.basis.domain.BasisCustomer;
import com.ruoyi.basis.domain.BasisSupplier;
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
import com.ruoyi.basis.domain.BasisCarriers;
import com.ruoyi.basis.service.IBasisCarriersService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 承运商管理Controller
 * 
 * @author miki
 * @date 2021-05-20
 */
@Controller
@RequestMapping("/basis/carriers")
public class BasisCarriersController extends BaseController
{
    private String prefix = "basis/carriers";

    @Autowired
    private IBasisCarriersService basisCarriersService;

    @RequiresPermissions("basis:carriers:view")
    @GetMapping()
    public String carriers()
    {
        return prefix + "/carriers";
    }

    /**
     * 查询承运商管理列表
     */
    @RequiresPermissions("basis:carriers:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BasisCarriers basisCarriers)
    {
        basisCarriers.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<BasisCarriers> list = basisCarriersService.selectBasisCarriersList(basisCarriers);
        return getDataTable(list);
    }

    /**
     * 导出承运商管理列表
     */
    @RequiresPermissions("basis:carriers:export")
    @Log(title = "承运商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BasisCarriers basisCarriers)
    {
        List<BasisCarriers> list = basisCarriersService.selectBasisCarriersList(basisCarriers);
        ExcelUtil<BasisCarriers> util = new ExcelUtil<BasisCarriers>(BasisCarriers.class);
        return util.exportExcel(list, "承运商管理数据");
    }

    /**
     * 新增承运商管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存承运商管理
     */
    @RequiresPermissions("basis:carriers:add")
    @Log(title = "承运商管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BasisCarriers basisCarriers)
    {
        basisCarriers.setDeptId(ShiroUtils.getDeptId());
        basisCarriers.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(basisCarriersService.insertBasisCarriers(basisCarriers));
    }

    /**
     * 修改承运商管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BasisCarriers basisCarriers = basisCarriersService.selectBasisCarriersById(id);
        mmap.put("basisCarriers", basisCarriers);
        return prefix + "/edit";
    }

    /**
     * 修改保存承运商管理
     */
    @RequiresPermissions("basis:carriers:edit")
    @Log(title = "承运商管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BasisCarriers basisCarriers)
    {
        basisCarriers.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(basisCarriersService.updateBasisCarriers(basisCarriers));
    }

    /**
     * 删除承运商管理
     */
    @RequiresPermissions("basis:carriers:remove")
    @Log(title = "承运商管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(basisCarriersService.deleteBasisCarriersByIds(ids));
    }

    /**
     * 校验承运商编码
     */
    @PostMapping("/checkCarriersCodeUnique")
    @ResponseBody
    public String checkCarriersCodeUnique(BasisCarriers basisCarriers)
    {
        return basisCarriersService.checkCarriersCodeUnique(basisCarriers.getCarriersCode());
    }

    /**
     * 选择承运商
     */
    @GetMapping("/selectCarriers")
    public String selectCarriers(ModelMap mmap)
    {
        return prefix + "/selectCarriers";
    }

    /**
     * 查询承运商管理列表(选择页面)
     */
    @PostMapping("/selectList")
    @ResponseBody
    public TableDataInfo selectList(BasisCarriers basisCarriers)
    {
        basisCarriers.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<BasisCarriers> list = basisCarriersService.selectBasisCarriersList(basisCarriers);
        return getDataTable(list);
    }
}
