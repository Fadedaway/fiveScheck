package com.magic.jovi.controllers;

import com.magic.jovi.entities.vo.ProblemCollectVO;
import com.magic.jovi.services.ProblemCollectService;
import com.magic.jovi.utils.ReqResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fanjiawei on 2018/4/7
 * 工作站位问题扣分集合
 */
@RestController
@RequestMapping(value = "/problemCollect")
public class ProblemCollectController {

    private ProblemCollectService problemCollectService;

    @Autowired
    public ProblemCollectController(ProblemCollectService problemCollectService) {
        this.problemCollectService = problemCollectService;
    }

    /**
     * 初始化工作站位问题扣分集合数据 通过选择日期以及工作组创建
     * @param createDate 创建日期
     * @param groupId 工作组
     * @return ReqResult
     */
    @RequestMapping(value = "/initData")
    public ReqResult<?> initProblemCollectData(@RequestParam String createDate, @RequestParam Long groupId) {
        try {
            return ReqResult.success(problemCollectService.initData(createDate, groupId));
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure(e.getMessage());
        }
    }

    /**
     * 问题收集
     * @param problemCollectVO 收集问题
     * @return ReqResult
     */
    @RequestMapping(value = "/gatherPoints")
    public ReqResult<?> gatherPoints(@RequestBody ProblemCollectVO problemCollectVO) {
        try {
            //获取前台传入的问题id字符串与分数只和，更新入问题收集表中
            problemCollectService.gatherPoints(problemCollectVO);
            return ReqResult.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReqResult.failure(e.getMessage());
        }
    }
}
