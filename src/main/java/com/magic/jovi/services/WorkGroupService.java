package com.magic.jovi.services;

import com.magic.jovi.entities.vo.WorkGroupVO;

/**
 * Created by fanjiawei on 2018/3/31
 */
public interface WorkGroupService {

    /**
     * 新增工作组
     * @param workGroupVO 前台传入参数
     */
    void add(WorkGroupVO workGroupVO);
}
