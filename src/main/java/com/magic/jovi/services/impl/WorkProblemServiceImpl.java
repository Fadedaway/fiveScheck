package com.magic.jovi.services.impl;

import com.magic.jovi.entities.WorkProblem;
import com.magic.jovi.entities.vo.WorkProblemVO;
import com.magic.jovi.repositories.WorkProblemRepo;
import com.magic.jovi.services.WorkProblemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by fanjiawei on 2018/4/5
 */
@Service
@Transactional
public class WorkProblemServiceImpl implements WorkProblemService {

    private WorkProblemRepo workProblemRepo;

    @Autowired
    public WorkProblemServiceImpl(WorkProblemRepo workProblemRepo) {
        this.workProblemRepo = workProblemRepo;
    }

    @Override
    public void add(WorkProblemVO workProblemVO) {
        WorkProblem workProblem = new WorkProblem();
        BeanUtils.copyProperties(workProblemVO, workProblem);

        workProblemRepo.save(workProblem);
    }

    @Override
    public void edit(WorkProblemVO workProblemVO) {
        if (Objects.isNull(workProblemVO))
            throw new RuntimeException("数据不存在");
        if (Objects.isNull(workProblemVO.getId()))
            throw new RuntimeException("主键为空");

        WorkProblem workProblem = workProblemRepo.findOneById(workProblemVO.getId());

        if (Objects.nonNull(workProblem)) {
            if (StringUtils.isNotBlank(workProblemVO.getName()))
                workProblem.setName(workProblemVO.getName());
            if (StringUtils.isNotBlank(workProblemVO.getDetail()))
                workProblem.setDetail(workProblemVO.getDetail());
            if (workProblemVO.getPoint() != 0)
                workProblem.setPoint(workProblemVO.getPoint());

            workProblemRepo.save(workProblem);
        } else {
            throw new RuntimeException("工作问题表数据不存在");
        }
    }

    @Override
    public void delete(String ids) {
        //TODO 删除逻辑
    }
}
