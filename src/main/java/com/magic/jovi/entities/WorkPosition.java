package com.magic.jovi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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

    /**
     * 工作组主键
     */
    @Column
    private Long groupId;

    /**
     * 工作组名称
     */
    @Transient
    private String groupName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
