package com.ruoyi.basis.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.basis.mapper.BasisWarehouseMapper;
import com.ruoyi.basis.domain.BasisWarehouse;
import com.ruoyi.basis.service.IBasisWarehouseService;
import com.ruoyi.common.core.text.Convert;

/**
 * 单位关联Service业务层处理
 * 
 * @author miki
 * @date 2021-06-11
 */
@Service
public class BasisWarehouseServiceImpl implements IBasisWarehouseService 
{
    @Autowired
    private BasisWarehouseMapper basisWarehouseMapper;

    /**
     * 查询单位关联
     * 
     * @param id 单位关联ID
     * @return 单位关联
     */
    @Override
    public BasisWarehouse selectBasisWarehouseById(Long id)
    {
        return basisWarehouseMapper.selectBasisWarehouseById(id);
    }

    /**
     * 查询单位关联列表
     * 
     * @param basisWarehouse 单位关联
     * @return 单位关联
     */
    @Override
    public List<BasisWarehouse> selectBasisWarehouseList(BasisWarehouse basisWarehouse)
    {
        return basisWarehouseMapper.selectBasisWarehouseList(basisWarehouse);
    }

    /**
     * 新增单位关联
     * 
     * @param basisWarehouse 单位关联
     * @return 结果
     */
    @Override
    public int insertBasisWarehouse(BasisWarehouse basisWarehouse)
    {
        basisWarehouse.setCreateTime(DateUtils.getNowDate());
        return basisWarehouseMapper.insertBasisWarehouse(basisWarehouse);
    }

    /**
     * 修改单位关联
     * 
     * @param basisWarehouse 单位关联
     * @return 结果
     */
    @Override
    public int updateBasisWarehouse(BasisWarehouse basisWarehouse)
    {
        basisWarehouse.setUpdateTime(DateUtils.getNowDate());
        return basisWarehouseMapper.updateBasisWarehouse(basisWarehouse);
    }

    /**
     * 删除单位关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBasisWarehouseByIds(String ids)
    {
        return basisWarehouseMapper.deleteBasisWarehouseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除单位关联信息
     * 
     * @param id 单位关联ID
     * @return 结果
     */
    @Override
    public int deleteBasisWarehouseById(Long id)
    {
        return basisWarehouseMapper.deleteBasisWarehouseById(id);
    }
}
