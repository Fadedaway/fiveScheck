package com.magic.jovi.listeners;

import com.magic.jovi.entities.BaseEntity;
import com.magic.jovi.utils.DeleteStatus;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by fanjiawei on 2018/3/31
 * 在数据持久化到数据库前对实体进行统一数据处理
 */
public class CRUDListener{

    private static final String SYS_NAME = "SYS";

    /**
     * 在persist方法调用后立刻发生，级联保存也会发生该事件，此时数据还没有真实插入数据库中。
     * @param entity 抽像公共类
     */
    @PrePersist
    public void preInsert(BaseEntity entity) {
        entity.setCreateTime(new Date());
        entity.setCreator(SYS_NAME);
        entity.setIsDeleted(DeleteStatus.enable.ordinal());
        preUpdate(entity);
    }

    /**
     * 在实体的状态同步到数据库之前触发，此时数据还没有真实更新到数据库中。
     * @param entity 抽象实体类
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity){
        entity.setModifyTime(new Date());
        entity.setModifier(SYS_NAME);
    }

}
