package com.ruoyi.wh.controller;

import java.util.List;

import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.wh.domain.WhReservoir;
import com.ruoyi.wh.vo.WhReservoirVo;
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
import com.ruoyi.wh.domain.WhStorageSize;
import com.ruoyi.wh.service.IWhStorageSizeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库位尺寸Controller
 * 
 * @author miki
 * @date 2021-05-23
 */
@Controller
@RequestMapping("/wh/storageSize")
public class WhStorageSizeController extends BaseController
{
    private String prefix = "wh/storageSize";

    @Autowired
    private IWhStorageSizeService whStorageSizeService;

    @RequiresPermissions("wh:storageSize:view")
    @GetMapping()
    public String storageSize()
    {
        return prefix + "/storageSize";
    }

    /**
     * 查询库位尺寸列表
     */
    @RequiresPermissions("wh:storageSize:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WhStorageSize whStorageSize)
    {
        whStorageSize.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<WhStorageSize> list = whStorageSizeService.selectWhStorageSizeList(whStorageSize);
        return getDataTable(list);
    }

    /**
     * 导出库位尺寸列表
     */
    @RequiresPermissions("wh:storageSize:export")
    @Log(title = "库位尺寸", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WhStorageSize whStorageSize)
    {
        List<WhStorageSize> list = whStorageSizeService.selectWhStorageSizeList(whStorageSize);
        ExcelUtil<WhStorageSize> util = new ExcelUtil<WhStorageSize>(WhStorageSize.class);
        return util.exportExcel(list, "库位尺寸数据");
    }

    /**
     * 新增库位尺寸
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存库位尺寸
     */
    @RequiresPermissions("wh:storageSize:add")
    @Log(title = "库位尺寸", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WhStorageSize whStorageSize)
    {
        whStorageSize.setDeptId(ShiroUtils.getDeptId());
        whStorageSize.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(whStorageSizeService.insertWhStorageSize(whStorageSize));
    }

    /**
     * 修改库位尺寸
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WhStorageSize whStorageSize = whStorageSizeService.selectWhStorageSizeById(id);
        mmap.put("whStorageSize", whStorageSize);
        return prefix + "/edit";
    }

    /**
     * 修改保存库位尺寸
     */
    @RequiresPermissions("wh:storageSize:edit")
    @Log(title = "库位尺寸", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WhStorageSize whStorageSize)
    {
        whStorageSize.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(whStorageSizeService.updateWhStorageSize(whStorageSize));
    }

    /**
     * 删除库位尺寸
     */
    @RequiresPermissions("wh:storageSize:remove")
    @Log(title = "库位尺寸", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(whStorageSizeService.deleteWhStorageSizeByIds(ids));
    }

    /**
     * ajax查询库位尺寸列表
     */
    @PostMapping("/ajaxList")
    @ResponseBody
    public AjaxResult ajaxList(WhStorageSize whStorageSize)
    {
        whStorageSize.setDeptId(ShiroUtils.getDeptId());
        List<WhStorageSize> list = whStorageSizeService.selectWhStorageSizeList(whStorageSize);
        return AjaxResult.success(list);
    }
}
