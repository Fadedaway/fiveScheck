package com.magic.jovi.services.impl;

import com.magic.jovi.entities.ProblemCollect;
import com.magic.jovi.entities.WorkGroup;
import com.magic.jovi.entities.WorkPosition;
import com.magic.jovi.entities.WorkProblem;
import com.magic.jovi.entities.vo.ProblemCollectVO;
import com.magic.jovi.entities.vo.QueryVO;
import com.magic.jovi.repositories.ProblemCollectRepo;
import com.magic.jovi.repositories.WorkGroupRepo;
import com.magic.jovi.repositories.WorkPositionRepo;
import com.magic.jovi.repositories.WorkProblemRepo;
import com.magic.jovi.services.ProblemCollectService;
import com.magic.jovi.specification.SimplePageBuilder;
import com.magic.jovi.specification.SimpleSortBuilder;
import com.magic.jovi.specification.SimpleSpecificationBuilder;
import com.magic.jovi.utils.DeleteStatus;
import com.magic.jovi.utils.OperateSymbol;
import com.magic.jovi.utils.excelUtil.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by fanjiawei on 2018/4/10
 */
@Service
@Transactional
public class ProblemCollectServiceImpl implements ProblemCollectService {

    private WorkGroupRepo workGroupRepo;

    private WorkPositionRepo workPositionRepo;

    private WorkProblemRepo workProblemRepo;

    private ProblemCollectRepo problemCollectRepo;

    @Autowired
    public ProblemCollectServiceImpl(WorkGroupRepo workGroupRepo, WorkPositionRepo workPositionRepo, WorkProblemRepo workProblemRepo, ProblemCollectRepo problemCollectRepo) {
        this.workGroupRepo = workGroupRepo;
        this.workPositionRepo = workPositionRepo;
        this.workProblemRepo = workProblemRepo;
        this.problemCollectRepo = problemCollectRepo;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProblemCollectVO> initData(String createDate, Long groupId) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date checkDate = null;
        try {
            checkDate = simpleDateFormat.parse(createDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        WorkGroup workGroup = workGroupRepo.findOneById(groupId);

        //校验有没有当天的检查数据
        List<ProblemCollect> collectList = problemCollectRepo.findAll(new SimpleSpecificationBuilder<ProblemCollect>("isDeleted",
                OperateSymbol.E.getSymbol(), DeleteStatus.enable.ordinal()).addAnd("groupId", OperateSymbol.E.getSymbol(),
                groupId).addAnd("checkDate", OperateSymbol.E.getSymbol(), checkDate).generateSpecification());

        if (Objects.nonNull(collectList) && collectList.size() > 0) {

            return collectList.stream().map(problemCollect -> {
                ProblemCollectVO problemCollectVO = new ProblemCollectVO();
                BeanUtils.copyProperties(problemCollect, problemCollectVO);
                problemCollectVO.setGroupName(workGroup.getName());

                WorkPosition workPosition = workPositionRepo.findOneById(problemCollect.getPositionId());
                problemCollectVO.setPositionName(workPosition.getName());

                if (StringUtils.isNotBlank(problemCollect.getProblemId())) {
                    final int[] counter = {0};
                    List problemNameList = Arrays.stream(problemCollect.getProblemId().split(",")).map(problemId -> {
                        WorkProblem workProblem = workProblemRepo.findOneById(problemId);

                        counter[0] += workProblem.getPoint();
                        return workProblem.getName();
                    }).collect(Collectors.toList());

                    String problemNames = StringUtils.join(problemNameList, ",");
                    problemCollectVO.setPoint(counter[0]);
                    problemCollectVO.setProblemName(problemNames);
                } else {
                    problemCollectVO.setPoint(0);
                    problemCollectVO.setProblemName("");
                }

                return problemCollectVO;
            }).collect(Collectors.toList());
        }

        List<WorkPosition> workPositions = workPositionRepo.findAllByIsDeleted(DeleteStatus.enable.ordinal());


        Date finalCheckDate = checkDate;
        return workPositions.stream().map(workPosition -> {

            ProblemCollect problemCollect = new ProblemCollect();
            problemCollect.setGroupId(groupId);
            problemCollect.setPositionId(workPosition.getId());
            problemCollect.setCheckDate(finalCheckDate);
            problemCollect = problemCollectRepo.save(problemCollect);

            ProblemCollectVO problemCollectVO = new ProblemCollectVO();
            BeanUtils.copyProperties(problemCollect, problemCollectVO);

            problemCollectVO.setGroupName(workGroup.getName());
            problemCollectVO.setPositionName(workPosition.getName());
            problemCollectVO.setProblemName("");
            return problemCollectVO;
        }).collect(Collectors.toList());
    }

    @Override
    public void gatherPoints(ProblemCollectVO problemCollectVO) {
        if (Objects.isNull(problemCollectVO))
            throw new RuntimeException("传入数据为空");
        if (Objects.isNull(problemCollectVO.getId()))
            throw new RuntimeException("数据主键为空");

        // 获取前台传入的问题id字符串与分数只和，更新入问题收集表中
        ProblemCollect problemCollect = problemCollectRepo.findOneById(problemCollectVO.getId());

        if (Objects.isNull(problemCollect))
            throw new RuntimeException("数据不存在或已被删除");

        problemCollect.setProblemId(problemCollectVO.getProblemId());
        problemCollect.setPoint(problemCollectVO.getPoint());

        problemCollectRepo.save(problemCollect);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<ProblemCollect> queryInfo(QueryVO queryVO) throws ParseException {
        if (Objects.isNull(queryVO))
            throw new RuntimeException("传入数据为空");

        SimpleSpecificationBuilder<ProblemCollect> builder = new SimpleSpecificationBuilder<>("isDeleted",
                OperateSymbol.E.getSymbol(), DeleteStatus.enable.ordinal());

        if (Objects.nonNull(queryVO.getGroupId()))
            builder.addAnd("groupId", OperateSymbol.E.getSymbol(), queryVO.getGroupId());

        if (Objects.nonNull(queryVO.getPositionId()))
            builder.addAnd("positionId", OperateSymbol.E.getSymbol(), queryVO.getPositionId());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (StringUtils.isNotBlank(queryVO.getBeginDate())) {
            Date beginDate = simpleDateFormat.parse(queryVO.getBeginDate());
            builder.addAnd("checkDate", OperateSymbol.GE.getSymbol(), beginDate);
        }

        if (StringUtils.isNotBlank(queryVO.getEndDate())) {
            Date endDate = simpleDateFormat.parse(queryVO.getEndDate());
            builder.addAnd("checkDate", OperateSymbol.LE.getSymbol(), endDate);
        }

        Sort sort = SimpleSortBuilder.generateSort("createTime_d");

        Pageable pageable = SimplePageBuilder.generate(queryVO.getPageNum() - 1, queryVO.getPageSize(), sort);
        Page<ProblemCollect> collectPage = problemCollectRepo.findAll(builder.generateSpecification(), pageable);

        if (Objects.nonNull(collectPage) && collectPage.getContent().size() > 0) {
            collectPage.getContent().forEach(problemCollect -> {

                WorkGroup workGroup = workGroupRepo.findOneById(problemCollect.getGroupId());
                WorkPosition workPosition = workPositionRepo.findOneById(problemCollect.getPositionId());

                problemCollect.setGroupName(workGroup.getName());
                problemCollect.setPositionName(workPosition.getName());

                if (StringUtils.isNotBlank(problemCollect.getProblemId())) {
                    final int[] counter = {0};
                    List problemNameList = Arrays.stream(problemCollect.getProblemId().split(",")).map(problemId -> {
                        WorkProblem workProblem = workProblemRepo.findOneById(problemId);

                        counter[0] += workProblem.getPoint();
                        return workProblem.getName();
                    }).collect(Collectors.toList());

                    String problemNames = StringUtils.join(problemNameList, ",");
                    problemCollect.setPoint(counter[0]);
                    problemCollect.setProblemName(problemNames);
                } else {
                    problemCollect.setPoint(0);
                    problemCollect.setProblemName("");
                }
            });
        }

        return collectPage;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void exportByParam(QueryVO queryVO, HttpServletResponse response) throws ParseException, IOException {
        if (Objects.isNull(queryVO))
            throw new RuntimeException("传入数据为空");

        SimpleSpecificationBuilder<ProblemCollect> builder = new SimpleSpecificationBuilder<>("isDeleted",
                OperateSymbol.E.getSymbol(), DeleteStatus.enable.ordinal());

        if (Objects.nonNull(queryVO.getGroupId()))
            builder.addAnd("groupId", OperateSymbol.E.getSymbol(), queryVO.getGroupId());

        if (Objects.nonNull(queryVO.getPositionId()))
            builder.addAnd("positionId", OperateSymbol.E.getSymbol(), queryVO.getPositionId());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (StringUtils.isNotBlank(queryVO.getBeginDate())) {
            Date beginDate = simpleDateFormat.parse(queryVO.getBeginDate());
            builder.addAnd("checkDate", OperateSymbol.GE.getSymbol(), beginDate);
        }

        if (StringUtils.isNotBlank(queryVO.getEndDate())) {
            Date endDate = simpleDateFormat.parse(queryVO.getEndDate());
            builder.addAnd("checkDate", OperateSymbol.LE.getSymbol(), endDate);
        }

        Sort sort = SimpleSortBuilder.generateSort("createTime_d");

        List<ProblemCollect> problemCollectList = problemCollectRepo.findAll(builder.generateSpecification(), sort);

        List<Map<String, Object>> dataList = problemCollectList.stream().map(problemCollect -> {
            WorkGroup workGroup = workGroupRepo.findOneById(problemCollect.getGroupId());
            WorkPosition workPosition = workPositionRepo.findOneById(problemCollect.getPositionId());

            problemCollect.setGroupName(workGroup.getName());
            problemCollect.setPositionName(workPosition.getName());

            if (StringUtils.isNotBlank(problemCollect.getProblemId())) {
                final int[] counter = {0};
                List problemNameList = Arrays.stream(problemCollect.getProblemId().split(",")).map(problemId -> {
                    WorkProblem workProblem = workProblemRepo.findOneById(problemId);

                    counter[0] += workProblem.getPoint();
                    return workProblem.getName();
                }).collect(Collectors.toList());

                String problemNames = StringUtils.join(problemNameList, ",");
                problemCollect.setPoint(counter[0]);
                problemCollect.setProblemName(problemNames);
            } else {
                problemCollect.setPoint(0);
                problemCollect.setProblemName("");
            }

            Map<String, Object> data = new HashMap<>();
            data.put("checkDateStr", problemCollect.getCheckDateStr());
            data.put("groupName", problemCollect.getGroupName());
            data.put("positionName", problemCollect.getPositionName());
            data.put("problemName", problemCollect.getProblemName());
            data.put("point", problemCollect.getPoint());
            return data;
        }).collect(Collectors.toList());

        Map<String, String> headers = new HashMap<>();
        headers.put("checkDateStr", "日期");
        headers.put("groupName", "小组");
        headers.put("positionName", "机器");
        headers.put("problemName", "问题");
        headers.put("point", "扣分");

        response.setHeader("Content-Type","application/force-download");
        response.setHeader("Content-Type","application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=" + new Date().getTime()+".xls");
        ExcelUtil.exportExcel(headers, dataList, response.getOutputStream());
    }
}
