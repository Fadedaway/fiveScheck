package com.magic.jovi.entities.vo;

/**
 * Created by fanjiawei on 2018/4/4
 */
public class WorkPositionVO extends BaseVO {

    private static final long serialVersionUID = 5132071639822968535L;

    private String name;

    private Long groupId;

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
