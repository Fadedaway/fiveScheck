package com.magic.jovi.services.impl;

import com.magic.jovi.entities.WorkGroup;
import com.magic.jovi.entities.vo.WorkGroupVO;
import com.magic.jovi.repositories.WorkGroupRepo;
import com.magic.jovi.services.WorkGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
