package com.magic.jovi.repositories;

import com.magic.jovi.entities.WorkProblem;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Created by fanjiawei on 2018/4/5
 */
public interface WorkProblemRepo extends BaseJpaRepository<WorkProblem, Serializable>, JpaSpecificationExecutor<WorkProblem> {
}
