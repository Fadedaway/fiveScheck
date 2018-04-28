package com.magic.jovi.services.impl;

import com.magic.jovi.entities.WorkGroup;
import com.magic.jovi.entities.WorkPosition;
import com.magic.jovi.entities.vo.PageVO;
import com.magic.jovi.entities.vo.WorkPositionVO;
import com.magic.jovi.repositories.WorkGroupRepo;
import com.magic.jovi.repositories.WorkPositionRepo;
import com.magic.jovi.services.WorkPositionService;
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
 * Created by fanjiawei on 2018/4/3
 */
@Service
@Transactional
public class WorkPositionServiceImpl implements WorkPositionService {

    private WorkPositionRepo workPositionRepo;

    private WorkGroupRepo workGroupRepo;

    @Autowired
    public WorkPositionServiceImpl(WorkPositionRepo workPositionRepo, WorkGroupRepo workGroupRepo) {
        this.workPositionRepo = workPositionRepo;
        this.workGroupRepo = workGroupRepo;
    }

    @Override
    public void add(WorkPositionVO workPositionVO) {
        WorkPosition workPosition = new WorkPosition();
        BeanUtils.copyProperties(workPositionVO, workPosition);

        workPositionRepo.save(workPosition);
    }

    @Override
    public void edit(WorkPositionVO workPositionVO) {
        if (Objects.isNull(workPositionVO))
            throw new RuntimeException("数据不存在");
        if (Objects.isNull(workPositionVO.getId()))
            throw new RuntimeException("主键为空");

        WorkPosition workPosition = workPositionRepo.findOneById(workPositionVO.getId());

        if (null != workPosition) {

            if (StringUtils.isNotBlank(workPositionVO.getName()))
                workPosition.setName(workPositionVO.getName());

            if (Objects.nonNull(workPositionVO.getGroupId()) && workPositionVO.getGroupId() != 0)
                workPosition.setGroupId(workPositionVO.getGroupId());

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
                WorkPosition workPosition = workPositionRepo.findOneById(Long.valueOf(id));

                if (Objects.nonNull(workPosition)) {
                    workPositionRepo.logicalDelete(Long.valueOf(id));
                }
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<WorkPosition> findAll(Long groupId ,PageVO pageVO) {
        SimpleSpecificationBuilder<WorkPosition> builder = new SimpleSpecificationBuilder<>("isDeleted",
                OperateSymbol.E.getSymbol(), DeleteStatus.enable.ordinal());

        if (Objects.nonNull(groupId) && groupId != 0)
            builder.addAnd("groupId", OperateSymbol.E.getSymbol(), groupId);


        Page<WorkPosition> page = workPositionRepo.findAll(builder.generateSpecification(),
                SimplePageBuilder.generate(pageVO.getPage() - 1, SimpleSortBuilder.generateSort("createTime_d")));

        if (Objects.nonNull(page) && Objects.nonNull(page.getContent()) && page.getContent().size() > 0) {
            page.getContent().forEach(workPosition -> {
                WorkGroup workGroup = workGroupRepo.findOneById(workPosition.getGroupId());

                workPosition.setGroupName(workGroup.getName());
            });
        }
        return page;
    }

    @Override
    public List<WorkPosition> findAllList() {
        return workPositionRepo.findAllByIsDeleted(DeleteStatus.enable.ordinal());
    }

    @Override
    public WorkPosition detail(Long id) {
        WorkPosition workPosition = workPositionRepo.findOneById(id);

        if (Objects.nonNull(workPosition)) {
            WorkGroup workGroup = workGroupRepo.findOneById(workPosition.getGroupId());

            workPosition.setGroupName(workGroup.getName());
        }

        return workPosition;
    }
}
