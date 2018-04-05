package com.magic.jovi.specification;

import com.magic.jovi.entities.specification.SpecificationOperator;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanjiawei on 2018/4/3
 */
public class SimpleSpecificationBuilder<T> {

    private List<SpecificationOperator> operators;

    public SimpleSpecificationBuilder(String key, String oper, Object value) {
        SpecificationOperator operator = new SpecificationOperator();
        operator.setJoin("and");
        operator.setKey(key);
        operator.setOper(oper);
        operator.setValue(value);
        operators = new ArrayList<>();
        operators.add(operator);
    }

    public SimpleSpecificationBuilder() {
        operators = new ArrayList<>();
    }

    /**
     * 完成条件的添加
     * @return
     */
    public SimpleSpecificationBuilder add(String key,String oper,Object value,String join) {
        SpecificationOperator so = new SpecificationOperator();
        so.setKey(key);
        so.setValue(value);
        so.setOper(oper);
        so.setJoin(join);
        operators.add(so);
        return this;
    }

    /**
     * 添加or条件的重载
     * @return this，方便后续的链式调用
     */
    public SimpleSpecificationBuilder addOr(String key,String oper,Object value) {
        return this.add(key,oper,value,"or");
    }

    /**
     * 添加and的条件
     * @return
     */
    public SimpleSpecificationBuilder addAnd(String key,String oper,Object value) {
        return this.add(key,oper,value,"and");
    }

    public Specification generateSpecification() {
        return new SimpleSpecification<T>(operators);
    }

}
