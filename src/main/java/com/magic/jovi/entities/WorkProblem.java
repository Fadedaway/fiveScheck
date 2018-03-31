package com.magic.jovi.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by fanjiawei on 2018/3/31
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "work_problem")
@Data
public class WorkProblem extends BaseEntity {

    private static final long serialVersionUID = -2819958311088265152L;

    /**
     * 问题名称
     */
    @Column
    private String name;

    /**
     * 问题详情
     */
    @Column
    private String detail;

    /**
     * 减分
     */
    @Column
    private int point;
}
