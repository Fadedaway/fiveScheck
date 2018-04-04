package com.magic.jovi.controllers;

import com.magic.jovi.entities.vo.WorkGroupVO;
import com.magic.jovi.services.WorkGroupService;
import com.magic.jovi.utils.ReqResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fanjiawei on 2018/3/31
 * 工作组管理
 */
@RequestMapping(value = "/workGroup")
@RestController
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
    public ReqResult<?> add(@RequestBody WorkGroupVO workGroupVO) {
        try {
            workGroupService.add(workGroupVO);
            return ReqResult.success("新增成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure(e.getMessage());
        }
    }

    /**
     * 编辑工作组
     * @param workGroupVO 前台传入数据
     * @return ReqResult
     */
    @RequestMapping(value = "/edit")
    public ReqResult<?> edit(@RequestBody WorkGroupVO workGroupVO) {
        try {
            workGroupService.edit(workGroupVO);
            return ReqResult.success("编辑成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure(e.getMessage());
        }
    }

    /**
     * 删除工作组，支持批量删除
     * @param ids 需要删除的工作组id 批量删除时以逗号分隔
     * @return ReqResult
     */
    @RequestMapping(value = "/delete/{ids}")
    public ReqResult<?> delete(@PathVariable String ids) {
        try {
            workGroupService.delete(ids);
            return ReqResult.success("删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure(e.getMessage());
        }
    }

    /**
     * 查询所有的工作组
     * @return ReqResult
     */
    @RequestMapping(value = "/findAll")
    public ReqResult<?> findAll() {
        try {
            return ReqResult.success(workGroupService.findAll());
        }catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure(e.getMessage());
        }
    }
}
