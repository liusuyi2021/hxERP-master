package com.ruoyi.wh.service.impl;

import com.ruoyi.basis.domain.BasisDiscountPermission;
import com.ruoyi.basis.domain.DeptWarehouse;
import com.ruoyi.basis.enums.CostType;
import com.ruoyi.basis.service.IBasisDiscountPermissionService;
import com.ruoyi.basis.service.IDeptWarehouseService;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.constant.UserConstants;
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
import com.ruoyi.shop.mode.ShopGoodsSeedMode;
import com.ruoyi.shop.service.IShopGoodsSeedService;
import com.ruoyi.shop.service.IShopGoodsService;
import com.ruoyi.wh.domain.WhWarehousingOrder;
import com.ruoyi.wh.domain.WhWarehousingOrderSeed;
import com.ruoyi.wh.enums.WarehousingOrderStatus;
import com.ruoyi.wh.mapper.WhWarehousingOrderSeedMapper;
import com.ruoyi.wh.model.WarehousingOrderSeedModel;
import com.ruoyi.wh.service.IWhStorageService;
import com.ruoyi.wh.service.IWhWarehousingOrderSeedService;
import com.ruoyi.wh.service.IWhWarehousingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * 商品入库单子Service业务层处理
 * 
 * @author miki
 * @date 2021-05-26
 */
@Service
public class WhWarehousingOrderSeedServiceImpl implements IWhWarehousingOrderSeedService 
{
    @Autowired
    private WhWarehousingOrderSeedMapper whWarehousingOrderSeedMapper;

    @Autowired
    private IWhWarehousingOrderService whWarehousingOrderService;

    @Autowired
    private IShopGoodsService shopGoodsService;

    @Autowired
    private IShopGoodsSeedService shopGoodsSeedService;

    @Autowired
    private IWhStorageService whStorageService;

    @Autowired
    private IDeptWarehouseService deptWarehouseService;

    /**
     * 查询商品入库单子
     * 
     * @param id 商品入库单子ID
     * @return 商品入库单子
     */
    @Override
    public WhWarehousingOrderSeed selectWhWarehousingOrderSeedById(Long id)
    {
        return whWarehousingOrderSeedMapper.selectWhWarehousingOrderSeedById(id);
    }

    /**
     * 查询商品入库单子列表
     * 
     * @param whWarehousingOrderSeed 商品入库单子
     * @return 商品入库单子
     */
    @Override
    public List<WhWarehousingOrderSeed> selectWhWarehousingOrderSeedList(WhWarehousingOrderSeed whWarehousingOrderSeed)
    {
        return whWarehousingOrderSeedMapper.selectWhWarehousingOrderSeedList(whWarehousingOrderSeed);
    }

    /**
     * 新增商品入库单子
     * 
     * @param whWarehousingOrderSeed 商品入库单子
     * @return 结果
     */
    @Override
    @Transactional
    public int insertWhWarehousingOrderSeed(WhWarehousingOrderSeed whWarehousingOrderSeed)
    {
        int result = 0;
        whWarehousingOrderSeed.setCreateTime(DateUtils.getNowDate());
        //统计商品价格各项值
        WhWarehousingOrder whWarehousingOrder =  whWarehousingOrderService.selectWhWarehousingOrderById(whWarehousingOrderSeed.getWarehousingOrderId());
        whWarehousingOrderSeed = calculate(whWarehousingOrder,whWarehousingOrderSeed,whWarehousingOrder.getRate());
        result = whWarehousingOrderSeedMapper.insertWhWarehousingOrderSeed(whWarehousingOrderSeed);

        //主表总价格受影响需重新计算
        whWarehousingOrder = whWarehousingOrderService.calculate(whWarehousingOrder,whWarehousingOrder.getRate());
        whWarehousingOrder.setUpdateBy(whWarehousingOrderSeed.getCreateBy());
        whWarehousingOrder.setUpdateTime(whWarehousingOrderSeed.getCreateTime());
        result = whWarehousingOrderService.updateWhWarehousingOrder(whWarehousingOrder);

        return result;
    }

    /**
     * 修改商品入库单子
     * 
     * @param whWarehousingOrderSeed 商品入库单子
     * @return 结果
     */
    @Override
    public int updateWhWarehousingOrderSeed(WhWarehousingOrderSeed whWarehousingOrderSeed)
    {
        whWarehousingOrderSeed.setUpdateTime(DateUtils.getNowDate());
        return whWarehousingOrderSeedMapper.updateWhWarehousingOrderSeed(whWarehousingOrderSeed);
    }

    @Override
    @Transactional
    public int updateWhWarehousingOrderSeedRecalculate(WhWarehousingOrderSeed whWarehousingOrderSeed) {
        int result = 0;
        whWarehousingOrderSeed.setUpdateTime(DateUtils.getNowDate());
        //统计商品价格各项值
        WhWarehousingOrder whWarehousingOrder =  whWarehousingOrderService.selectWhWarehousingOrderById(selectWhWarehousingOrderSeedById(whWarehousingOrderSeed.getId()).getWarehousingOrderId());
        whWarehousingOrderSeed = calculate(whWarehousingOrder,whWarehousingOrderSeed,whWarehousingOrder.getRate());
        result = whWarehousingOrderSeedMapper.updateWhWarehousingOrderSeed(whWarehousingOrderSeed);

        //主表总价格受影响需重新计算
        whWarehousingOrder = whWarehousingOrderService.calculate(whWarehousingOrder,whWarehousingOrder.getRate());
        whWarehousingOrder.setUpdateBy(whWarehousingOrderSeed.getUpdateBy());
        result = whWarehousingOrderService.updateWhWarehousingOrder(whWarehousingOrder);

        return result;
    }

    /**
     * 删除商品入库单子对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteWhWarehousingOrderSeedByIds(String ids)
    {
        int result = 0;
        Long[] whWarehousingOrderSeedids = Convert.toLongArray(ids);

        Long warehousingOrderId =0L;
        for (Long whWarehousingOrderSeedid : whWarehousingOrderSeedids ) {
            warehousingOrderId = selectWhWarehousingOrderSeedById(whWarehousingOrderSeedid).getWarehousingOrderId();
            WhWarehousingOrderSeed whWarehousingOrderSeed = new WhWarehousingOrderSeed();
            whWarehousingOrderSeed.setId(whWarehousingOrderSeedid);
            whWarehousingOrderSeed.setDelFlag(Status.DELETED.getCode());
            whWarehousingOrderSeed.setUpdateBy(ShiroUtils.getLoginName());
            result = updateWhWarehousingOrderSeed(whWarehousingOrderSeed);
        }

        //主表总价格受影响需重新计算
        WhWarehousingOrder whWarehousingOrder =  whWarehousingOrderService.selectWhWarehousingOrderById(warehousingOrderId);
        whWarehousingOrder = whWarehousingOrderService.calculate(whWarehousingOrder,whWarehousingOrder.getRate());
        whWarehousingOrder.setUpdateBy(ShiroUtils.getLoginName());
        result = whWarehousingOrderService.updateWhWarehousingOrder(whWarehousingOrder);
        return result;
    }

    /**
     * 删除商品入库单子信息
     * 
     * @param id 商品入库单子ID
     * @return 结果
     */
    @Override
    public int deleteWhWarehousingOrderSeedById(Long id)
    {
        return whWarehousingOrderSeedMapper.deleteWhWarehousingOrderSeedById(id);
    }

    @Override
    public BigDecimal getSumAmount(Long warehousingOrderId) {
        return whWarehousingOrderSeedMapper.getSumAmount(warehousingOrderId);
    }

    @Override
    public int getOrderSeedShopCount(Long warehousingOrderId) {
        return whWarehousingOrderSeedMapper.getOrderSeedShopCount(warehousingOrderId);
    }

    @Override
    @Transactional
    public int updateCompleteSave(WarehousingOrderSeedModel warehousingOrderSeedModel) {
        int result = 0;
        List<ShopGoodsSeedMode> shopGoodsSeedList = new ArrayList<>();;//商品库存信息<商品ID,商品库存数量>
        List<Long> shopGoodsIds =  new ArrayList<>();//List商品信息

        //步骤1:计算是否有或多或少的数量,存储数据库中，并改变订单状态
        List<WhWarehousingOrderSeed> whWarehousingOrderSeedList = warehousingOrderSeedModel.getWarehousingOrderSeed();
        for (WhWarehousingOrderSeed warehousingOrderSeed :whWarehousingOrderSeedList) {
            if(warehousingOrderSeed.getWarehousingNumber().intValue() == 0){
                throw new BusinessException("实际数量不能为0，入库操作失败");
            }
           int number = warehousingOrderSeed.getPlanNumber().intValue() - warehousingOrderSeed.getWarehousingNumber().intValue() ;/** 计划数量-实际数量 */
           if(number < 0){
               warehousingOrderSeed.setMoreNumber(Long.valueOf(Math.abs(number))); /** 多到货数量 */
           }else if(number > 0){
               warehousingOrderSeed.setShortageNumber(Long.valueOf(number));/** 到货短少数量 */
           }
            warehousingOrderSeed.setUpdateBy(warehousingOrderSeedModel.getUpdateBy());
            result = updateWhWarehousingOrderSeed(warehousingOrderSeed);

            ShopGoodsSeedMode shopGoodsSeedMode = new ShopGoodsSeedMode();
            shopGoodsSeedMode.setGoodsId(warehousingOrderSeed.getShopGoodsId());
            shopGoodsSeedMode.setStockNumber(warehousingOrderSeed.getWarehousingNumber());
            shopGoodsSeedList.add(shopGoodsSeedMode);
            shopGoodsIds.add(warehousingOrderSeed.getShopGoodsId());//存储List对象中
        }
        WhWarehousingOrder whWarehousingOrder = new WhWarehousingOrder();
        whWarehousingOrder.setId(warehousingOrderSeedModel.getWarehousingOrderId());
        whWarehousingOrder.setStatus(WarehousingOrderStatus.HASBEENSORTING.getCode());
        whWarehousingOrder.setUpdateBy(warehousingOrderSeedModel.getUpdateBy());
        result = whWarehousingOrderService.updateWhWarehousingOrder(whWarehousingOrder);

        //步骤2 商品数量入库商品库存信息表
        result = shopGoodsSeedService.updateShopGoodsSeedStockNumber(shopGoodsSeedList);

        //步骤3 根据商品ID查询库位，在把空库位标识 改为N
        List<Long> storageIds = shopGoodsService.selectShopGoodsStorageIds(shopGoodsIds);
        result =  whStorageService.updateWhStorageBatch(UserConstants.NO,storageIds);
        return result;
    }

    /**
     * 计算统计子表各项价格
     * @param whWarehousingOrderSeed 商品入库单子
     * @param rate whWarehousingOrder入库主表供应商税率
     * @return whWarehousingOrderSeed 商品入库单子
     */
    public  WhWarehousingOrderSeed calculate(WhWarehousingOrder whWarehousingOrder, WhWarehousingOrderSeed whWarehousingOrderSeed, BigDecimal rate){
        BigDecimal price=whWarehousingOrderSeed.getTaxUnitPrice();
        if(WhWarehousingOrderType.TRANSFER.getCode().equals(whWarehousingOrder.getOrderType())){//如果是请拔单则根据单位关系选择价格
            DeptWarehouse deptWarehouse = deptWarehouseService.selectDeptWarehouseByWarehouseId(whWarehousingOrder.getWarehouseId(),whWarehousingOrder.getDeptId());
            ShopGoods shopGoods = shopGoodsService.selectShopGoodsById(whWarehousingOrderSeed.getShopGoodsId());
            if(CostType.SELLINGPRICE.getCode().equals(deptWarehouse.getCostType())){
                price=shopGoods.getSellingPrice();
            }else if(CostType.WHOLESALEPRICE.getCode().equals(deptWarehouse.getCostType())){
                price=shopGoods.getWholesalePrice();
            }else if(CostType.COSTPRICE.getCode().equals(deptWarehouse.getCostType())){
                price=shopGoods.getCostPrice();
            }else if(CostType.RETAILPRICE.getCode().equals(deptWarehouse.getCostType())){
                price=shopGoods.getRetailPrice();
            }
            whWarehousingOrderSeed.setTaxUnitPrice(price);
        }
        whWarehousingOrderSeed.setRate(rate);//税率
        whWarehousingOrderSeed.setAmount(ArithUtils.safeSubtracts(ArithUtils.safeMultiplys(new BigDecimal(whWarehousingOrderSeed.getPlanNumber().toString()),price),whWarehousingOrderSeed.getDiscountPrice()));//含税金额=(数量*含税单价)-折让
        whWarehousingOrderSeed.setUnitPrice(ArithUtils.safeMultiplys(price,whWarehousingOrderSeed.getRate()));//未税单价=含税单价*税率
        whWarehousingOrderSeed.setTax(ArithUtils.safeMultiplys(whWarehousingOrderSeed.getAmount(),whWarehousingOrderSeed.getRate()));//合计税额=含税金额*税率
        whWarehousingOrderSeed.setUntaxedAmount( ArithUtils.safeSubtracts(whWarehousingOrderSeed.getAmount(),whWarehousingOrderSeed.getTax()));//未税金额= 含税金额-税额
        return whWarehousingOrderSeed;

    }

    @Override
    public int insertWhWarehousingOrderSeedBatch(List<WhWarehousingOrderSeed> whWarehousingOrderSeedList) {
        return whWarehousingOrderSeedMapper.insertWhWarehousingOrderSeedBatch(whWarehousingOrderSeedList);
    }
}
