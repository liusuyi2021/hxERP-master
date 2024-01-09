package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserDeptMapper;
import com.ruoyi.common.core.domain.entity.SysUserDept;
import com.ruoyi.system.service.ISysUserDeptService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户与公司关联Service业务层处理
 * 
 * @author miki
 * @date 2021-05-19
 */
@Service
public class SysUserDeptServiceImpl implements ISysUserDeptService 
{
    @Autowired
    private SysUserDeptMapper sysUserDeptMapper;

    /**
     * 查询用户与公司关联
     * 
     * @param id 用户与公司关联ID
     * @return 用户与公司关联
     */
    @Override
    public SysUserDept selectSysUserDeptById(Long id)
    {
        return sysUserDeptMapper.selectSysUserDeptById(id);
    }

    /**
     * 查询用户与公司关联列表
     * 
     * @param sysUserDept 用户与公司关联
     * @return 用户与公司关联
     */
    @Override
    public List<SysUserDept> selectSysUserDeptList(SysUserDept sysUserDept)
    {
        return sysUserDeptMapper.selectSysUserDeptList(sysUserDept);
    }

    /**
     * 新增用户与公司关联
     * 
     * @param sysUserDept 用户与公司关联
     * @return 结果
     */
    @Override
    public int insertSysUserDept(SysUserDept sysUserDept)
    {
        sysUserDept.setCreateTime(DateUtils.getNowDate());
        return sysUserDeptMapper.insertSysUserDept(sysUserDept);
    }

    /**
     * 修改用户与公司关联
     * 
     * @param sysUserDept 用户与公司关联
     * @return 结果
     */
    @Override
    public int updateSysUserDept(SysUserDept sysUserDept)
    {
        sysUserDept.setUpdateTime(DateUtils.getNowDate());
        return sysUserDeptMapper.updateSysUserDept(sysUserDept);
    }

    /**
     * 删除用户与公司关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysUserDeptByIds(String ids)
    {
        return sysUserDeptMapper.deleteSysUserDeptByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户与公司关联信息
     * 
     * @param id 用户与公司关联ID
     * @return 结果
     */
    @Override
    public int deleteSysUserDeptById(Long id)
    {
        return sysUserDeptMapper.deleteSysUserDeptById(id);
    }
}
