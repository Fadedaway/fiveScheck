package com.magic.jovi.utils;

/**
 * Created by fanjiawei on 2018/4/3
 */
public enum OperateSymbol {

    E(0, "=", "等于"),
    NE(1, "!=", "不等于"),
    GE(2, ">=", "大于等于"),
    LE(3, "<=", "小于等于"),
    G(4, ">", "大于"),
    L(5, "<", "小于"),
    AL(6, ":", "全相似 %value%"),
    LL(7, "l:", "左相似 value%"),
    RL(8, ":l", "右相似 %value"),
    N(9, "null", "为null"),
    NN(10, "!null", "不为null");

    OperateSymbol(int ordinal, String symbol, String desc) {
        this.ordinal = ordinal;
        this.symbol = symbol;
        this.desc = desc;
    }

    private int ordinal;

    private String symbol;

    private String desc;

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
