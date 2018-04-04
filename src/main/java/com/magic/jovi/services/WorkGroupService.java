package com.magic.jovi.services;

import com.magic.jovi.entities.WorkGroup;
import com.magic.jovi.entities.vo.WorkGroupVO;

import java.util.List;

/**
 * Created by fanjiawei on 2018/3/31
 */
public interface WorkGroupService {

    /**
     * 新增工作组
     * @param workGroupVO 前台传入参数
     */
    void add(WorkGroupVO workGroupVO);

    /**
     * 编辑工作组
     * @param workGroupVO 前台传入参数
     */
    void edit(WorkGroupVO workGroupVO);

    /**
     * 删除工作组，支持批量删除
     * @param ids 需要删除的工作组主键id，以逗号分隔
     */
    void delete(String ids);

    /**
     * 查处所有未删除的数据
     * @return List
     */
    List<WorkGroup> findAll();
}
