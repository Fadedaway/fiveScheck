package com.magic.jovi.services.impl;

import com.magic.jovi.entities.WorkGroup;
import com.magic.jovi.entities.vo.PageVO;
import com.magic.jovi.entities.vo.WorkGroupVO;
import com.magic.jovi.repositories.WorkGroupRepo;
import com.magic.jovi.services.WorkGroupService;
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
import java.util.List;
import java.util.Objects;

/**
 * Created by fanjiawei on 2018/3/31
 */
@Service
@Transactional
public class WorkGroupServiceImpl implements WorkGroupService {

    private final WorkGroupRepo workGroupRepo;

    @Autowired
    public WorkGroupServiceImpl(WorkGroupRepo workGroupRepo) {
        this.workGroupRepo = workGroupRepo;
    }

    @Override
    public void add(WorkGroupVO workGroupVO) {
        WorkGroup workGroup = new WorkGroup();
        BeanUtils.copyProperties(workGroupVO, workGroup);

        workGroupRepo.save(workGroup);
    }

    @Override
    public void edit(WorkGroupVO workGroupVO) {
        if (Objects.isNull(workGroupVO))
            throw new RuntimeException("数据不存在");
        if (Objects.isNull(workGroupVO.getId()))
            throw new RuntimeException("主键为空");

        WorkGroup workGroup = workGroupRepo.findOneById(workGroupVO.getId());

        if (null != workGroup) {

            if (StringUtils.isNotBlank(workGroupVO.getName()))
                workGroup.setName(workGroupVO.getName());

            workGroupRepo.save(workGroup);

        }else {
            throw new RuntimeException("工作组表数据不存在");
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
                WorkGroup workGroup = workGroupRepo.findOneById(Long.valueOf(id));

                if (Objects.nonNull(workGroup)) {
                    workGroupRepo.logicalDelete(Long.valueOf(id));
                }
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<WorkGroup> findAll(PageVO pageVO) {
        return workGroupRepo.findAll(new SimpleSpecificationBuilder<WorkGroup>("isDeleted",
                OperateSymbol.E.getSymbol(), DeleteStatus.enable.ordinal()).generateSpecification()
        , SimplePageBuilder.generate(pageVO.getPage(), SimpleSortBuilder.generateSort("createTime_d")));
    }

    @Override
    public List<WorkGroup> findAllList() {
        return workGroupRepo.findAllByIsDeleted(DeleteStatus.enable.ordinal());
    }

    @Override
    public WorkGroup detail(Long id) {
        return workGroupRepo.findOneById(id);
    }
}
