package com.magic.jovi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
     * 问题Id 逗号分隔
     */
    @Column
    private String problemId;

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

    @Transient
    private String checkDateStr;

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

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
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

    public String getCheckDateStr() {
        if (Objects.nonNull(checkDate)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            return simpleDateFormat.format(checkDate);
        }
        return "";
    }

    public void setCheckDateStr(String checkDateStr) {
        this.checkDateStr = checkDateStr;
    }
}
