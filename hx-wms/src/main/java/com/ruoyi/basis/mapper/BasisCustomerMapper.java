package com.ruoyi.basis.mapper;

import java.util.List;
import com.ruoyi.basis.domain.BasisCustomer;

/**
 * 客户资料Mapper接口
 * 
 * @author miki
 * @date 2021-05-19
 */
public interface BasisCustomerMapper 
{
    /**
     * 查询客户资料
     * 
     * @param id 客户资料ID
     * @return 客户资料
     */
    public BasisCustomer selectBasisCustomerById(Long id);

    /**
     * 查询客户资料列表
     * 
     * @param basisCustomer 客户资料
     * @return 客户资料集合
     */
    public List<BasisCustomer> selectBasisCustomerList(BasisCustomer basisCustomer);

    /**
     * 新增客户资料
     * 
     * @param basisCustomer 客户资料
     * @return 结果
     */
    public int insertBasisCustomer(BasisCustomer basisCustomer);

    /**
     * 修改客户资料
     * 
     * @param basisCustomer 客户资料
     * @return 结果
     */
    public int updateBasisCustomer(BasisCustomer basisCustomer);

    /**
     * 删除客户资料
     * 
     * @param id 客户资料ID
     * @return 结果
     */
    public int deleteBasisCustomerById(Long id);

    /**
     * 批量删除客户资料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasisCustomerByIds(String[] ids);

    /**
     * 校验客户编码是否唯一
     *
     * @param customerCode 客户编码
     * @return 结果
     */
    public int checkCustomerCodeUnique(String customerCode);
}
