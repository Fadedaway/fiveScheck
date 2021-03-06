package com.magic.jovi.controllers;

import com.magic.jovi.entities.vo.PageVO;
import com.magic.jovi.entities.vo.WorkPositionVO;
import com.magic.jovi.services.WorkPositionService;
import com.magic.jovi.utils.ReqResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ReqResult<?> add(WorkPositionVO workPositionVO) {
        try {
            workPositionService.add(workPositionVO);
            return ReqResult.success("新增完成");
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure(e.getMessage());
        }
    }

    /**
     * 更新工作站位
     * @param workPositionVO 前台传入数据
     * @return ReqResult
     */
    @RequestMapping(value = "/edit")
    public ReqResult<?> edit(WorkPositionVO workPositionVO) {
        try {
            workPositionService.edit(workPositionVO);
            return ReqResult.success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure(e.getMessage());
        }
    }

    /**
     * 查询工作站位详情
     * @param id 工作站位主键
     * @return ReqResult
     */
    @RequestMapping(value = "/detail/{id}")
    public ReqResult<?> detail(@PathVariable Long id) {
        try {
            return ReqResult.success(workPositionService.detail(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure("查询机器详情失败：" + e.getMessage());
        }
    }

    /**
     * 删除工作站位
     * @param ids 工作站位id，以逗号分割
     * @return ReqResult
     */
    @RequestMapping(value = "/delete/{ids}")
    public ReqResult<?> delete(@PathVariable String ids) {
        try {
            workPositionService.delete(ids);
            return ReqResult.success("删除工作站位成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure(e.getMessage());
        }
    }

    /**
     * 查询所有的工作站位
     * @param groupId 所属小组
     * @param pageNum 当前页
     * @return ReqResult
     */
    @RequestMapping(value = "/findAll")
    public ReqResult<?> findAll(Long groupId, int pageNum) {
        try {
            PageVO pageVO = new PageVO(pageNum);
            return ReqResult.success(workPositionService.findAll(groupId, pageVO), pageVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure("查询失败：" + e.getMessage());
        }
    }

    /**
     * 查询所有的工作站位 下拉框数据
     * @return List
     */
    @RequestMapping(value = "/findAllList")
    public ReqResult<?> findAllList(Long groupId) {
        return ReqResult.success(workPositionService.findAllList(groupId));
    }
}
