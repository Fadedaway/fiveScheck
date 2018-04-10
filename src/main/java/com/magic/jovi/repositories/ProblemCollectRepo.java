package com.magic.jovi.repositories;

import com.magic.jovi.entities.ProblemCollect;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * Created by fanjiawei on 2018/4/10
 */
public interface ProblemCollectRepo extends BaseJpaRepository<ProblemCollect, Serializable>, JpaSpecificationExecutor<ProblemCollect> {
}
