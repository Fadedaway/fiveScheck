package com.magic.jovi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanjiawei on 2018/4/2
 */
@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    void logicalDelete(ID id);

    void logicalDeleteByIdList(List<ID> idList);

    void logicalDelete(List<T> entities);

    T findOneById(ID id);
}
