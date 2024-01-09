package com.ruoyi.wh.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.WhOutboundOrderType;
import com.ruoyi.common.enums.WhWarehousingOrderType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.OrderNumGeneratorUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.wh.domain.WhOutboundOrder;
import com.ruoyi.wh.domain.WhOutboundOrderSeed;
import com.ruoyi.wh.domain.WhWarehousingOrder;
import com.ruoyi.wh.enums.OutboundOrderStatus;
import com.ruoyi.wh.service.IWhOutboundOrderSeedService;
import com.ruoyi.wh.service.IWhOutboundOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品出库单主表Controller
 * 
 * @author miki
 * @date 2021-06-07
 */
@Controller
@RequestMapping("/wh/outboundOrder")
public class WhOutboundOrderController extends BaseController
{
    private String prefix = "wh/outboundOrder";

    @Autowired
    private IWhOutboundOrderService whOutboundOrderService;

    @Autowired
    private IWhOutboundOrderSeedService whOutboundOrderSeedService;

    @RequiresPermissions("wh:outboundOrder:view")
    @GetMapping()
    public String outboundOrder()
    {
        return prefix + "/outboundOrder";
    }

    /**
     * 查询商品出库单主表列表
     */
    @RequiresPermissions("wh:outboundOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WhOutboundOrder whOutboundOrder)
    {
        whOutboundOrder.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<WhOutboundOrder> list = whOutboundOrderService.selectWhOutboundOrderList(whOutboundOrder);
        return getDataTable(list);
    }

    /**
     * 新增商品出库单主表
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        //新增出库订单
        WhOutboundOrder whOutboundOrder = new WhOutboundOrder();
        whOutboundOrder.setOrderDate(DateUtils.getNowDate());
        whOutboundOrder.setOrderType(WhOutboundOrderType.OTHER.getCode());
        whOutboundOrder.setOrderCode(OrderNumGeneratorUtils.makeOrderNum(OrderConstants.SF));
        whOutboundOrder.setDiscountRate(BigDecimal.ONE);
        whOutboundOrder.setDiscountAmount(BigDecimal.ZERO);
        whOutboundOrder.setDiscountPrice(BigDecimal.ZERO);
        whOutboundOrder.setOtherFee(BigDecimal.ZERO);
        whOutboundOrder.setStatus(OutboundOrderStatus.THEDELIVERY.getCode());
        whOutboundOrder.setCreateBy(ShiroUtils.getLoginName());
        whOutboundOrder.setDeptId(ShiroUtils.getDeptId());
        whOutboundOrderService.insertWhOutboundOrder(whOutboundOrder);
        modelMap.put("whOutboundOrder", whOutboundOrder);
        return prefix + "/add";
    }

    /**
     * 新增保存商品出库单主表
     */
    @RequiresPermissions("wh:outboundOrder:add")
    @Log(title = "商品出库单主表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WhOutboundOrder whOutboundOrder)
    {
        return toAjax(whOutboundOrderService.insertWhOutboundOrder(whOutboundOrder));
    }

    /**
     * 修改商品出库单主表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WhOutboundOrder whOutboundOrder = whOutboundOrderService.selectWhOutboundOrderById(id);
        mmap.put("whOutboundOrder", whOutboundOrder);
        return prefix + "/add";
    }

    /**
     * 修改保存商品出库单主表
     */
    @RequiresPermissions("wh:outboundOrder:edit")
    @Log(title = "商品出库单主表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WhOutboundOrder whOutboundOrder)
    {
        if(StringUtils.isNotNull(whOutboundOrder.getCustomerId())){
            whOutboundOrder.setDesWarehouseId(whOutboundOrder.getCustomerId());
            whOutboundOrder.setDesWarehouseName(whOutboundOrder.getCustomerName());
        }
        whOutboundOrder.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(whOutboundOrderService.updateWhOutboundOrderShop(whOutboundOrder));
    }

    /**
     * 删除商品出库单主表
     */
    @RequiresPermissions("wh:outboundOrder:remove")
    @Log(title = "商品出库单主表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(whOutboundOrderService.deleteWhOutboundOrderByIds(ids));
    }

    /**
     * 修改商品出库单状态-生成拣货单
     */
    @RequiresPermissions("wh:outboundOrder:changeStatus")
    @Log(title = "商品出库单主表", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(WhOutboundOrder whOutboundOrder)
    {
        //检测是否已选中客户和加入商品
        WhOutboundOrder whOutboundOrder1 = whOutboundOrderService.selectWhOutboundOrderById(whOutboundOrder.getId());
        if(StringUtils.isNull(whOutboundOrder1.getCustomerId()) || StringUtils.isBlank(whOutboundOrder1.getOrderName()) || whOutboundOrderSeedService.getOrderSeedShopCount(whOutboundOrder.getId()) == 0  ){
            return AjaxResult.warn("请加入订单编号、客户或商品");
        }
        whOutboundOrder.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(whOutboundOrderService.changeStatus(whOutboundOrder));
    }

    /**
     * 修改商品入库单状态-确认作废
     */
    @RequiresPermissions("wh:outboundOrder:editRemove")
    @Log(title = "商品出库单主表", businessType = BusinessType.UPDATE)
    @PostMapping("/editRemove")
    @ResponseBody
    public AjaxResult editRemove(WhOutboundOrder whOutboundOrder)
    {
        return toAjax(whOutboundOrderService.editRemove(whOutboundOrder));
    }

    /**
     * 出货作业界面
     * @return
     */
    @RequiresPermissions("wh:warehousingOrder:viewWork")
    @GetMapping("/outboundOrderWord")
    public String outboundOrderWord()
    {
        return prefix + "/outboundOrderWord";
    }

    /**
     * 修改保存商品出库单主表-装车发货
     */
    @RequiresPermissions("wh:outboundOrder:editLoading")
    @Log(title = "商品出库单主表", businessType = BusinessType.UPDATE)
    @PostMapping("/editLoading")
    @ResponseBody
    public AjaxResult editLoading(WhOutboundOrder whOutboundOrder)
    {
        WhOutboundOrderSeed whOutboundOrderSeed = new WhOutboundOrderSeed();
        whOutboundOrderSeed.setOutboundOrderId(whOutboundOrder.getId());
        List<WhOutboundOrderSeed> whOutboundOrderSeedList = whOutboundOrderSeedService.selectWhOutboundOrderSeedList(whOutboundOrderSeed);
        for (WhOutboundOrderSeed outboundOrderSeed : whOutboundOrderSeedList) {//判断订单是否有商品欠货
            if(outboundOrderSeed.getOweNumber().intValue() > 0){
                return error("有商品欠货,请核对订单!");
            }
        }

        whOutboundOrder.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(whOutboundOrderService.editLoading(whOutboundOrder));
    }

    /**
     * 修改保存商品出库单主表-签收回单
     */
    @RequiresPermissions("wh:outboundOrder:editSignfor")
    @Log(title = "商品出库单主表", businessType = BusinessType.UPDATE)
    @PostMapping("/editSignfor")
    @ResponseBody
    public AjaxResult editSignfor(WhOutboundOrder whOutboundOrder)
    {
        whOutboundOrder.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(whOutboundOrderService.updateWhOutboundOrder(whOutboundOrder));
    }

    /**
     * 跳转选择界面
     */
    @GetMapping("/selectOutboundOrder/{desWarehouseId}")
    public String selectOutboundOrder(@PathVariable("desWarehouseId") Long desWarehouseId, ModelMap mmap)
    {
        mmap.put("desWarehouseId",desWarehouseId);
        return prefix + "/selectOutboundOrder";
    }

    /**
     * 查询商品出库管理列表(选择页面)
     */
    @PostMapping("/selectList")
    @ResponseBody
    public TableDataInfo selectList(WhOutboundOrder whOutboundOrder)
    {
        whOutboundOrder.setDeptId(ShiroUtils.getDeptId());
        startPage();
        List<WhOutboundOrder> list = whOutboundOrderService.selectWhOutboundOrderList(whOutboundOrder);
        return getDataTable(list);
    }
}
