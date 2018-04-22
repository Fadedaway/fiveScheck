package com.magic.jovi.controllers;

import com.magic.jovi.entities.vo.PageVO;
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
    public ReqResult<?> add(WorkGroupVO workGroupVO) {
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
    public ReqResult<?> edit(WorkGroupVO workGroupVO) {
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
     * 查询工作组详情
     * @param id 工作组主键
     * @return ReqResult
     */
    @RequestMapping(value = "/detail/{id}")
    public ReqResult<?> detail(@PathVariable Long id) {
        try {
            return ReqResult.success(workGroupService.detail(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure("查询小组详情失败：" + e.getMessage());
        }
    }

    /**
     * 查询所有的工作组
     * @param page 当前页码
     * @return ReqResult
     */
    @RequestMapping(value = "/findAll")
    public ReqResult<?> findAll(@RequestParam int page) {
        try {
            PageVO pageVO = new PageVO(page);
            return ReqResult.success(workGroupService.findAll(pageVO), pageVO);
        }catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure(e.getMessage());
        }
    }

    /**
     * 所有未删除工作组
     * @return ReqResult
     */
    @RequestMapping(value = "/findAllList", method = RequestMethod.GET)
    public ReqResult<?> findAllList() {
        return ReqResult.success(workGroupService.findAllList());
    }
}
