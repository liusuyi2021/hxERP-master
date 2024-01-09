package com.ruoyi.wh.service.impl;

import com.ruoyi.basis.domain.BasisCarriers;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.Status;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.wh.domain.WhWarehouse;
import com.ruoyi.wh.mapper.WhWarehouseMapper;
import com.ruoyi.wh.service.IWhWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 仓库设置Service业务层处理
 * 
 * @author miki
 * @date 2021-05-20
 */
@Service
public class WhWarehouseServiceImpl implements IWhWarehouseService 
{
    @Autowired
    private WhWarehouseMapper whWarehouseMapper;

    /**
     * 查询仓库设置
     * 
     * @param id 仓库设置ID
     * @return 仓库设置
     */
    @Override
    public WhWarehouse selectWhWarehouseById(Long id)
    {
        return whWarehouseMapper.selectWhWarehouseById(id);
    }

    /**
     * 查询仓库设置列表
     * 
     * @param whWarehouse 仓库设置
     * @return 仓库设置
     */
    @Override
    public List<WhWarehouse> selectWhWarehouseList(WhWarehouse whWarehouse)
    {
        return whWarehouseMapper.selectWhWarehouseList(whWarehouse);
    }

    /**
     * 新增仓库设置
     * 
     * @param whWarehouse 仓库设置
     * @return 结果
     */
    @Override
    public int insertWhWarehouse(WhWarehouse whWarehouse)
    {
        whWarehouse.setCreateTime(DateUtils.getNowDate());
        return whWarehouseMapper.insertWhWarehouse(whWarehouse);
    }

    /**
     * 修改仓库设置
     * 
     * @param whWarehouse 仓库设置
     * @return 结果
     */
    @Override
    public int updateWhWarehouse(WhWarehouse whWarehouse)
    {
        whWarehouse.setUpdateTime(DateUtils.getNowDate());
        return whWarehouseMapper.updateWhWarehouse(whWarehouse);
    }

    /**
     * 删除仓库设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteWhWarehouseByIds(String ids)
    {
        int result = 0;
        Long[] warehouseids = Convert.toLongArray(ids);
        for (Long warehouseid : warehouseids ) {
            WhWarehouse whWarehouse = new WhWarehouse();
            whWarehouse.setId(warehouseid);
            whWarehouse.setDelFlag(Status.DELETED.getCode());
            whWarehouse.setUpdateBy(ShiroUtils.getLoginName());
            result =updateWhWarehouse(whWarehouse);
        }
        return result;
    }

    /**
     * 删除仓库设置信息
     * 
     * @param id 仓库设置ID
     * @return 结果
     */
    @Override
    public int deleteWhWarehouseById(Long id)
    {
        return whWarehouseMapper.deleteWhWarehouseById(id);
    }

    @Override
    public String checkWarehouseCodeUnique(String warehouseCode) {
        int count = whWarehouseMapper.checkWarehouseCodeUnique(warehouseCode);
        if (count > 0)
        {
            return Constants.NAME_NOT_UNIQUE;
        }
        return Constants.NAME_UNIQUE;
    }
}
