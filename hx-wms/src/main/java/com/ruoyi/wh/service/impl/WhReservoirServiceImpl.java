package com.ruoyi.wh.service.impl;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.Status;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.wh.domain.WhWarehouse;
import com.ruoyi.wh.vo.WhReservoirVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wh.mapper.WhReservoirMapper;
import com.ruoyi.wh.domain.WhReservoir;
import com.ruoyi.wh.service.IWhReservoirService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 库区设置Service业务层处理
 * 
 * @author miki
 * @date 2021-05-21
 */
@Service
public class WhReservoirServiceImpl implements IWhReservoirService 
{
    @Autowired
    private WhReservoirMapper whReservoirMapper;

    /**
     * 查询库区设置
     * 
     * @param id 库区设置ID
     * @return 库区设置
     */
    @Override
    public WhReservoir selectWhReservoirById(Long id)
    {
        return whReservoirMapper.selectWhReservoirById(id);
    }

    /**
     * 查询库区设置列表
     * 
     * @param whReservoir 库区设置
     * @return 库区设置
     */
    @Override
    public List<WhReservoir> selectWhReservoirList(WhReservoir whReservoir)
    {
        return whReservoirMapper.selectWhReservoirList(whReservoir);
    }

    @Override
    public List<WhReservoirVo> selectWhReservoirVoList(WhReservoir whReservoir) {
        return whReservoirMapper.selectWhReservoirVoList(whReservoir);
    }

    /**
     * 新增库区设置
     * 
     * @param whReservoir 库区设置
     * @return 结果
     */
    @Override
    public int insertWhReservoir(WhReservoir whReservoir)
    {
        whReservoir.setCreateTime(DateUtils.getNowDate());
        return whReservoirMapper.insertWhReservoir(whReservoir);
    }

    /**
     * 修改库区设置
     * 
     * @param whReservoir 库区设置
     * @return 结果
     */
    @Override
    public int updateWhReservoir(WhReservoir whReservoir)
    {
        whReservoir.setUpdateTime(DateUtils.getNowDate());
        return whReservoirMapper.updateWhReservoir(whReservoir);
    }

    /**
     * 删除库区设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteWhReservoirByIds(String ids)
    {
        int result = 0;
        Long[] warehouseids = Convert.toLongArray(ids);
        for (Long warehouseid : warehouseids ) {
            WhReservoir whReservoir = new WhReservoir();
            whReservoir.setId(warehouseid);
            whReservoir.setDelFlag(Status.DELETED.getCode());
            whReservoir.setUpdateBy(ShiroUtils.getLoginName());
            result = updateWhReservoir(whReservoir);
        }
        return result;
    }

    /**
     * 删除库区设置信息
     * 
     * @param id 库区设置ID
     * @return 结果
     */
    @Override
    public int deleteWhReservoirById(Long id)
    {
        return whReservoirMapper.deleteWhReservoirById(id);
    }

    @Override
    public String checkReservoirCodeUnique(String reservoirCode) {
        int count = whReservoirMapper.checkReservoirCodeUnique(reservoirCode);
        if (count > 0)
        {
            return Constants.NAME_NOT_UNIQUE;
        }
        return Constants.NAME_UNIQUE;
    }
}
