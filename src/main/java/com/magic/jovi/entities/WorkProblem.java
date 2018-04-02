package com.magic.jovi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by fanjiawei on 2018/3/31
 */
@Entity
@Table(name = "work_problem")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
