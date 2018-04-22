package com.magic.jovi.services;

import com.magic.jovi.entities.WorkPosition;
import com.magic.jovi.entities.vo.PageVO;
import com.magic.jovi.entities.vo.WorkPositionVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by fanjiawei on 2018/4/3
 */
public interface WorkPositionService {

    /**
     * 新增工作站位
     * @param workPositionVO 前台传入数据
     */
    void add(WorkPositionVO workPositionVO);

    /**
     * 更新工作站位
     * @param workPositionVO 前台传入数据
     */
    void edit(WorkPositionVO workPositionVO);

    /**
     * 删除工作站位
     * @param ids 以逗号分隔的id
     */
    void delete(String ids);

    /**
     * 查询所有工作站位
     * @param pageVO 分页数据
     * @return Page
     */
    Page<WorkPosition> findAll(PageVO pageVO);

    /**
     * 查询所有的工作站位
     * @return List
     */
    List<WorkPosition> findAllList();

    /**
     * 查询工作站位
     * @param id 工作站位主键
     * @return WorkPosition
     */
    WorkPosition detail(Long id);
}
