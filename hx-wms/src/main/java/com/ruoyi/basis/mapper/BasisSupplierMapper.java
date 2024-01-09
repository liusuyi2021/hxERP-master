package com.ruoyi.basis.mapper;

import java.util.List;
import com.ruoyi.basis.domain.BasisSupplier;

/**
 * 供应商管理Mapper接口
 * 
 * @author miki
 * @date 2021-05-20
 */
public interface BasisSupplierMapper 
{
    /**
     * 查询供应商管理
     * 
     * @param id 供应商管理ID
     * @return 供应商管理
     */
    public BasisSupplier selectBasisSupplierById(Long id);

    /**
     * 查询供应商管理列表
     * 
     * @param basisSupplier 供应商管理
     * @return 供应商管理集合
     */
    public List<BasisSupplier> selectBasisSupplierList(BasisSupplier basisSupplier);

    /**
     * 新增供应商管理
     * 
     * @param basisSupplier 供应商管理
     * @return 结果
     */
    public int insertBasisSupplier(BasisSupplier basisSupplier);

    /**
     * 修改供应商管理
     * 
     * @param basisSupplier 供应商管理
     * @return 结果
     */
    public int updateBasisSupplier(BasisSupplier basisSupplier);

    /**
     * 删除供应商管理
     * 
     * @param id 供应商管理ID
     * @return 结果
     */
    public int deleteBasisSupplierById(Long id);

    /**
     * 批量删除供应商管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasisSupplierByIds(String[] ids);


    /**
     * 校验供应商编码是否唯一
     *
     * @param supplierCode 供应商编码
     * @return 结果
     */
    public int checkSupplierCodeUnique(String supplierCode);
}
