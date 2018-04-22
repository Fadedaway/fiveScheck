package com.magic.jovi.services;

import com.magic.jovi.entities.WorkProblem;
import com.magic.jovi.entities.vo.WorkProblemVO;
import org.springframework.data.domain.Page;

import java.util.List;

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

    /**
     * 分页查询
     * @param page 当前页码
     * @return Page
     */
    Page<WorkProblem> findAll(int page);

    /**
     * 查询所有的问题选项
     * @return List
     */
    List<WorkProblem> findAllList();

    /**
     * 查询工作问题详情
     * @param id 问题主键
     * @return WorkProblem
     */
    WorkProblem detail(Long id);
}
