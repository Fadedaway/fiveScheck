package com.magic.jovi.utils;

/**
 * Created by fanjiawei on 2018/3/31
 * controller 返回对象
 */
public class ReqResult<T> {

    private boolean success;

    private T data;

    public static <Q> ReqResult<Q> success(Q data) {
        ReqResult<Q> reqResult = new ReqResult<>();
        reqResult.setData(data);
        reqResult.setSuccess(true);
        return reqResult;
    }

    public static <Q> ReqResult<Q> failure(Q data) {
        ReqResult<Q> reqResult = new ReqResult<>();
        reqResult.setData(data);
        reqResult.setSuccess(false);
        return reqResult;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
