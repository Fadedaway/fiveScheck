package com.magic.jovi.repositories;

import com.magic.jovi.entities.WorkGroup;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanjiawei on 2018/3/31
 */
public interface WorkGroupRepo extends BaseJpaRepository<WorkGroup, Serializable>, JpaSpecificationExecutor<WorkGroup> {

    List<WorkGroup> findAllByIsDeleted(int ordinal);
}
