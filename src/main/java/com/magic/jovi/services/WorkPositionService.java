package com.magic.jovi.services;

import com.magic.jovi.entities.vo.WorkPositionVO;

/**
 * Created by fanjiawei on 2018/4/3
 */
public interface WorkPositionService {

    /**
     * 新增工作站位
     * @param workPositionVO 前台传入数据
     */
    void add(WorkPositionVO workPositionVO);

}
