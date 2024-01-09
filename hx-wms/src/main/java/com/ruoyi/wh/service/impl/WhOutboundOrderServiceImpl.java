package com.ruoyi.wh.service.impl;

import com.ruoyi.basis.domain.BasisCustomer;
import com.ruoyi.basis.domain.BasisDiscountPermission;
import com.ruoyi.basis.service.IBasisCustomerService;
import com.ruoyi.basis.service.IBasisDiscountPermissionService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.Status;
import com.ruoyi.common.enums.WhWarehousingOrderType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.ArithUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.shop.domain.ShopGoodsSeed;
import com.ruoyi.shop.service.IShopGoodsSeedService;
import com.ruoyi.wh.domain.WhOutboundOrder;
import com.ruoyi.wh.domain.WhOutboundOrderSeed;
import com.ruoyi.wh.mapper.WhOutboundOrderMapper;
import com.ruoyi.wh.service.IWhOutboundOrderSeedService;
import com.ruoyi.wh.service.IWhOutboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品出库单主表Service业务层处理
 * 
 * @author miki
 * @date 2021-06-07
 */
@Service
public class WhOutboundOrderServiceImpl implements IWhOutboundOrderService 
{
    @Autowired
    private WhOutboundOrderMapper whOutboundOrderMapper;

    @Autowired
    private IWhOutboundOrderSeedService whOutboundOrderSeedService;

    @Autowired
    private IBasisCustomerService basisCustomerService;

    @Autowired
    private IShopGoodsSeedService shopGoodsSeedService;

    @Autowired
    private IBasisDiscountPermissionService basisDiscountPermissionService;

    /**
     * 查询商品出库单主表
     * 
     * @param id 商品出库单主表ID
     * @return 商品出库单主表
     */
    @Override
    public WhOutboundOrder selectWhOutboundOrderById(Long id)
    {
        return whOutboundOrderMapper.selectWhOutboundOrderById(id);
    }

    /**
     * 查询商品出库单主表列表
     * 
     * @param whOutboundOrder 商品出库单主表
     * @return 商品出库单主表
     */
    @Override
    public List<WhOutboundOrder> selectWhOutboundOrderList(WhOutboundOrder whOutboundOrder)
    {
        return whOutboundOrderMapper.selectWhOutboundOrderList(whOutboundOrder);
    }

    /**
     * 新增商品出库单主表
     * 
     * @param whOutboundOrder 商品出库单主表
     * @return 结果
     */
    @Override
    public int insertWhOutboundOrder(WhOutboundOrder whOutboundOrder)
    {
        whOutboundOrder.setCreateTime(DateUtils.getNowDate());
        return whOutboundOrderMapper.insertWhOutboundOrder(whOutboundOrder);
    }

    /**
     * 修改商品出库单主表
     * 
     * @param whOutboundOrder 商品出库单主表
     * @return 结果
     */
    @Override
    public int updateWhOutboundOrder(WhOutboundOrder whOutboundOrder)
    {
        whOutboundOrder.setUpdateTime(DateUtils.getNowDate());
        return whOutboundOrderMapper.updateWhOutboundOrder(whOutboundOrder);
    }

    @Override
    @Transactional
    public int updateWhOutboundOrderShop(WhOutboundOrder whOutboundOrder) {
        int result = 0;
        //TODO 步骤:1.先重新获得存储客户税率，以防客户已做更改税率发生变化，2.按最新税率重新计算所有商品价格(备注:数量和含税单价不受税率影响),3.在梳理计算主表价格并且存库
        //步骤1
        whOutboundOrder.setUpdateTime(DateUtils.getNowDate());
        BasisCustomer basisCustomer = basisCustomerService.selectBasisCustomerById(whOutboundOrder.getCustomerId());
        whOutboundOrder.setRate(basisCustomer.getInvoiceTax());

        //步骤2
        WhOutboundOrderSeed whOutboundOrderSeed = new WhOutboundOrderSeed();
        whOutboundOrderSeed.setOutboundOrderId(whOutboundOrder.getId());
        List<WhOutboundOrderSeed> whOutboundOrderSeedList = whOutboundOrderSeedService.selectWhOutboundOrderSeedList(whOutboundOrderSeed);
        for (WhOutboundOrderSeed outboundOrderSeed : whOutboundOrderSeedList) {
            outboundOrderSeed = whOutboundOrderSeedService.calculate(whOutboundOrder,outboundOrderSeed,whOutboundOrder.getRate());
            outboundOrderSeed.setUpdateBy(whOutboundOrder.getUpdateBy());
            result = whOutboundOrderSeedService.updateWhOutboundOrderSeed(outboundOrderSeed);
        }

        //步骤3
        whOutboundOrder = calculate(whOutboundOrder,whOutboundOrder.getRate());
        result = whOutboundOrderMapper.updateWhOutboundOrder(whOutboundOrder);
        return result;
    }

    /**
     * 删除商品出库单主表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWhOutboundOrderByIds(String ids)
    {
        int result = 0;
        Long[] whOutboundOrderids = Convert.toLongArray(ids);
        for (Long whOutboundOrderid : whOutboundOrderids ) {
            WhOutboundOrder whOutboundOrder = new WhOutboundOrder();
            whOutboundOrder.setId(whOutboundOrderid);
            whOutboundOrder.setDelFlag(Status.DELETED.getCode());
            whOutboundOrder.setUpdateBy(ShiroUtils.getLoginName());
            result = updateWhOutboundOrder(whOutboundOrder);
        }
        return result;
    }

    /**
     * 删除商品出库单主表信息
     * 
     * @param id 商品出库单主表ID
     * @return 结果
     */
    @Override
    public int deleteWhOutboundOrderById(Long id)
    {
        return whOutboundOrderMapper.deleteWhOutboundOrderById(id);
    }

    @Override
    @Transactional
    public int editRemove(WhOutboundOrder whOutboundOrder) {
        int result = 0;
        WhOutboundOrderSeed whOutboundOrderSeed = new WhOutboundOrderSeed();
        whOutboundOrderSeed.setOutboundOrderId(whOutboundOrder.getId());
        List<WhOutboundOrderSeed> outboundOrderSeedList = whOutboundOrderSeedService.selectWhOutboundOrderSeedList(whOutboundOrderSeed);
        for (WhOutboundOrderSeed outboundOrderSeed: outboundOrderSeedList) {
            ShopGoodsSeed  shopGoodsSeed = shopGoodsSeedService.selectShopGoodsSeedBygoodsId(outboundOrderSeed.getShopGoodsId());
            shopGoodsSeed.setForPickingNumber(shopGoodsSeed.getForPickingNumber()- outboundOrderSeed.getPlanNumber());//待拣货数量=原待拣货数量-新订单计划数量
            result = shopGoodsSeedService.updateShopGoodsSeed(shopGoodsSeed);
        }
        result =  deleteWhOutboundOrderByIds(whOutboundOrder.getId().toString());
        return result;
    }

    @Override
    @Transactional
    public int changeStatus(WhOutboundOrder whOutboundOrder) {
        int result = 0;
        WhOutboundOrderSeed whOutboundOrderSeed = new WhOutboundOrderSeed();
        whOutboundOrderSeed.setOutboundOrderId(whOutboundOrder.getId());
        List<WhOutboundOrderSeed> outboundOrderSeedList = whOutboundOrderSeedService.selectWhOutboundOrderSeedList(whOutboundOrderSeed);
        for (WhOutboundOrderSeed outboundOrderSeed: outboundOrderSeedList) {
            ShopGoodsSeed  shopGoodsSeed = shopGoodsSeedService.selectShopGoodsSeedBygoodsId(outboundOrderSeed.getShopGoodsId());
            shopGoodsSeed.setForPickingNumber(shopGoodsSeed.getForPickingNumber()+outboundOrderSeed.getPlanNumber());//待拣货数量=原待拣货数量+新订单计划数量
            result = shopGoodsSeedService.updateShopGoodsSeed(shopGoodsSeed);
        }
        result = updateWhOutboundOrder(whOutboundOrder);
        return result;
    }

    @Override
    @Transactional
    public int editLoading(WhOutboundOrder whOutboundOrder) {
        int result = 0;
        WhOutboundOrderSeed whOutboundOrderSeed = new WhOutboundOrderSeed();
        whOutboundOrderSeed.setOutboundOrderId(whOutboundOrder.getId());
        List<WhOutboundOrderSeed> outboundOrderSeedList = whOutboundOrderSeedService.selectWhOutboundOrderSeedList(whOutboundOrderSeed);
        for (WhOutboundOrderSeed outboundOrderSeed: outboundOrderSeedList) {
            ShopGoodsSeed  shopGoodsSeed = shopGoodsSeedService.selectShopGoodsSeedBygoodsId(outboundOrderSeed.getShopGoodsId());
            shopGoodsSeed.setBeenPickingNumber(shopGoodsSeed.getBeenPickingNumber() - outboundOrderSeed.getActualNumber());//已拣货数量
            shopGoodsSeed.setStockNumber(shopGoodsSeed.getStockNumber() - outboundOrderSeed.getActualNumber());//库存数量
            shopGoodsSeed.setUpdateBy(whOutboundOrder.getUpdateBy());
            result = shopGoodsSeedService.updateShopGoodsSeed(shopGoodsSeed);
        }
        result = updateWhOutboundOrder(whOutboundOrder);
        return result;
    }

    /**
     * 计算统计出库单主表各项价格
     * @param whOutboundOrder 商品出库单主表
     * @param rate 出库主表供应商税率
     * @return 结果
     */
    @Override
    public WhOutboundOrder calculate(WhOutboundOrder whOutboundOrder, BigDecimal rate) {
        BasisDiscountPermission basisDiscountPermission = basisDiscountPermissionService.selectBasisDiscountPermissionByUserId(ShiroUtils.getUserId());
        if(StringUtils.isNull(basisDiscountPermission)){throw new BusinessException("未开通折扣或折让权限");}

        if(StringUtils.isNotNull(whOutboundOrder.getDiscountRate()) && !WhWarehousingOrderType.TRANSFER.getCode().equals(whOutboundOrder.getOrderType()) && whOutboundOrder.getDiscountRate().compareTo(BigDecimal.ONE) == -1 ){//a小于b
            if(basisDiscountPermission.getMinDiscountRate().compareTo(whOutboundOrder.getDiscountRate()) == 1){throw new BusinessException("已超过最低折扣权限:"+basisDiscountPermission.getMinDiscountRate());}
        }
        if( StringUtils.isNotNull(whOutboundOrder.getDiscountPrice()) && whOutboundOrder.getDiscountPrice().compareTo(BigDecimal.ZERO) == 1){//a大于b
            if(basisDiscountPermission.getMaxDiscountPrice().compareTo(whOutboundOrder.getDiscountPrice()) == -1){throw new BusinessException("已超过最大折让权:"+basisDiscountPermission.getMaxDiscountPrice());  }
        }

        BigDecimal sumAmount = whOutboundOrderSeedService.getSumAmount(whOutboundOrder.getId());//查询出库单所有商品合计金额
        whOutboundOrder.setDiscountAmount(ArithUtils.safeSubtracts(sumAmount ,ArithUtils.safeMultiplys(sumAmount,whOutboundOrder.getDiscountRate())) );/** 折扣金额=商品总金额-(商品总金额*折扣率) */
        whOutboundOrder.setTotalAmount(ArithUtils.safeAdds(ArithUtils.safeSubtracts(sumAmount,whOutboundOrder.getDiscountAmount(),whOutboundOrder.getDiscountPrice()), whOutboundOrder.getOtherFee()));  /** 含税金额= 商品合计金额-折扣金额-折让金额+其他费用*/
        whOutboundOrder.setTax(ArithUtils.safeMultiplys(whOutboundOrder.getTotalAmount(),rate));/** 税额=含税金额*税率 */
        whOutboundOrder.setUntaxedAmount(ArithUtils.safeSubtracts(whOutboundOrder.getTotalAmount(),whOutboundOrder.getTax())); /** 未税金额= 含税金额-税额=*/
        return whOutboundOrder;
    }
}
