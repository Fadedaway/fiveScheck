package com.magic.jovi.services;

import com.magic.jovi.entities.vo.WorkProblemVO;

/**
 * Created by fanjiawei on 2018/4/5
 */
public interface WorkProblemService {

    /**
     * 新增工作问题
     * @param workProblemVO 工作问题数据
     */
    void add(WorkProblemVO workProblemVO);

    /**
     * 编辑工作问题
     * @param workProblemVO 工作问题数据
     */
    void edit(WorkProblemVO workProblemVO);

    /**
     * 删除工作问题
     * @param ids 问题主键，逗号分隔
     */
    void delete(String ids);
}
