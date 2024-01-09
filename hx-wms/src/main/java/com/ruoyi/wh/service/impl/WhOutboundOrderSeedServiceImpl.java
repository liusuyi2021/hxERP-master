package com.ruoyi.wh.service.impl;

import com.ruoyi.basis.domain.BasisDiscountPermission;
import com.ruoyi.basis.domain.DeptWarehouse;
import com.ruoyi.basis.enums.CostType;
import com.ruoyi.basis.service.IBasisDiscountPermissionService;
import com.ruoyi.basis.service.IDeptWarehouseService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.Status;
import com.ruoyi.common.enums.WhWarehousingOrderType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.ArithUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.shop.domain.ShopGoods;
import com.ruoyi.shop.domain.ShopGoodsSeed;
import com.ruoyi.shop.service.IShopGoodsSeedService;
import com.ruoyi.shop.service.IShopGoodsService;
import com.ruoyi.wh.domain.WhOutboundOrder;
import com.ruoyi.wh.domain.WhOutboundOrderSeed;
import com.ruoyi.wh.domain.WhOutboundStockout;
import com.ruoyi.wh.enums.OutboundOrderStatus;
import com.ruoyi.wh.mapper.WhOutboundOrderSeedMapper;
import com.ruoyi.wh.model.OutboundOrderSeedModel;
import com.ruoyi.wh.service.IWhOutboundOrderSeedService;
import com.ruoyi.wh.service.IWhOutboundOrderService;
import com.ruoyi.wh.service.IWhOutboundStockoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品出库单子表Service业务层处理
 * 
 * @author miki
 * @date 2021-06-07
 */
@Service
public class WhOutboundOrderSeedServiceImpl implements IWhOutboundOrderSeedService 
{
    @Autowired
    private WhOutboundOrderSeedMapper whOutboundOrderSeedMapper;

    @Autowired
    private IWhOutboundOrderService whOutboundOrderService;

    @Autowired
    private IShopGoodsSeedService shopGoodsSeedService;

    @Autowired
    private IWhOutboundStockoutService whOutboundStockoutService;//缺货管制订单

    @Autowired
    private IBasisDiscountPermissionService basisDiscountPermissionService;

    @Autowired
    private IDeptWarehouseService deptWarehouseService;

    @Autowired
    private IShopGoodsService shopGoodsService;

    /**
     * 查询商品出库单子表
     * 
     * @param id 商品出库单子表ID
     * @return 商品出库单子表
     */
    @Override
    public WhOutboundOrderSeed selectWhOutboundOrderSeedById(Long id)
    {
        return whOutboundOrderSeedMapper.selectWhOutboundOrderSeedById(id);
    }

    /**
     * 查询商品出库单子表列表
     * 
     * @param whOutboundOrderSeed 商品出库单子表
     * @return 商品出库单子表
     */
    @Override
    public List<WhOutboundOrderSeed> selectWhOutboundOrderSeedList(WhOutboundOrderSeed whOutboundOrderSeed)
    {
        return whOutboundOrderSeedMapper.selectWhOutboundOrderSeedList(whOutboundOrderSeed);
    }

    /**
     * 新增商品出库单子表
     * 
     * @param whOutboundOrderSeed 商品出库单子表
     * @return 结果
     */
    @Override
    @Transactional
    public int insertWhOutboundOrderSeed(WhOutboundOrderSeed whOutboundOrderSeed)
    {
        int result = 0;
        whOutboundOrderSeed.setCreateTime(DateUtils.getNowDate());
        //统计商品价格各项值
        WhOutboundOrder whOutboundOrder =  whOutboundOrderService.selectWhOutboundOrderById(whOutboundOrderSeed.getOutboundOrderId());
        whOutboundOrderSeed = calculate(whOutboundOrder,whOutboundOrderSeed,whOutboundOrder.getRate());
        result = whOutboundOrderSeedMapper.insertWhOutboundOrderSeed(whOutboundOrderSeed);

        //主表总价格受影响需重新计算
        whOutboundOrder = whOutboundOrderService.calculate(whOutboundOrder,whOutboundOrder.getRate());
        whOutboundOrder.setUpdateBy(whOutboundOrderSeed.getCreateBy());
        whOutboundOrder.setUpdateTime(whOutboundOrderSeed.getCreateTime());
        result = whOutboundOrderService.updateWhOutboundOrder(whOutboundOrder);

        return result;
    }

    /**
     * 修改商品出库单子表
     * 
     * @param whOutboundOrderSeed 商品出库单子表
     * @return 结果
     */
    @Override
    public int updateWhOutboundOrderSeed(WhOutboundOrderSeed whOutboundOrderSeed)
    {
        whOutboundOrderSeed.setUpdateTime(DateUtils.getNowDate());
        return whOutboundOrderSeedMapper.updateWhOutboundOrderSeed(whOutboundOrderSeed);
    }

    @Override
    @Transactional
    public int updateWhOutboundOrderSeedRecalculate(WhOutboundOrderSeed whOutboundOrderSeed) {
        int result = 0;
        whOutboundOrderSeed.setUpdateTime(DateUtils.getNowDate());
        //统计商品价格各项值
        WhOutboundOrder whOutboundOrder =  whOutboundOrderService.selectWhOutboundOrderById(selectWhOutboundOrderSeedById(whOutboundOrderSeed.getId()).getOutboundOrderId());
        whOutboundOrderSeed = calculate(whOutboundOrder,whOutboundOrderSeed,whOutboundOrder.getRate());
        result = whOutboundOrderSeedMapper.updateWhOutboundOrderSeed(whOutboundOrderSeed);

        //主表总价格受影响需重新计算
        whOutboundOrder = whOutboundOrderService.calculate(whOutboundOrder,whOutboundOrder.getRate());
        whOutboundOrder.setUpdateBy(whOutboundOrderSeed.getUpdateBy());
        result = whOutboundOrderService.updateWhOutboundOrder(whOutboundOrder);
        return result;
    }

    /**
     * 删除商品出库单子表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteWhOutboundOrderSeedByIds(String ids)
    {
        int result = 0;
        Long[] wOutboundOrderSeedids = Convert.toLongArray(ids);

        Long outboundOrderId =0L;
        for (Long wOutboundOrderSeedid : wOutboundOrderSeedids ) {
            outboundOrderId = selectWhOutboundOrderSeedById(wOutboundOrderSeedid).getOutboundOrderId();
            WhOutboundOrderSeed whOutboundOrderSeed = new WhOutboundOrderSeed();
            whOutboundOrderSeed.setId(wOutboundOrderSeedid);
            whOutboundOrderSeed.setDelFlag(Status.DELETED.getCode());
            whOutboundOrderSeed.setUpdateBy(ShiroUtils.getLoginName());
            result = updateWhOutboundOrderSeed(whOutboundOrderSeed);
        }

        //主表总价格受影响需重新计算
        WhOutboundOrder whOutboundOrder =  whOutboundOrderService.selectWhOutboundOrderById(outboundOrderId);
        whOutboundOrder = whOutboundOrderService.calculate(whOutboundOrder,whOutboundOrder.getRate());
        whOutboundOrder.setUpdateBy(ShiroUtils.getLoginName());
        result = whOutboundOrderService.updateWhOutboundOrder(whOutboundOrder);
        return result;
    }

    /**
     * 删除商品出库单子表信息
     * 
     * @param id 商品出库单子表ID
     * @return 结果
     */
    @Override
    public int deleteWhOutboundOrderSeedById(Long id)
    {
        return whOutboundOrderSeedMapper.deleteWhOutboundOrderSeedById(id);
    }

    @Override
    public BigDecimal getSumAmount(Long outboundOrderId) {
        return whOutboundOrderSeedMapper.getSumAmount(outboundOrderId);
    }

    @Override
    public int getOrderSeedShopCount(Long outboundOrderId) {
        return whOutboundOrderSeedMapper.getOrderSeedShopCount(outboundOrderId);
    }

    @Override
    @Transactional
    public int updateCompleteSave(OutboundOrderSeedModel outboundOrderSeedModel) {
        int result = 0;
        //步骤1 统计实际数量，实际出库数量=计划出库数量，如果实际数量少于计划量，则商品进入缺货管制中，等待解欠
        List<WhOutboundOrderSeed> outboundOrderSeedList = outboundOrderSeedModel.getOutboundOrderSeedList();
        for (WhOutboundOrderSeed outboundOrderSeed:outboundOrderSeedList) {
              //查询库存可用量
            ShopGoodsSeed  shopGoodsSeed = shopGoodsSeedService.selectShopGoodsSeedBygoodsId(outboundOrderSeed.getShopGoodsId());
            Long kyNumber = shopGoodsSeed.getStockNumber()-shopGoodsSeed.getBeenPickingNumber();//库存可用量=库存数量-已拣货数量
            if(kyNumber.intValue() >= outboundOrderSeed.getPlanNumber().intValue()){//可用量等于或大于计划数量 表示可拣货
                outboundOrderSeed.setActualNumber(outboundOrderSeed.getPlanNumber());
                shopGoodsSeed.setForPickingNumber(shopGoodsSeed.getForPickingNumber()-outboundOrderSeed.getPlanNumber());//待拣货数量=原待拣货数量-计划数量
                shopGoodsSeed.setBeenPickingNumber(shopGoodsSeed.getBeenPickingNumber()+outboundOrderSeed.getPlanNumber());//已拣货数量=原已拣货数量+计划数量
            }else{//可用量小于计划数量
                outboundOrderSeed.setActualNumber(kyNumber);//实际数量=库存数量-已拣货数量
                outboundOrderSeed.setOweNumber(outboundOrderSeed.getPlanNumber()-outboundOrderSeed.getActualNumber());//欠货数量=计划数量-实际数量
                shopGoodsSeed.setForPickingNumber(shopGoodsSeed.getForPickingNumber()-outboundOrderSeed.getActualNumber());//待拣货数量=原待拣货数量-实际数量
                shopGoodsSeed.setBeenPickingNumber(shopGoodsSeed.getBeenPickingNumber()+outboundOrderSeed.getActualNumber());//已拣货数量=原已拣货数量+实际数量

                WhOutboundStockout whOutboundStockout = new WhOutboundStockout();
                whOutboundStockout.setOutboundOrderId(outboundOrderSeedModel.getOutboundOrderId());/** 商品出库主表ID */
                whOutboundStockout.setOutboundOrderSeedId(outboundOrderSeed.getId());/** 商品出库子表ID */
                whOutboundStockout.setShopGoodsId(outboundOrderSeed.getShopGoodsId());/** 商品主键ID */
                whOutboundStockout.setOweNumber(outboundOrderSeed.getPlanNumber()-outboundOrderSeed.getActualNumber());//欠货数量=计划数量-实际数量
                whOutboundStockout.setDeptId(ShiroUtils.getDeptId());
                whOutboundStockout.setCreateBy(outboundOrderSeedModel.getUpdateBy());
                result = whOutboundStockoutService.insertWhOutboundStockout(whOutboundStockout);
            }
            result = shopGoodsSeedService.updateShopGoodsSeed(shopGoodsSeed);

            outboundOrderSeed.setUpdateBy(outboundOrderSeedModel.getUpdateBy());
            result =  updateWhOutboundOrderSeed(outboundOrderSeed);
        }

        WhOutboundOrder outboundOrder = new WhOutboundOrder();
        outboundOrder.setId(outboundOrderSeedModel.getOutboundOrderId());
        outboundOrder.setStatus(OutboundOrderStatus.HASBEENPICKING.getCode());
        outboundOrder.setUpdateBy(outboundOrderSeedModel.getUpdateBy());
        result = whOutboundOrderService.updateWhOutboundOrder(outboundOrder);
        return result;
    }

    /**
     * 计算统计子表各项价格
     * @param whOutboundOrderSeed 商品出库单子表
     * @param rate rate 出库主表供应商税率
     * @return whOutboundOrderSeed 商品出库单子
     */
    public WhOutboundOrderSeed calculate(WhOutboundOrder whOutboundOrder,WhOutboundOrderSeed whOutboundOrderSeed, BigDecimal rate){
        if(!WhWarehousingOrderType.TRANSFER.getCode().equals(whOutboundOrder.getOrderType()) && StringUtils.isNotNull(whOutboundOrderSeed.getDiscountPrice()) && whOutboundOrderSeed.getDiscountPrice().compareTo(BigDecimal.ZERO) == 1){//折让不为空，并且大于0时
            BasisDiscountPermission basisDiscountPermission = basisDiscountPermissionService.selectBasisDiscountPermissionByUserId(ShiroUtils.getUserId());
            if(StringUtils.isNull(basisDiscountPermission)){//判断员工是否有权限问题
                throw new BusinessException("未开通折扣折让权限");
            }else if(StringUtils.isNotNull(basisDiscountPermission)  &&  basisDiscountPermission.getMaxDiscountPrice().compareTo(whOutboundOrderSeed.getDiscountPrice()) == -1){
                throw new BusinessException("已超过最大折让权限");
            }
        }

        BigDecimal price=whOutboundOrderSeed.getTaxUnitPrice();
        if(WhWarehousingOrderType.TRANSFER.getCode().equals(whOutboundOrder.getOrderType())){//如果是请拔单则根据单位关系选择价格
            DeptWarehouse deptWarehouse = deptWarehouseService.selectDeptWarehouseByWarehouseId(whOutboundOrder.getWarehouseId(),whOutboundOrder.getDeptId());
            ShopGoods shopGoods = shopGoodsService.selectShopGoodsById(whOutboundOrderSeed.getShopGoodsId());
            if(CostType.SELLINGPRICE.getCode().equals(deptWarehouse.getCostType())){
                price=shopGoods.getSellingPrice();
            }else if(CostType.WHOLESALEPRICE.getCode().equals(deptWarehouse.getCostType())){
                price=shopGoods.getWholesalePrice();
            }else if(CostType.COSTPRICE.getCode().equals(deptWarehouse.getCostType())){
                price=shopGoods.getCostPrice();
            }else if(CostType.RETAILPRICE.getCode().equals(deptWarehouse.getCostType())){
                price=shopGoods.getRetailPrice();
            }
            whOutboundOrderSeed.setTaxUnitPrice(price);
        }

        whOutboundOrderSeed.setRate(rate);//税率
        whOutboundOrderSeed.setAmount(ArithUtils.safeSubtracts(ArithUtils.safeMultiplys(new BigDecimal(whOutboundOrderSeed.getPlanNumber().toString()),price),whOutboundOrderSeed.getDiscountPrice()));//含税金额=(数量*含税单价)-折让
        whOutboundOrderSeed.setUnitPrice(ArithUtils.safeMultiplys(price,whOutboundOrderSeed.getRate()));//未税单价=含税单价*税率
        whOutboundOrderSeed.setTax(ArithUtils.safeMultiplys(whOutboundOrderSeed.getAmount(),whOutboundOrderSeed.getRate()));//合计税额=含税金额*税率
        whOutboundOrderSeed.setUntaxedAmount(ArithUtils.safeSubtracts(whOutboundOrderSeed.getAmount(),whOutboundOrderSeed.getTax()));//未税金额=含税金额-税额

        return whOutboundOrderSeed;
    }

    @Override
    public int insertWhOutboundOrderSeedBatch(List<WhOutboundOrderSeed> whOutboundOrderSeedList) {
        return whOutboundOrderSeedMapper.insertWhOutboundOrderSeedBatch(whOutboundOrderSeedList);
    }
}
