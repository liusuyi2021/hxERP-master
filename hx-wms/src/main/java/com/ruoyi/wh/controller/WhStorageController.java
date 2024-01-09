package com.ruoyi.wh.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.Status;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.shop.domain.ShopGoods;
import com.ruoyi.shop.service.IShopGoodsService;
import com.ruoyi.wh.domain.WhStorage;
import com.ruoyi.wh.service.IWhStorageService;
import com.ruoyi.wh.vo.WhStorageVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库位(储位)设置Controller
 * 
 * @author miki
 * @date 2021-05-23
 */
@Controller
@RequestMapping("/wh/storage")
public class WhStorageController extends BaseController {
    private String prefix = "wh/storage";

    @Autowired
    private IWhStorageService whStorageService;

    @Autowired
    private IShopGoodsService shopGoodsService;

    @RequiresPermissions("wh:storage:view")
    @GetMapping()
    public String storage() {
        return prefix + "/storage";
    }

    /**
     * 查询库位(储位)设置列表
     */
    @RequiresPermissions("wh:storage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WhStorage whStorage) {
        whStorage.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<WhStorageVo> list = whStorageService.selectWhStorageVoList(whStorage);
        return getDataTable(list);
    }

    /**
     * 导出库位(储位)设置列表
     */
    @RequiresPermissions("wh:storage:export")
    @Log(title = "库位(储位)设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WhStorage whStorage) {
        List<WhStorage> list = whStorageService.selectWhStorageList(whStorage);
        ExcelUtil<WhStorage> util = new ExcelUtil<WhStorage>(WhStorage.class);
        return util.exportExcel(list, "库位(储位)设置数据");
    }

    /**
     * 新增库位(储位)设置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存库位(储位)设置
     */
    @RequiresPermissions("wh:storage:add")
    @Log(title = "库位(储位)设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WhStorage whStorage) {
        whStorage.setDeptId(ShiroUtils.getDeptId());
        whStorage.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(whStorageService.insertWhStorage(whStorage));
    }

    /**
     * 修改库位(储位)设置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        WhStorage whStorage = whStorageService.selectWhStorageById(id);
        mmap.put("whStorage", whStorage);
        return prefix + "/edit";
    }

    /**
     * 修改保存库位(储位)设置
     */
    @RequiresPermissions("wh:storage:edit")
    @Log(title = "库位(储位)设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WhStorage whStorage) {
        whStorage.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(whStorageService.updateWhStorage(whStorage));
    }

    /**
     * 删除库位(储位)设置
     */
    @RequiresPermissions("wh:storage:remove")
    @Log(title = "库位(储位)设置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(whStorageService.deleteWhStorageByIds(ids));
    }


    /**
     * 校验库位(储位)设置编码
     */
    @PostMapping("/checkStorageCodeUnique")
    @ResponseBody
    public String checkStorageCodeUnique(WhStorage whStorage) {
        return whStorageService.checkStorageCodeUnique(whStorage.getStorageCode());
    }

    /**
     * 选择库位(储位)
     */
    @GetMapping("/selectStorage")
    public String selectStorage(ModelMap mmap)
    {
        return prefix + "/selectStorage";
    }

    /**
     * 查询库位(储位)设置列表(选择页面)
     */
    @PostMapping("/selectList")
    @ResponseBody
    public TableDataInfo selectList(WhStorage whStorage)
    {
        whStorage.setIsDisable(Status.OK.getCode());
        whStorage.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<WhStorageVo> list = whStorageService.selectWhStorageVoList(whStorage);
        return getDataTable(list);
    }

    /**
     * 库位(储位)是否停用修改
     */
    @Log(title = "库位(储位)设置", businessType = BusinessType.UPDATE)
    @RequiresPermissions("wh:storage:edit")
    @PostMapping("/changeIsDisable")
    @ResponseBody
    public AjaxResult changeIsDisable(WhStorage whStorage)
    {
        ShopGoods shopGoods = new ShopGoods();
        shopGoods.setStorageId(whStorage.getId());
        if(Status.DISABLE.getCode().equals(whStorage.getIsDisable())){//选择停用时，先判断该库位是否有绑定商品，如绑定则不允许停用
            if(shopGoodsService.selectShopGoodsCount(shopGoods) > 0){
                return error("该库位有商品不允许停用");
            }
        }
        return toAjax(whStorageService.updateWhStorage(whStorage));
    }

}