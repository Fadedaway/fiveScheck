package com.magic.jovi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by fanjiawei on 2018/3/31
 * 工作组
 */
@Entity
@Table(name = "work_group")
public class WorkGroup extends BaseEntity {

    private static final long serialVersionUID = -7981336478589801454L;

    /**
     * 工作组名称
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
