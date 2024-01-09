package com.ruoyi.wh.controller;

import java.util.List;

import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.wh.vo.WhOutboundStockoutVo;
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
import com.ruoyi.wh.domain.WhOutboundStockout;
import com.ruoyi.wh.service.IWhOutboundStockoutService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 缺货管制订单Controller
 * 
 * @author miki
 * @date 2021-06-09
 */
@Controller
@RequestMapping("/wh/outboundStockout")
public class WhOutboundStockoutController extends BaseController
{
    private String prefix = "wh/outboundStockout";

    @Autowired
    private IWhOutboundStockoutService whOutboundStockoutService;

    @RequiresPermissions("wh:outboundStockout:view")
    @GetMapping()
    public String outboundStockout()
    {
        return prefix + "/outboundStockout";
    }

    /**
     * 查询缺货管制订单列表
     */
    @RequiresPermissions("wh:outboundStockout:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WhOutboundStockout whOutboundStockout)
    {
        whOutboundStockout.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<WhOutboundStockoutVo> list = whOutboundStockoutService.selectWhOutboundStockoutListVo(whOutboundStockout);
        return getDataTable(list);
    }

    /**
     * 解欠缺货管制订单
     */
    @RequiresPermissions("wh:outboundStockout:editStockout")
    @Log(title = "缺货管制订单", businessType = BusinessType.DELETE)
    @PostMapping( "/editStockout")
    @ResponseBody
    public AjaxResult editStockout(String ids)
    {
        return toAjax(whOutboundStockoutService.editStockout(ids));
    }
}
