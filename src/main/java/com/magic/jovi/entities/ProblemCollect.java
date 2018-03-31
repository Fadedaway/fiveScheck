package com.magic.jovi.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by fanjiawei on 2018/3/31
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "problem_collect")
public class ProblemCollect extends BaseEntity{

    private static final long serialVersionUID = 7916266883686670904L;

    /**
     * 机台Id
     */
    @Column
    private String positionId;

    /**
     * 工作组Id
     */
    @Column
    private String groupId;

    /**
     * 问题Id
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
}
