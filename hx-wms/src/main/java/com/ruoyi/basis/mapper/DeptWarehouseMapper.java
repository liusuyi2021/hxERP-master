package com.ruoyi.basis.mapper;

import java.util.List;
import com.ruoyi.basis.domain.DeptWarehouse;
import com.ruoyi.basis.vo.DeptWarehouseVo;
import org.apache.ibatis.annotations.Param;

/**
 * 单位(部门)与客户或供应商关联Mapper接口
 * 
 * @author miki
 * @date 2021-06-16
 */
public interface DeptWarehouseMapper 
{
    /**
     * 查询单位(部门)与客户或供应商关联
     * 
     * @param id 单位(部门)与客户或供应商关联ID
     * @return 单位(部门)与客户或供应商关联
     */
    public DeptWarehouse selectDeptWarehouseById(Long id);

    /**
     * 查询单位(部门)与客户或供应商关联列表
     * 
     * @param deptWarehouse 单位(部门)与客户或供应商关联
     * @return 单位(部门)与客户或供应商关联集合
     */
    public List<DeptWarehouse> selectDeptWarehouseList(DeptWarehouse deptWarehouse);

    /**
     * 查询单位(部门)与客户或供应商关联列表VO
     *
     * @param deptWarehouse 单位(部门)与客户或供应商关联
     * @return 单位(部门)与客户或供应商关联集合
     */
    public List<DeptWarehouseVo> selectDeptWarehouseListVo(DeptWarehouse deptWarehouse);

    /**
     * 新增单位(部门)与客户或供应商关联
     * 
     * @param deptWarehouse 单位(部门)与客户或供应商关联
     * @return 结果
     */
    public int insertDeptWarehouse(DeptWarehouse deptWarehouse);

    /**
     * 修改单位(部门)与客户或供应商关联
     * 
     * @param deptWarehouse 单位(部门)与客户或供应商关联
     * @return 结果
     */
    public int updateDeptWarehouse(DeptWarehouse deptWarehouse);

    /**
     * 删除单位(部门)与客户或供应商关联
     * 
     * @param id 单位(部门)与客户或供应商关联ID
     * @return 结果
     */
    public int deleteDeptWarehouseById(Long id);

    /**
     * 批量删除单位(部门)与客户或供应商关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDeptWarehouseByIds(String[] ids);

    /**
     * 查询单位(部门)与客户或供应商关联
     *
     * @param warehouseId 单位关联表主键
     * @param deptId 部门
     * @return 单位(部门)与客户或供应商关联
     */
    public DeptWarehouse selectDeptWarehouseByWarehouseId(@Param("warehouseId")Long warehouseId, @Param("deptId")Long deptId);
}
