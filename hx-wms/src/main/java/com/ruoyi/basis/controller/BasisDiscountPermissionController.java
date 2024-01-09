package com.ruoyi.basis.controller;

import com.ruoyi.basis.domain.BasisDiscountPermission;
import com.ruoyi.basis.service.IBasisDiscountPermissionService;
import com.ruoyi.basis.vo.BasisDiscountPermissionVo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 折扣权限设定Controller
 * 
 * @author miki
 * @date 2021-06-15
 */
@Controller
@RequestMapping("/basis/discountPermission")
public class BasisDiscountPermissionController extends BaseController
{
    private String prefix = "basis/discountPermission";

    @Autowired
    private IBasisDiscountPermissionService basisDiscountPermissionService;


    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("basis:discountPermission:view")
    @GetMapping()
    public String discountPermission()
    {
        return prefix + "/discountPermission";
    }

    /**
     * 查询折扣权限设定列表
     */
    @RequiresPermissions("basis:discountPermission:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BasisDiscountPermission basisDiscountPermission)
    {
        basisDiscountPermission.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<BasisDiscountPermissionVo> list = basisDiscountPermissionService.selectBasisDiscountPermissionListVo(basisDiscountPermission);
        return getDataTable(list);
    }

    /**
     * 新增折扣权限设定
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存折扣权限设定
     */
    @RequiresPermissions("basis:discountPermission:add")
    @Log(title = "折扣权限设定", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BasisDiscountPermission basisDiscountPermission)
    {
        basisDiscountPermission.setDeptId(ShiroUtils.getDeptId());
        basisDiscountPermission.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(basisDiscountPermissionService.insertBasisDiscountPermission(basisDiscountPermission));
    }

    /**
     * 修改折扣权限设定
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BasisDiscountPermission basisDiscountPermission = basisDiscountPermissionService.selectBasisDiscountPermissionById(id);
        mmap.put("basisDiscountPermission", basisDiscountPermission);
        SysUser sysUser = userService.selectUserById(basisDiscountPermission.getUserId());
        mmap.put("sysUser", sysUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存折扣权限设定
     */
    @RequiresPermissions("basis:discountPermission:edit")
    @Log(title = "折扣权限设定", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BasisDiscountPermission basisDiscountPermission)
    {
        basisDiscountPermission.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(basisDiscountPermissionService.updateBasisDiscountPermission(basisDiscountPermission));
    }

    /**
     * 删除折扣权限设定
     */
    @RequiresPermissions("basis:discountPermission:remove")
    @Log(title = "折扣权限设定", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(basisDiscountPermissionService.deleteBasisDiscountPermissionByIds(ids));
    }

    /**
     * 校验用户主键
     */
    @PostMapping("/checkSupplierCodeUnique")
    @ResponseBody
    public AjaxResult checkBasisDiscountPermissionUserIdUnique(BasisDiscountPermission basisDiscountPermission)
    {
        BasisDiscountPermission basisDiscountPermissionByUserId = basisDiscountPermissionService.selectBasisDiscountPermissionByUserId(basisDiscountPermission.getUserId());
        if(StringUtils.isNull(basisDiscountPermissionByUserId)){
            return success();
        }
        return AjaxResult.warn("数据已存在,请重新选择其他用户");

    }
}
