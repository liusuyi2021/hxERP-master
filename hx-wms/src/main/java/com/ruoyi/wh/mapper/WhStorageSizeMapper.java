package com.ruoyi.wh.mapper;

import java.util.List;
import com.ruoyi.wh.domain.WhStorageSize;

/**
 * 库位尺寸Mapper接口
 * 
 * @author miki
 * @date 2021-05-23
 */
public interface WhStorageSizeMapper 
{
    /**
     * 查询库位尺寸
     * 
     * @param id 库位尺寸ID
     * @return 库位尺寸
     */
    public WhStorageSize selectWhStorageSizeById(Long id);

    /**
     * 查询库位尺寸列表
     * 
     * @param whStorageSize 库位尺寸
     * @return 库位尺寸集合
     */
    public List<WhStorageSize> selectWhStorageSizeList(WhStorageSize whStorageSize);

    /**
     * 新增库位尺寸
     * 
     * @param whStorageSize 库位尺寸
     * @return 结果
     */
    public int insertWhStorageSize(WhStorageSize whStorageSize);

    /**
     * 修改库位尺寸
     * 
     * @param whStorageSize 库位尺寸
     * @return 结果
     */
    public int updateWhStorageSize(WhStorageSize whStorageSize);

    /**
     * 删除库位尺寸
     * 
     * @param id 库位尺寸ID
     * @return 结果
     */
    public int deleteWhStorageSizeById(Long id);

    /**
     * 批量删除库位尺寸
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWhStorageSizeByIds(String[] ids);
}
