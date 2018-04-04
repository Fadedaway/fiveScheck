package com.magic.jovi.specification;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by fanjiawei on 2018/4/4
 * 分页工具类
 */
public class SimplePageBuilder {

    /**
     * 默认的 pageSize，每页的展示数据量
     */
    private static final int PAGE_SIZE = 10;

    public static Pageable generate(int page, int pageSize, Sort sort) {
        if (null == sort)
            return new PageRequest(page, pageSize);
        return new PageRequest(page, pageSize, sort);
    }

    public static Pageable generate(int page) {
        return new PageRequest(page, PAGE_SIZE, null);
    }

    public static Pageable generate(int page, Sort sort) {
        return new PageRequest(page, PAGE_SIZE, sort);
    }
}
