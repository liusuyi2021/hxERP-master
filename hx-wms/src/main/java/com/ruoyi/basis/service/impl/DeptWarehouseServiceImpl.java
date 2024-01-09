package com.ruoyi.basis.service.impl;

import com.ruoyi.basis.domain.BasisCarriers;
import com.ruoyi.basis.domain.DeptWarehouse;
import com.ruoyi.basis.mapper.DeptWarehouseMapper;
import com.ruoyi.basis.service.IDeptWarehouseService;
import com.ruoyi.basis.vo.DeptWarehouseVo;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.Status;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单位(部门)与客户或供应商关联Service业务层处理
 * 
 * @author miki
 * @date 2021-06-16
 */
@Service
public class DeptWarehouseServiceImpl implements IDeptWarehouseService 
{
    @Autowired
    private DeptWarehouseMapper deptWarehouseMapper;

    /**
     * 查询单位(部门)与客户或供应商关联
     * 
     * @param id 单位(部门)与客户或供应商关联ID
     * @return 单位(部门)与客户或供应商关联
     */
    @Override
    public DeptWarehouse selectDeptWarehouseById(Long id)
    {
        return deptWarehouseMapper.selectDeptWarehouseById(id);
    }

    /**
     * 查询单位(部门)与客户或供应商关联列表
     * 
     * @param deptWarehouse 单位(部门)与客户或供应商关联
     * @return 单位(部门)与客户或供应商关联
     */
    @Override
    public List<DeptWarehouse> selectDeptWarehouseList(DeptWarehouse deptWarehouse)
    {
        return deptWarehouseMapper.selectDeptWarehouseList(deptWarehouse);
    }

    /**
     * 查询单位(部门)与客户或供应商关联列表Vo
     *
     * @param deptWarehouse 单位(部门)与客户或供应商关联
     * @return 单位(部门)与客户或供应商关联
     */
    @Override
    public List<DeptWarehouseVo> selectDeptWarehouseListVo(DeptWarehouse deptWarehouse)
    {
        return deptWarehouseMapper.selectDeptWarehouseListVo(deptWarehouse);
    }

    /**
     * 新增单位(部门)与客户或供应商关联
     * 
     * @param deptWarehouse 单位(部门)与客户或供应商关联
     * @return 结果
     */
    @Override
    public int insertDeptWarehouse(DeptWarehouse deptWarehouse)
    {
        deptWarehouse.setCreateTime(DateUtils.getNowDate());
        return deptWarehouseMapper.insertDeptWarehouse(deptWarehouse);
    }

    /**
     * 修改单位(部门)与客户或供应商关联
     * 
     * @param deptWarehouse 单位(部门)与客户或供应商关联
     * @return 结果
     */
    @Override
    public int updateDeptWarehouse(DeptWarehouse deptWarehouse)
    {
        deptWarehouse.setUpdateTime(DateUtils.getNowDate());
        return deptWarehouseMapper.updateDeptWarehouse(deptWarehouse);
    }

    /**
     * 删除单位(部门)与客户或供应商关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteDeptWarehouseByIds(String ids)
    {
        int result = 0;
        Long[] deptWarehouseids = Convert.toLongArray(ids);
        for (Long deptWarehouseid : deptWarehouseids ) {
            DeptWarehouse deptWarehouse = new DeptWarehouse();
            deptWarehouse.setId(deptWarehouseid);
            deptWarehouse.setDelFlag(Status.DELETED.getCode());
            deptWarehouse.setUpdateBy(ShiroUtils.getLoginName());
            result =updateDeptWarehouse(deptWarehouse);
        }
        return result;
    }

    /**
     * 删除单位(部门)与客户或供应商关联信息
     * 
     * @param id 单位(部门)与客户或供应商关联ID
     * @return 结果
     */
    @Override
    public int deleteDeptWarehouseById(Long id)
    {
        return deptWarehouseMapper.deleteDeptWarehouseById(id);
    }

    @Override
    public DeptWarehouse selectDeptWarehouseByWarehouseId(Long warehouseId, Long deptId) {
        return deptWarehouseMapper.selectDeptWarehouseByWarehouseId(warehouseId,deptId);
    }
}
