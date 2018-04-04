package com.magic.jovi.services.impl;

import com.magic.jovi.entities.WorkPosition;
import com.magic.jovi.entities.vo.PageVO;
import com.magic.jovi.entities.vo.WorkPositionVO;
import com.magic.jovi.repositories.WorkPositionRepo;
import com.magic.jovi.services.WorkPositionService;
import com.magic.jovi.specification.SimplePageBuilder;
import com.magic.jovi.specification.SimpleSortBuilder;
import com.magic.jovi.specification.SimpleSpecification;
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
 * Created by fanjiawei on 2018/4/3
 */
@Service
@Transactional
public class WorkPositionServiceImpl implements WorkPositionService {

    private WorkPositionRepo workPositionRepo;

    @Autowired
    public WorkPositionServiceImpl(WorkPositionRepo workPositionRepo) {
        this.workPositionRepo = workPositionRepo;
    }

    @Override
    public void add(WorkPositionVO workPositionVO) {
        WorkPosition workPosition = new WorkPosition();
        BeanUtils.copyProperties(workPositionVO, workPosition);

        workPositionRepo.save(workPosition);
    }

    @Override
    public void edit(WorkPositionVO workPositionVO) {
        if (null == workPositionVO)
            throw new RuntimeException("数据不存在");
        if (StringUtils.isNotBlank(workPositionVO.getId()))
            throw new RuntimeException("主键为空");

        WorkPosition workPosition = workPositionRepo.findOne(workPositionVO.getId());

        if (null != workPosition) {

            if (StringUtils.isNotBlank(workPositionVO.getName()))
                workPosition.setName(workPositionVO.getName());

            workPositionRepo.save(workPosition);

        }else {
            throw new RuntimeException("工作站位表数据不存在");
        }
    }

    @Override
    public void delete(String ids) {
        if (StringUtils.isBlank(ids)) {
            throw new RuntimeException("传入数据为空");
        }

        String[] idArray = ids.split(",");
        //删除逻辑 批量删除
        Arrays.stream(idArray).forEach(id -> {
            if (StringUtils.isNotBlank(id)) {
                WorkPosition workPosition = workPositionRepo.findOneById(id);

                if (Objects.nonNull(workPosition)) {
                    workPositionRepo.logicalDelete(id);
                }
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<WorkPosition> findAll(PageVO pageVO) {
        return workPositionRepo.findAll(new SimpleSpecificationBuilder<WorkPosition>("isDeleted",
                OperateSymbol.E.getSymbol(), DeleteStatus.enable.ordinal()).generateSpecification(),
                SimplePageBuilder.generate(pageVO.getPage(), SimpleSortBuilder.generateSort("createTime_d")));
    }
}
