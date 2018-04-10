package com.magic.jovi.entities.vo;

import java.util.Date;

/**
 * Created by fanjiawei on 2018/4/10
 */
public class ProblemCollectVO extends BaseVO {

    private static final long serialVersionUID = -579923613708936806L;

    /**
     * 机台Id
     */
    private Long positionId;

    /**
     * 机台名称
     */
    private String positionName;

    /**
     * 工作组Id
     */
    private Long groupId;

    /**
     * 工作组名称
     */
    private String groupName;

    /**
     * 问题Id
     */
    private Long problemId;

    /**
     * 问题描述
     */
    private String problemName;

    /**
     * 减分
     */
    private int point;

    /**
     * 检查时间
     */
    private Date checkDate;

    public ProblemCollectVO() {
    }

    public ProblemCollectVO(Long positionId, String positionName, Long groupId, String groupName, Long problemId, String problemName, int point, Date checkDate) {
        this.positionId = positionId;
        this.positionName = positionName;
        this.groupId = groupId;
        this.groupName = groupName;
        this.problemId = problemId;
        this.problemName = problemName;
        this.point = point;
        this.checkDate = checkDate;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}
