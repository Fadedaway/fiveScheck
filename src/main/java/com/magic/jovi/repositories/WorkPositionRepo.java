package com.magic.jovi.repositories;

import com.magic.jovi.entities.WorkPosition;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Created by fanjiawei on 2018/4/4
 */
public interface WorkPositionRepo extends BaseJpaRepository<WorkPosition, Serializable>, JpaSpecificationExecutor<WorkPosition> {
}
