package com.magic.jovi.entities;

import com.magic.jovi.listeners.CRUDListener;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by fanjiawei on 2018/3/31
 */
@Data
@MappedSuperclass
@Audited
@EntityListeners({AuditingEntityListener.class, CRUDListener.class})
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -5040425880097759738L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private int isDeleted;

    @Version
    @Column
    private int version;

    @Column
    private String creator;

    @Column
    private Date createTime;

    @Column
    private String modifier;

    @Column
    private Date modifyTime;
}
