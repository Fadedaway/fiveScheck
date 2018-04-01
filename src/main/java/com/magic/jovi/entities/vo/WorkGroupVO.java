package com.magic.jovi.entities.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by fanjiawei on 2018/3/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WorkGroupVO extends BaseVO {

    private static final long serialVersionUID = -4617981240426123493L;

    private String name;
}
