package com.ruoyi.wh.service;

import java.util.List;
import com.ruoyi.wh.domain.WhStorage;
import com.ruoyi.wh.vo.WhStorageVo;

/**
 * 库位(储位)设置Service接口
 * 
 * @author miki
 * @date 2021-05-23
 */
public interface IWhStorageService 
{
    /**
     * 查询库位(储位)设置
     * 
     * @param id 库位(储位)设置ID
     * @return 库位(储位)设置
     */
    public WhStorage selectWhStorageById(Long id);

    /**
     * 查询库位(储位)设置列表
     * 
     * @param whStorage 库位(储位)设置
     * @return 库位(储位)设置集合
     */
    public List<WhStorage> selectWhStorageList(WhStorage whStorage);

    /**
     * 新增库位(储位)设置
     * 
     * @param whStorage 库位(储位)设置
     * @return 结果
     */
    public int insertWhStorage(WhStorage whStorage);

    /**
     * 修改库位(储位)设置
     * 
     * @param whStorage 库位(储位)设置
     * @return 结果
     */
    public int updateWhStorage(WhStorage whStorage);

    /**
     * 批量删除库位(储位)设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWhStorageByIds(String ids);

    /**
     * 删除库位(储位)设置信息
     * 
     * @param id 库位(储位)设置ID
     * @return 结果
     */
    public int deleteWhStorageById(Long id);

    /**
     * 校验库位(储位)设置编码是否唯一
     *
     * @param storageCode 库位(储位)设置编码
     * @return 结果
     */
    public String checkStorageCodeUnique(String storageCode);

    /**
     * 查询库位(储位)设置列表VO
     *
     * @param whStorage 库位(储位)设置
     * @return 库位(储位)设置集合
     */
    public List<WhStorageVo> selectWhStorageVoList(WhStorage whStorage);

    /**
     * 批量修改空库位标识
     *
     * @param isEmpty 空库位标识(0: true 是 1:false 否 )
     * @param ids 库位(储位)设置主键
     * @return 结果
     */
    public int updateWhStorageBatch(String isEmpty, List<Long> ids);

    /**
     * 任务定时器，监测空库位标识
     *
     * @return 结果
     */
    public int updateisEmptyTask();
}
