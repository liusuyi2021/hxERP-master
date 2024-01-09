package com.ruoyi.wh.service.impl;

import com.ruoyi.basis.domain.BasisSupplier;
import com.ruoyi.basis.service.IBasisSupplierService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.Status;
import com.ruoyi.common.utils.ArithUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.wh.domain.WhStorageSize;
import com.ruoyi.wh.domain.WhWarehousingOrder;
import com.ruoyi.wh.domain.WhWarehousingOrderSeed;
import com.ruoyi.wh.mapper.WhWarehousingOrderMapper;
import com.ruoyi.wh.service.IWhWarehousingOrderSeedService;
import com.ruoyi.wh.service.IWhWarehousingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品入库单主表Service业务层处理
 * 
 * @author miki
 * @date 2021-05-26
 */
@Service
public class WhWarehousingOrderServiceImpl implements IWhWarehousingOrderService 
{
    @Autowired
    private WhWarehousingOrderMapper whWarehousingOrderMapper;

    @Autowired
    private IWhWarehousingOrderSeedService whWarehousingOrderSeedService;

    @Autowired
    private IBasisSupplierService basisSupplierService;/*供应商Service*/

    /**
     * 查询商品入库单主表
     * 
     * @param id 商品入库单主表ID
     * @return 商品入库单主表
     */
    @Override
    public WhWarehousingOrder selectWhWarehousingOrderById(Long id)
    {
        return whWarehousingOrderMapper.selectWhWarehousingOrderById(id);
    }

    /**
     * 查询商品入库单主表列表
     * 
     * @param whWarehousingOrder 商品入库单主表
     * @return 商品入库单主表
     */
    @Override
    public List<WhWarehousingOrder> selectWhWarehousingOrderList(WhWarehousingOrder whWarehousingOrder)
    {
        return whWarehousingOrderMapper.selectWhWarehousingOrderList(whWarehousingOrder);
    }

    /**
     * 新增商品入库单主表
     * 
     * @param whWarehousingOrder 商品入库单主表
     * @return 结果
     */
    @Override
    public int insertWhWarehousingOrder(WhWarehousingOrder whWarehousingOrder)
    {
        whWarehousingOrder.setCreateTime(DateUtils.getNowDate());
        return whWarehousingOrderMapper.insertWhWarehousingOrder(whWarehousingOrder);
    }

    /**
     * 修改商品入库单主表
     * 
     * @param whWarehousingOrder 商品入库单主表
     * @return 结果
     */
    @Override
    public int updateWhWarehousingOrder(WhWarehousingOrder whWarehousingOrder)
    {
        whWarehousingOrder.setUpdateTime(DateUtils.getNowDate());
        return  whWarehousingOrderMapper.updateWhWarehousingOrder(whWarehousingOrder);
    }

    /**
     * 修改商品入库单主表
     *
     * @param whWarehousingOrder 商品入库单主表
     * @return 结果
     */
    @Override
    @Transactional
    public int updateWhWarehousingOrderShop(WhWarehousingOrder whWarehousingOrder)
    {
        int result = 0;
        //TODO 步骤:1.先重新获得存储供应商税率，以防供应商已做更改税率发生变化，2.按最新税率重新计算所有商品价格(备注:数量和含税单价不受税率影响),3.在梳理计算主表价格并且存库
        //步骤1
        whWarehousingOrder.setUpdateTime(DateUtils.getNowDate());
        BasisSupplier basisSupplier = basisSupplierService.selectBasisSupplierById(whWarehousingOrder.getSupplierId());
        whWarehousingOrder.setRate(basisSupplier.getInvoiceTax());

        //步骤2
        WhWarehousingOrderSeed whWarehousingOrderSeed = new WhWarehousingOrderSeed();
        whWarehousingOrderSeed.setWarehousingOrderId(whWarehousingOrder.getId());
        List<WhWarehousingOrderSeed> warehousingOrderSeedList = whWarehousingOrderSeedService.selectWhWarehousingOrderSeedList(whWarehousingOrderSeed);
        for (WhWarehousingOrderSeed warehousingOrderSeed : warehousingOrderSeedList) {
            whWarehousingOrderSeed = whWarehousingOrderSeedService.calculate(whWarehousingOrder,warehousingOrderSeed,whWarehousingOrder.getRate());
            whWarehousingOrderSeed.setUpdateBy(whWarehousingOrder.getUpdateBy());
            result = whWarehousingOrderSeedService.updateWhWarehousingOrderSeed(whWarehousingOrderSeed);
        }

        //步骤3
        whWarehousingOrder = calculate(whWarehousingOrder,whWarehousingOrder.getRate());
        result = whWarehousingOrderMapper.updateWhWarehousingOrder(whWarehousingOrder);
        return result;
    }

    /**
     * 删除商品入库单主表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteWhWarehousingOrderByIds(String ids)
    {
        int result = 0;
        Long[] whWarehousingOrderids = Convert.toLongArray(ids);
        for (Long whWarehousingOrderid : whWarehousingOrderids ) {
            WhWarehousingOrder whWarehousingOrder = new WhWarehousingOrder();
            whWarehousingOrder.setId(whWarehousingOrderid);
            whWarehousingOrder.setDelFlag(Status.DELETED.getCode());
            whWarehousingOrder.setUpdateBy(ShiroUtils.getLoginName());
            result =updateWhWarehousingOrder(whWarehousingOrder);
        }
        return result;
    }

    /**
     * 删除商品入库单主表信息
     * 
     * @param id 商品入库单主表ID
     * @return 结果
     */
    @Override
    public int deleteWhWarehousingOrderById(Long id)
    {
        return whWarehousingOrderMapper.deleteWhWarehousingOrderById(id);
    }

    /**
     * 计算统计入库单主表各项价格
     * @param whWarehousingOrder 商品入库单主表
     * @param rate 入库主表供应商税率
     * @return 结果
     */
    public WhWarehousingOrder calculate(WhWarehousingOrder whWarehousingOrder, BigDecimal rate){
        BigDecimal sumAmount = whWarehousingOrderSeedService.getSumAmount(whWarehousingOrder.getId());//查询入库单所有商品合计金额
        whWarehousingOrder.setDiscountAmount(ArithUtils.safeSubtracts(sumAmount ,ArithUtils.safeMultiplys(sumAmount,whWarehousingOrder.getDiscountRate())));/** 折扣金额=商品总金额-(商品总金额*折扣率) */
        whWarehousingOrder.setTotalAmount(ArithUtils.safeAdds(ArithUtils.safeSubtracts(sumAmount,whWarehousingOrder.getDiscountAmount(),whWarehousingOrder.getDiscountPrice()),whWarehousingOrder.getOtherFee()));  /** 含税金额= 商品合计金额-折扣金额-折让金额+其他费用*/
        whWarehousingOrder.setTax(ArithUtils.safeMultiplys(whWarehousingOrder.getTotalAmount(),rate));/** 税额=含税金额*税率 */
        whWarehousingOrder.setUntaxedAmount(ArithUtils.safeSubtracts(whWarehousingOrder.getTotalAmount(),whWarehousingOrder.getTax())); /** 未税金额= 含税金额-税额 */
        return whWarehousingOrder;
    }

}
