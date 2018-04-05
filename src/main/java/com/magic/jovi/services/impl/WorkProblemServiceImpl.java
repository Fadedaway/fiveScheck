package com.magic.jovi.services.impl;

import com.magic.jovi.entities.WorkProblem;
import com.magic.jovi.entities.vo.WorkProblemVO;
import com.magic.jovi.repositories.WorkProblemRepo;
import com.magic.jovi.services.WorkProblemService;
import com.magic.jovi.specification.SimplePageBuilder;
import com.magic.jovi.specification.SimpleSortBuilder;
import com.magic.jovi.specification.SimpleSpecificationBuilder;
import com.magic.jovi.utils.DeleteStatus;
import com.magic.jovi.utils.OperateSymbol;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
        if (StringUtils.isBlank(ids))
            throw new RuntimeException("传入数据为空");
        String[] idArray = ids.split(",");
        //批量删除
        Arrays.stream(idArray).forEach(id -> {
            if (StringUtils.isNotBlank(id)) {
                WorkProblem workProblem = workProblemRepo.findOneById(Long.valueOf(id));

                if (Objects.nonNull(workProblem))
                    workProblemRepo.logicalDelete(workProblem.getId());
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<WorkProblem> findAll(int page) {
        return workProblemRepo.findAll(new SimpleSpecificationBuilder<WorkProblem>("isDeleted",
                        OperateSymbol.E.getSymbol(), DeleteStatus.enable.ordinal()).generateSpecification(),
                SimplePageBuilder.generate(page, SimpleSortBuilder.generateSort("createTime_d")));
    }
}
