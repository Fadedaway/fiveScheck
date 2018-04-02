package com.magic.jovi.repositories.impl;

import com.magic.jovi.entities.BaseEntity;
import com.magic.jovi.repositories.BaseJpaRepository;
import com.magic.jovi.utils.DeleteStatus;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by fanjiawei on 2018/4/2
 */
public class BaseJpaRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseJpaRepository<T, ID> {

    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";
    private static final String LIST_MUST_NOT_BE_EMPTY = "The given collection must not be empty!";

    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public void logicalDelete(ID id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        T entity = findOneById(id);
        if (entity == null) {
            throw new EmptyResultDataAccessException(String.format("No entity with id %s exists!", id), 1);
        }
        entity.setIsDeleted(DeleteStatus.disable.ordinal());
        save(entity);

    }

    @Override
    public void logicalDeleteByIdList(List<ID> idList) {
        Assert.notEmpty(idList, LIST_MUST_NOT_BE_EMPTY);
        idList.forEach(id -> {
            T entity = findOneById(id);
            if (null == entity) {
                throw new EmptyResultDataAccessException(String.format("No entity with id %s exists!", id), 1);
            }
            entity.setIsDeleted(DeleteStatus.disable.ordinal());
            save(entity);
        });
    }

    @Override
    public void logicalDelete(List<T> entities) {
        Assert.notEmpty(entities, LIST_MUST_NOT_BE_EMPTY);
        for (T entity : entities) {
            if (Objects.isNull(entity)){
                throw new NullPointerException("Null entity exists!");
            }
            entity.setIsDeleted(DeleteStatus.disable.ordinal());
            save(entity);
        }

    }

    @Override
    public T findOneById(ID id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        return findOne((root, query, cb)->{
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(cb.equal(root.<Long>get("id"), id));
            //未删除数据
            predicateList.add(cb.equal(root.<Integer>get("deleted"), DeleteStatus.enable.ordinal()));
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
    }

}
