package com.magic.jovi.entities.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fanjiawei on 2018/4/1
 */
@Data
public abstract class BaseVO implements Serializable {

    private static final long serialVersionUID = -1586054152340145113L;

    private String id;

    private int isDeleted;

    private int version;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;
}
