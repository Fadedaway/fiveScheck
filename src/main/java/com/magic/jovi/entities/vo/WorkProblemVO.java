package com.magic.jovi.entities.vo;

/**
 * Created by fanjiawei on 2018/4/5
 */
public class WorkProblemVO extends BaseVO {

    private static final long serialVersionUID = -2880396437336842492L;

    private String name;

    private String detail;

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
