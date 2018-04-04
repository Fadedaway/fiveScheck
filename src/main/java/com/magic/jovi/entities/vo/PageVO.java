package com.magic.jovi.entities.vo;

import java.io.Serializable;

/**
 * Created by fanjiawei on 2018/4/4
 */
public class PageVO implements Serializable {

    private static final long serialVersionUID = 6514262106063556397L;

    /**
     * 当前页码
     */
    private int page;

    /**
     * 每页显示条数
     */
    private int pageSize;

    /**
     * 数据总量
     */
    private int totalCount;

    /**
     * 总页数
     */
    private int totalPage;

    public PageVO() {
    }

    public PageVO(int page) {
        this.page = page;
    }

    public PageVO(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
