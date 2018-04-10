package com.magic.jovi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by fanjiawei on 2018/3/31
 */
@Entity
@Table(name = "problem_collect")
public class ProblemCollect extends BaseEntity{

    private static final long serialVersionUID = 7916266883686670904L;

    /**
     * 机台Id
     */
    @Column
    private Long positionId;

    /**
     * 工作组Id
     */
    @Column
    private Long groupId;

    /**
     * 问题Id
     */
    @Column
    private Long problemId;

    /**
     * 减分
     */
    @Column
    private int point;

    /**
     * 检查时间
     */
    @Column
    private Date checkDate;

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
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
