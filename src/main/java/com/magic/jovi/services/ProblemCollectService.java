package com.magic.jovi.services;

import com.magic.jovi.entities.ProblemCollect;
import com.magic.jovi.entities.vo.ProblemCollectVO;
import com.magic.jovi.entities.vo.QueryVO;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.List;

/**
 * Created by fanjiawei on 2018/4/10
 */
public interface ProblemCollectService {

    /**
     * 初始化工作站位问题扣分集合数据 通过选择日期以及工作组创建
     * @param createDate 创建日期
     * @param groupId 工作组
     * @return List
     */
    List<ProblemCollectVO> initData(String createDate, Long groupId);

    /**
     * 获取前台传入的问题id字符串与分数只和，更新入问题收集表中
     * @param problemCollectVO 问题集合
     */
    void gatherPoints(ProblemCollectVO problemCollectVO);

    /**
     * 分页查询问题集合
     * @param queryVO 查询条件
     * @return Page
     */
    Page<ProblemCollect> queryInfo(QueryVO queryVO) throws ParseException;
}
