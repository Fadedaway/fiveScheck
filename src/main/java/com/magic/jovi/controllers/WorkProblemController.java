package com.magic.jovi.controllers;

import com.magic.jovi.entities.vo.WorkProblemVO;
import com.magic.jovi.services.WorkProblemService;
import com.magic.jovi.utils.ReqResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fanjiawei on 2018/4/5
 */
@RestController
@RequestMapping(value = "/workProblem")
public class WorkProblemController {

    private WorkProblemService workProblemService;

    @Autowired
    public WorkProblemController(WorkProblemService workProblemService) {
        this.workProblemService = workProblemService;
    }

    /**
     * 新增工作问题
     * @param workProblemVO 工作问题数据
     * @return ReqResult
     */
    @RequestMapping(value = "/add")
    public ReqResult<?> add(WorkProblemVO workProblemVO) {
        try {
            workProblemService.add(workProblemVO);
            return ReqResult.success("新增工作问题成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure("新增工作问题失败" + e.getMessage());
        }
    }

    /**
     * 编辑工作问题
     * @param workProblemVO 工作问题数据
     * @return ReqResult
     */
    @RequestMapping(value = "/edit")
    public ReqResult<?> edit(WorkProblemVO workProblemVO) {
        try {
            workProblemService.edit(workProblemVO);
            return ReqResult.success("更新工作问题成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure("更新工作问题失败：" + e.getMessage());
        }
    }

    /**
     * 删除工作问题
     * @param ids 问题主键，逗号分隔
     * @return ReqResult
     */
    @RequestMapping(value = "/delete/{ids}")
    public ReqResult<?> delete(@PathVariable String ids) {
        try {
            workProblemService.delete(ids);
            return ReqResult.success("删除工作问题成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure("删除工作问题失败：" + e.getMessage());
        }
    }

    /**
     * 工作问题详情
     * @param id 问题主键
     * @return ReqResult
     */
    @RequestMapping(value = "/detail/{id}")
    public ReqResult<?> detail(@PathVariable String id) {
        try {
            return ReqResult.success(workProblemService.detail(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure("查询问题详情失败：" + e.getMessage());
        }
    }

    /**
     * 分页查询
     * @param page 当前页码
     * @return ReqResult
     */
    @RequestMapping(value = "/findAll")
    public ReqResult<?> findAll(@RequestParam int page) {
        try {
            return ReqResult.success(workProblemService.findAll(page));
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure("查询失败：" + e.getMessage());
        }
    }

    /**
     * 查询所有的问题选项
     * @return List
     */
    @RequestMapping(value = "/findAllList")
    public ReqResult<?> findAllList() {
        return ReqResult.success(workProblemService.findAllList());
    }
}
