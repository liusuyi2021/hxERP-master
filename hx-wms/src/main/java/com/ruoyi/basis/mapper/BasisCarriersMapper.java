package com.ruoyi.basis.mapper;

import java.util.List;
import com.ruoyi.basis.domain.BasisCarriers;

/**
 * 承运商管理Mapper接口
 * 
 * @author miki
 * @date 2021-05-20
 */
public interface BasisCarriersMapper 
{
    /**
     * 查询承运商管理
     * 
     * @param id 承运商管理ID
     * @return 承运商管理
     */
    public BasisCarriers selectBasisCarriersById(Long id);

    /**
     * 查询承运商管理列表
     * 
     * @param basisCarriers 承运商管理
     * @return 承运商管理集合
     */
    public List<BasisCarriers> selectBasisCarriersList(BasisCarriers basisCarriers);

    /**
     * 新增承运商管理
     * 
     * @param basisCarriers 承运商管理
     * @return 结果
     */
    public int insertBasisCarriers(BasisCarriers basisCarriers);

    /**
     * 修改承运商管理
     * 
     * @param basisCarriers 承运商管理
     * @return 结果
     */
    public int updateBasisCarriers(BasisCarriers basisCarriers);

    /**
     * 删除承运商管理
     * 
     * @param id 承运商管理ID
     * @return 结果
     */
    public int deleteBasisCarriersById(Long id);

    /**
     * 批量删除承运商管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasisCarriersByIds(String[] ids);

    /**
     * 校验承运商编码是否唯一
     *
     * @param carriersCode 承运商编码
     * @return 结果
     */
    public int checkCarriersCodeUnique(String carriersCode);
}
