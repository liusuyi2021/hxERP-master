package com.ruoyi.wh.service.impl;

import com.ruoyi.common.constant.OrderConstants;
import com.ruoyi.common.enums.WhOutboundOrderType;
import com.ruoyi.common.enums.WhWarehousingOrderType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.*;
import com.ruoyi.shop.domain.ShopGoodsSeed;
import com.ruoyi.shop.service.IShopGoodsSeedService;
import com.ruoyi.wh.domain.WhOutboundOrder;
import com.ruoyi.wh.domain.WhOutboundOrderSeed;
import com.ruoyi.wh.domain.WhWarehousingOrder;
import com.ruoyi.wh.domain.WhWarehousingOrderSeed;
import com.ruoyi.wh.enums.OutboundOrderStatus;
import com.ruoyi.wh.enums.WarehousingOrderStatus;
import com.ruoyi.wh.model.OrderModel;
import com.ruoyi.wh.model.OrderSeedModel;
import com.ruoyi.wh.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 单据精灵接口实现类
 * @author: miki
 * @modified By：注意：本内容仅限于湖南索孚威尔科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 * @time: 2021-06-13 12:18
 */
@Service
public class WhOrderServiceImpl implements IWhOrderService {
    @Autowired
    private IWhWarehousingOrderService whWarehousingOrderService;

    @Autowired
    private IWhWarehousingOrderSeedService whWarehousingOrderSeedService;

    @Autowired
    private IWhOutboundOrderService whOutboundOrderService;

    @Autowired
    private IWhOutboundOrderSeedService whOutboundOrderSeedService;

    @Autowired
    private IShopGoodsSeedService shopGoodsSeedService;

    @Override
    @Transactional
    public int edit(OrderModel orderModel) {
        int result = 0;
        if(OrderConstants.IF.equals(orderModel.getSheetType())){//退货单
            result = editIF(orderModel);
        }else if(OrderConstants.IB.equals(orderModel.getSheetType())){//销退单
            result = editIB(orderModel);
        }
        return result;
    }

    /**
     * 退货
     * @param orderModel
     * @return
     */
    public int editIF(OrderModel orderModel){
        int result = 0;
        //查询此单据是否已开过退货单，旧单据判断退货单商品数量是否大于原单据数量和是否还有库存可退货
        WhOutboundOrder whOutboundOrder = new WhOutboundOrder();
        whOutboundOrder.setWarehousingOrderId(orderModel.getOrderId());/** 关联入库单主键 */
        List<WhOutboundOrder> whOutboundOrderList = whOutboundOrderService.selectWhOutboundOrderList(whOutboundOrder);
        if(StringUtils.isNotEmpty(whOutboundOrderList)){
            throw new BusinessException("此单据已开过退货单，不可重复开单");
        }

        List<WhOutboundOrderSeed> whOutboundOrderSeedList = new ArrayList<>();
        WhWarehousingOrderSeed whWarehousingOrderSeed = new WhWarehousingOrderSeed();
        whWarehousingOrderSeed.setWarehousingOrderId(orderModel.getOrderId());
        List<WhWarehousingOrderSeed> whWarehousingOrderSeedList = whWarehousingOrderSeedService.selectWhWarehousingOrderSeedList(whWarehousingOrderSeed);
        BigDecimal sumAmount = BigDecimal.ZERO;
        for (OrderSeedModel orderSeedModel : orderModel.getOrderSeedModelList()) {
            for (WhWarehousingOrderSeed warehousingOrderSeed: whWarehousingOrderSeedList) {
                if(orderSeedModel.getId().equals(warehousingOrderSeed.getId())){
                    if(orderSeedModel.getPlanNumber() > warehousingOrderSeed.getWarehousingNumber()){
                        throw new BusinessException(warehousingOrderSeed.getShopGoodsName()+":退货数量不能大于此入库数量");
                    }
                    ShopGoodsSeed shopGoodsSeed = shopGoodsSeedService.selectShopGoodsSeedBygoodsId(orderSeedModel.getShopGoodsId());
                    Long kyNumber = shopGoodsSeed.getStockNumber()-shopGoodsSeed.getBeenPickingNumber();//库存可用量=库存数量-已拣货数量
                    if(kyNumber.intValue() < orderSeedModel.getPlanNumber().intValue()){
                        throw new BusinessException(warehousingOrderSeed.getShopGoodsName()+":退货数量不足,无法开单");
                    }
                    shopGoodsSeed.setForPickingNumber(shopGoodsSeed.getForPickingNumber()+orderSeedModel.getPlanNumber());//待拣货数量=原待拣货数量+新订单计划数量
                    result = shopGoodsSeedService.updateShopGoodsSeed(shopGoodsSeed);

                    WhOutboundOrderSeed whOutboundOrderSeed = new WhOutboundOrderSeed();
                    BeanUtils.copyProperties(warehousingOrderSeed,whOutboundOrderSeed);
                    whOutboundOrderSeed.setPlanNumber(orderSeedModel.getPlanNumber());/** 计划数量 */
                    whOutboundOrderSeed.setActualNumber(orderSeedModel.getPlanNumber());/** 实际数量 */
                    whOutboundOrderSeed.setAmount(ArithUtils.safeSubtracts(ArithUtils.safeMultiplys(new BigDecimal(whOutboundOrderSeed.getPlanNumber().toString()),whOutboundOrderSeed.getTaxUnitPrice()),whOutboundOrderSeed.getDiscountPrice()));/** 含税金额=(数量*含税单价)-折让 */
                    whOutboundOrderSeed.setTax(ArithUtils.safeMultiplys(whOutboundOrderSeed.getAmount(),whOutboundOrderSeed.getRate())); /** 合计税额 */
                    whOutboundOrderSeed.setUntaxedAmount(ArithUtils.safeSubtracts(whOutboundOrderSeed.getAmount(),whOutboundOrderSeed.getTax())); /** 未税金额 */
                    whOutboundOrderSeed.setCreateBy(orderModel.getCreateBy());
                    whOutboundOrderSeedList.add(whOutboundOrderSeed);
                    sumAmount = ArithUtils.safeAdds(whOutboundOrderSeed.getAmount());
                }
            }
        }
        WhWarehousingOrder warehousingOrder = whWarehousingOrderService.selectWhWarehousingOrderById(orderModel.getOrderId());
        whOutboundOrder.setOrderDate(DateUtils.getNowDate());
        whOutboundOrder.setOrderType(WhOutboundOrderType.RETURN.getCode());
        whOutboundOrder.setOrderCode(OrderNumGeneratorUtils.makeOrderNum(OrderConstants.IF));
        whOutboundOrder.setOrderName(orderModel.getOrderName());
        whOutboundOrder.setWarehouseId(warehousingOrder.getWarehouseId());/** 单位关系主键ID */
        whOutboundOrder.setDesWarehouseId(warehousingOrder.getSupplierId());/** 对象单位主键ID */
        whOutboundOrder.setDesWarehouseName(warehousingOrder.getSupplierName());/** 对象单位名称 */
        whOutboundOrder.setRate(warehousingOrder.getRate()); /** 税率 */
        whOutboundOrder.setDiscountRate(warehousingOrder.getDiscountRate());/** 折扣率 */
        //whOutboundOrder.setDiscountPrice(warehousingOrder.getDiscountPrice());/** 折让金额 退货时不计算在内*/
        //whOutboundOrder.setOtherFee(warehousingOrder.getOtherFee());/** 其他费用 退货时不计算在内*/
        whOutboundOrder.setDiscountAmount(ArithUtils.safeSubtracts(sumAmount,ArithUtils.safeMultiplys(sumAmount,warehousingOrder.getDiscountRate())));/** 折扣金额=商品总金额-(商品总金额*折扣率) */
        whOutboundOrder.setTotalAmount(ArithUtils.safeSubtracts(sumAmount,whOutboundOrder.getDiscountAmount()));  /** 含税金额= 商品总金额-折扣金额（不折让金额和其他费用不计算在内，财务结算时统一调整）*/
        whOutboundOrder.setTax(ArithUtils.safeMultiplys(whOutboundOrder.getTotalAmount(),whOutboundOrder.getRate()));/** 税额=含税金额*税率 */
        whOutboundOrder.setUntaxedAmount(ArithUtils.safeSubtracts(whOutboundOrder.getTotalAmount(),whOutboundOrder.getTax())); /** 未税金额= 含税金额-税额=*/
        whOutboundOrder.setStatus(OutboundOrderStatus.FORPICKING.getCode());
        whOutboundOrder.setCreateBy(orderModel.getCreateBy());
        whOutboundOrder.setDeptId(warehousingOrder.getDeptId());
        whOutboundOrder.setRemark(orderModel.getRemark());
        result = whOutboundOrderService.insertWhOutboundOrder(whOutboundOrder);
        whOutboundOrderSeedList.forEach(o -> o.setOutboundOrderId(whOutboundOrder.getId()));//赋值
        result = whOutboundOrderSeedService.insertWhOutboundOrderSeedBatch(whOutboundOrderSeedList);
        return result;
    }

    /**
     * 销退
     * @param orderModel
     * @return
     */
    public int editIB(OrderModel orderModel){
        int result = 0;
        //查询此单据是否已开过销退单，旧单据判断销退单商品数量是否大于原单据数量和是否还有库存可销退
        WhWarehousingOrder whWarehousingOrder = new WhWarehousingOrder();
        whWarehousingOrder.setOutboundOrderId(orderModel.getOrderId());/** 关联入库单主键 */
        List<WhWarehousingOrder> whWarehousingOrderList = whWarehousingOrderService.selectWhWarehousingOrderList(whWarehousingOrder);
        if(StringUtils.isNotEmpty(whWarehousingOrderList)){
            throw new BusinessException("此单据已开过销退单，不可重复开单");
        }
        List<WhWarehousingOrderSeed> whWarehousingOrderSeedList = new ArrayList<>();
        WhOutboundOrderSeed outboundOrderSeed = new WhOutboundOrderSeed();
        outboundOrderSeed.setOutboundOrderId(orderModel.getOrderId());
        List<WhOutboundOrderSeed> outboundOrderSeedList = whOutboundOrderSeedService.selectWhOutboundOrderSeedList(outboundOrderSeed);
        BigDecimal sumAmount = BigDecimal.ZERO;
        for (OrderSeedModel orderSeedModel : orderModel.getOrderSeedModelList()) {
            for (WhOutboundOrderSeed whOutboundOrderSeed: outboundOrderSeedList) {
                if(orderSeedModel.getId().equals(whOutboundOrderSeed.getId())){
                    if(orderSeedModel.getPlanNumber() > whOutboundOrderSeed.getActualNumber()){
                        throw new BusinessException(whOutboundOrderSeed.getShopGoodsName()+":销退数量不能大于此出库数量");
                    }

                    WhWarehousingOrderSeed warehousingOrderSeed = new WhWarehousingOrderSeed();
                    BeanUtils.copyProperties(whOutboundOrderSeed,warehousingOrderSeed);
                    warehousingOrderSeed.setPlanNumber(orderSeedModel.getPlanNumber());/** 计划数量 */
                    warehousingOrderSeed.setWarehousingNumber(orderSeedModel.getPlanNumber());/** 实际数量 */
                    warehousingOrderSeed.setAmount(ArithUtils.safeSubtracts(ArithUtils.safeMultiplys(new BigDecimal(warehousingOrderSeed.getPlanNumber().toString()),warehousingOrderSeed.getTaxUnitPrice()),warehousingOrderSeed.getDiscountPrice()));/** 含税金额=(数量*含税单价)-折让 */
                    warehousingOrderSeed.setTax(ArithUtils.safeMultiplys(warehousingOrderSeed.getAmount(),warehousingOrderSeed.getRate())); /** 合计税额 */
                    warehousingOrderSeed.setUntaxedAmount(ArithUtils.safeSubtracts(warehousingOrderSeed.getAmount(),warehousingOrderSeed.getTax())); /** 未税金额 */
                    warehousingOrderSeed.setCreateBy(orderModel.getCreateBy());
                    whWarehousingOrderSeedList.add(warehousingOrderSeed);
                    sumAmount = ArithUtils.safeAdds(warehousingOrderSeed.getAmount());
                }
            }
        }

        WhOutboundOrder whOutboundOrder = whOutboundOrderService.selectWhOutboundOrderById(orderModel.getOrderId());
        whWarehousingOrder.setOrderDate(DateUtils.getNowDate());
        whWarehousingOrder.setOrderType(WhWarehousingOrderType.SELLBACK.getCode());
        whWarehousingOrder.setOrderCode(OrderNumGeneratorUtils.makeOrderNum(OrderConstants.IB));
        whWarehousingOrder.setOrderName(orderModel.getOrderName());
        whWarehousingOrder.setWarehouseId(whOutboundOrder.getWarehouseId());/** 单位关系主键ID */
        whWarehousingOrder.setDesWarehouseId(whOutboundOrder.getCustomerId());/** 对象单位主键ID */
        whWarehousingOrder.setDesWarehouseName(whOutboundOrder.getCustomerName());/** 对象单位名称 */
        whWarehousingOrder.setRate(whOutboundOrder.getRate()); /** 税率 */
        whWarehousingOrder.setDiscountRate(whOutboundOrder.getDiscountRate());/** 折扣率 */
        //whWarehousingOrder.setDiscountPrice(whOutboundOrder.getDiscountPrice());/** 折让金额 销退时不计算在内*/
        //whWarehousingOrder.setOtherFee(whOutboundOrder.getOtherFee());/** 其他费用 销退时不计算在内*/
        whWarehousingOrder.setDiscountAmount(ArithUtils.safeMultiplys(sumAmount,whOutboundOrder.getDiscountRate()));/** 折扣金额=商品总金额*折扣率 */
        whWarehousingOrder.setTotalAmount(whWarehousingOrder.getDiscountAmount());  /** 含税金额= 折扣金额（不折让金额和其他费用不计算在内，财务结算时统一调整）*/
        whWarehousingOrder.setTax(ArithUtils.safeMultiplys(whWarehousingOrder.getTotalAmount(),whWarehousingOrder.getRate()));/** 税额=含税金额*税率 */
        whWarehousingOrder.setUntaxedAmount(ArithUtils.safeSubtracts(whWarehousingOrder.getTotalAmount(),whWarehousingOrder.getTax())); /** 未税金额= 含税金额-税额=*/
        whWarehousingOrder.setStatus(WarehousingOrderStatus.UNLOADED.getCode());
        whWarehousingOrder.setCreateBy(orderModel.getCreateBy());
        whWarehousingOrder.setDeptId(whOutboundOrder.getDeptId());
        whWarehousingOrder.setRemark(orderModel.getRemark());
        result = whWarehousingOrderService.insertWhWarehousingOrder(whWarehousingOrder);
        whWarehousingOrderSeedList.forEach(o -> o.setWarehousingOrderId(whWarehousingOrder.getId()));//赋值
        result = whWarehousingOrderSeedService.insertWhWarehousingOrderSeedBatch(whWarehousingOrderSeedList);
        return result;
    }
}
