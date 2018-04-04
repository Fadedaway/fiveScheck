package com.magic.jovi.specification;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fanjiawei on 2018/4/4
 * 排序工具类
 */
public class SimpleSortBuilder {

    /**
     * 调用的时候使用 SimpleSortBuilder.generateSort("name","createTime_d"); 表示先以name升序，之后createTime降序
     * @param fields 需要排序的字段
     * @return Sort
     */
    public static Sort generateSort(String... fields) {
        List<Sort.Order> orders = new ArrayList<>();
        Arrays.stream(fields).forEach(field -> orders.add(generateOrder(field)));
        return new Sort(orders);
    }

    private static Sort.Order generateOrder(String field) {
        Sort.Order order;
        String[] fieldArray = field.split("_");
        if (fieldArray.length >= 2) {
            if ("d".equalsIgnoreCase(fieldArray[1]))
                order = new Sort.Order(Sort.Direction.DESC, fieldArray[0]);
            else
                order = new Sort.Order(Sort.Direction.ASC, fieldArray[0]);

            return order;
        }
        return new Sort.Order(field);
    }
}
