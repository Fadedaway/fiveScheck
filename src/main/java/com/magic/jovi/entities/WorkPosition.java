package com.magic.jovi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by fanjiawei on 2018/3/31
 */
@Entity
@Table(name = "work_position")
public class WorkPosition extends BaseEntity {

    private static final long serialVersionUID = -3676510541319107309L;

    /**
     * 机台名称
     */
    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
