package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysUserDept;

/**
 * 用户与公司关联Service接口
 * 
 * @author miki
 * @date 2021-05-19
 */
public interface ISysUserDeptService 
{
    /**
     * 查询用户与公司关联
     * 
     * @param id 用户与公司关联ID
     * @return 用户与公司关联
     */
    public SysUserDept selectSysUserDeptById(Long id);

    /**
     * 查询用户与公司关联列表
     * 
     * @param sysUserDept 用户与公司关联
     * @return 用户与公司关联集合
     */
    public List<SysUserDept> selectSysUserDeptList(SysUserDept sysUserDept);

    /**
     * 新增用户与公司关联
     * 
     * @param sysUserDept 用户与公司关联
     * @return 结果
     */
    public int insertSysUserDept(SysUserDept sysUserDept);

    /**
     * 修改用户与公司关联
     * 
     * @param sysUserDept 用户与公司关联
     * @return 结果
     */
    public int updateSysUserDept(SysUserDept sysUserDept);

    /**
     * 批量删除用户与公司关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserDeptByIds(String ids);

    /**
     * 删除用户与公司关联信息
     * 
     * @param id 用户与公司关联ID
     * @return 结果
     */
    public int deleteSysUserDeptById(Long id);
}
