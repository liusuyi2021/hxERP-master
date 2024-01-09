package com.ruoyi.wh.mapper;

import java.util.List;
import com.ruoyi.wh.domain.WhWarehouse;

/**
 * 仓库设置Mapper接口
 * 
 * @author miki
 * @date 2021-05-20
 */
public interface WhWarehouseMapper 
{
    /**
     * 查询仓库设置
     * 
     * @param id 仓库设置ID
     * @return 仓库设置
     */
    public WhWarehouse selectWhWarehouseById(Long id);

    /**
     * 查询仓库设置列表
     * 
     * @param whWarehouse 仓库设置
     * @return 仓库设置集合
     */
    public List<WhWarehouse> selectWhWarehouseList(WhWarehouse whWarehouse);

    /**
     * 新增仓库设置
     * 
     * @param whWarehouse 仓库设置
     * @return 结果
     */
    public int insertWhWarehouse(WhWarehouse whWarehouse);

    /**
     * 修改仓库设置
     * 
     * @param whWarehouse 仓库设置
     * @return 结果
     */
    public int updateWhWarehouse(WhWarehouse whWarehouse);

    /**
     * 删除仓库设置
     * 
     * @param id 仓库设置ID
     * @return 结果
     */
    public int deleteWhWarehouseById(Long id);

    /**
     * 批量删除仓库设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWhWarehouseByIds(String[] ids);

    /**
     * 校验仓库编码是否唯一
     *
     * @param warehouseCode 仓库编码
     * @return 结果
     */
    public int checkWarehouseCodeUnique(String warehouseCode);
}
