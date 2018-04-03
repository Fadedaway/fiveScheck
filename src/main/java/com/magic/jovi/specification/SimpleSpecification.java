package com.magic.jovi.specification;

import com.magic.jovi.entities.specification.SpecificationOperator;
import com.magic.jovi.utils.OperateSymbol;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * Created by fanjiawei on 2018/4/3
 */
public class SimpleSpecification<T> implements Specification<T> {

    private static final String LIKE_PREFIX = "%";

    /**
     * 查询的条件列表，是一组列表
     */
    private List<SpecificationOperator> operators;

    public SimpleSpecification(List<SpecificationOperator> operators) {
        this.operators = operators;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        //组装查询条件
        Predicate predicate = null;

        int index = 0;
        for (SpecificationOperator operator : operators) {
            //第一个条件
            if (index++ == 0) {
                predicate = generatePredicate(root, cb, operator);
                continue;
            }

            Predicate pre = generatePredicate(root, cb, operator);
            if (null == pre)
                continue;

            if ("and".equalsIgnoreCase(operator.getJoin()))
                predicate = cb.and(predicate, pre);
            else if ("or".equalsIgnoreCase(operator.getJoin()))
                predicate = cb.or(predicate, pre);
        }

        return predicate;
    }

    private Predicate generatePredicate(Root<T> root, CriteriaBuilder criteriaBuilder, SpecificationOperator operator) {
        if (OperateSymbol.E.getSymbol().equalsIgnoreCase(operator.getOper())) {
            return criteriaBuilder.equal(root.get(operator.getKey()), operator.getValue());
        } else if (OperateSymbol.NE.getSymbol().equalsIgnoreCase(operator.getOper())) {
            return criteriaBuilder.notEqual(root.get(operator.getKey()), operator.getValue());
        } else if (OperateSymbol.GE.getSymbol().equalsIgnoreCase(operator.getOper())) {
            if (operator.getValue() instanceof Date)
                return criteriaBuilder.greaterThanOrEqualTo(root.get(operator.getKey()), (Date) operator.getValue());
            else
                return criteriaBuilder.ge(root.get(operator.getKey()), (Number) operator.getValue());
        } else if (OperateSymbol.LE.getSymbol().equalsIgnoreCase(operator.getOper())) {
            if (operator.getValue() instanceof Date)
                return criteriaBuilder.lessThanOrEqualTo(root.get(operator.getKey()), (Date) operator.getValue());
            else
                return criteriaBuilder.le(root.get(operator.getKey()), (Number) operator.getValue());
        } else if (OperateSymbol.G.getSymbol().equalsIgnoreCase(operator.getOper())) {
            if (operator.getValue() instanceof Date)
                return criteriaBuilder.greaterThan(root.get(operator.getKey()), (Date) operator.getValue());
            else
                return criteriaBuilder.gt(root.get(operator.getKey()), (Number) operator.getValue());
        } else if (OperateSymbol.L.getSymbol().equalsIgnoreCase(operator.getOper())) {
            if (operator.getValue() instanceof Date)
                return criteriaBuilder.lessThan(root.get(operator.getKey()), (Date) operator.getValue());
            else
                return criteriaBuilder.lt(root.get(operator.getKey()), (Number) operator.getValue());
        } else if (OperateSymbol.AL.getSymbol().equalsIgnoreCase(operator.getOper())) {
            return criteriaBuilder.like(root.get(operator.getKey()), LIKE_PREFIX + operator.getValue() + LIKE_PREFIX);
        } else if (OperateSymbol.LL.getSymbol().equalsIgnoreCase(operator.getOper())) {
            return criteriaBuilder.like(root.get(operator.getKey()), operator.getValue() + LIKE_PREFIX);
        } else if (OperateSymbol.RL.getSymbol().equalsIgnoreCase(operator.getOper())) {
            return criteriaBuilder.like(root.get(operator.getKey()), LIKE_PREFIX + operator.getValue());
        } else if (OperateSymbol.N.getSymbol().equalsIgnoreCase(operator.getOper())) {
            return criteriaBuilder.isNull(root.get(operator.getKey()));
        } else if (OperateSymbol.NN.getSymbol().equalsIgnoreCase(operator.getOper())) {
            return criteriaBuilder.isNotNull(root.get(operator.getKey()));
        }
        return null;
    }
}
