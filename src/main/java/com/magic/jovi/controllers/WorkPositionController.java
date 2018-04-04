package com.magic.jovi.controllers;

import com.magic.jovi.entities.vo.WorkPositionVO;
import com.magic.jovi.services.WorkPositionService;
import com.magic.jovi.utils.ReqResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fanjiawei on 2018/4/3
 * 工作站位管理（机器管理）
 */
@RequestMapping(value = "/workPosition")
@RestController
public class WorkPositionController {

    private final WorkPositionService workPositionService;

    @Autowired
    public WorkPositionController(WorkPositionService workPositionService) {
        this.workPositionService = workPositionService;
    }

    /**
     * 新增工作站位
     * @param workPositionVO 前台传入数据
     * @return ReqResult
     */
    @RequestMapping(value = "/add")
    public ReqResult<?> add(@RequestBody WorkPositionVO workPositionVO) {
        try {
            workPositionService.add(workPositionVO);
            return ReqResult.success("新增完成");
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure(e.getMessage());
        }
    }
}
