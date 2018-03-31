package com.magic.jovi.controllers;

import com.magic.jovi.entities.vo.WorkGroupVO;
import com.magic.jovi.services.WorkGroupService;
import com.magic.jovi.utils.ReqResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fanjiawei on 2018/3/31
 * 工作组管理
 */
@RequestMapping(value = "/workGroup")
@Controller
public class WorkGroupController {

    private final WorkGroupService workGroupService;

    @Autowired
    public WorkGroupController(WorkGroupService workGroupService) {
        this.workGroupService = workGroupService;
    }

    /**
     * 新增工作组
     * @param workGroupVO 前台传入数据
     * @return ReqResult
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ReqResult<?> add(WorkGroupVO workGroupVO) {
        workGroupService.add(workGroupVO);
        return ReqResult.success("新增成功");
    }

    /**
     * 编辑工作组
     * @param workGroupVO 前台传入数据
     * @return ReqResult
     */
    public ReqResult edit(WorkGroupVO workGroupVO) {
        return null;
    }
}
