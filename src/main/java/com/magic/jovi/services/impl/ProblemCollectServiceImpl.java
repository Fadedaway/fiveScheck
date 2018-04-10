package com.magic.jovi.services.impl;

import com.magic.jovi.entities.ProblemCollect;
import com.magic.jovi.entities.WorkGroup;
import com.magic.jovi.entities.WorkPosition;
import com.magic.jovi.entities.vo.ProblemCollectVO;
import com.magic.jovi.repositories.ProblemCollectRepo;
import com.magic.jovi.repositories.WorkGroupRepo;
import com.magic.jovi.repositories.WorkPositionRepo;
import com.magic.jovi.services.ProblemCollectService;
import com.magic.jovi.utils.DeleteStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fanjiawei on 2018/4/10
 */
@Service
@Transactional
public class ProblemCollectServiceImpl implements ProblemCollectService {

    private WorkGroupRepo workGroupRepo;

    private WorkPositionRepo workPositionRepo;

    private ProblemCollectRepo problemCollectRepo;

    @Autowired
    public ProblemCollectServiceImpl(WorkGroupRepo workGroupRepo, WorkPositionRepo workPositionRepo, ProblemCollectRepo problemCollectRepo) {
        this.workGroupRepo = workGroupRepo;
        this.workPositionRepo = workPositionRepo;
        this.problemCollectRepo = problemCollectRepo;
    }

    @Override
    public List<ProblemCollectVO> initData(String createDate, Long groupId) {

        WorkGroup workGroup = workGroupRepo.findOneById(groupId);

        List<WorkPosition> workPositions = workPositionRepo.findAllByIsDeleted(DeleteStatus.enable.ordinal());

        return workPositions.stream().map(workPosition -> {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            ProblemCollect problemCollect = new ProblemCollect();
            problemCollect.setGroupId(groupId);
            problemCollect.setPositionId(workPosition.getId());
            try {
                problemCollect.setCheckDate(simpleDateFormat.parse(createDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            problemCollect = problemCollectRepo.save(problemCollect);

            ProblemCollectVO problemCollectVO = new ProblemCollectVO();
            BeanUtils.copyProperties(problemCollect, problemCollectVO);

            problemCollectVO.setGroupName(workGroup.getName());
            problemCollectVO.setPositionName(workPosition.getName());
            return problemCollectVO;
        }).collect(Collectors.toList());
    }
}
