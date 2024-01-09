package com.ruoyi.wh.mapper;

import java.util.List;
import com.ruoyi.wh.domain.WhReservoir;
import com.ruoyi.wh.vo.WhReservoirVo;

/**
 * 库区设置Mapper接口
 * 
 * @author miki
 * @date 2021-05-21
 */
public interface WhReservoirMapper 
{
    /**
     * 查询库区设置
     * 
     * @param id 库区设置ID
     * @return 库区设置
     */
    public WhReservoir selectWhReservoirById(Long id);

    /**
     * 查询库区设置列表
     * 
     * @param whReservoir 库区设置
     * @return 库区设置集合
     */
    public List<WhReservoir> selectWhReservoirList(WhReservoir whReservoir);

    /**
     * 查询库区设置列表VO
     *
     * @param whReservoir 库区设置
     * @return 库区设置集合
     */
    public List<WhReservoirVo> selectWhReservoirVoList(WhReservoir whReservoir);

    /**
     * 新增库区设置
     * 
     * @param whReservoir 库区设置
     * @return 结果
     */
    public int insertWhReservoir(WhReservoir whReservoir);

    /**
     * 修改库区设置
     * 
     * @param whReservoir 库区设置
     * @return 结果
     */
    public int updateWhReservoir(WhReservoir whReservoir);

    /**
     * 删除库区设置
     * 
     * @param id 库区设置ID
     * @return 结果
     */
    public int deleteWhReservoirById(Long id);

    /**
     * 批量删除库区设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWhReservoirByIds(String[] ids);

    /**
     * 校验库区编码是否唯一
     *
     * @param reservoirCode 库区编码
     * @return 结果
     */
    public int checkReservoirCodeUnique(String reservoirCode);
}
