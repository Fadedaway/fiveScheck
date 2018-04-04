package com.magic.jovi.utils;

import com.magic.jovi.entities.vo.PageVO;

/**
 * Created by fanjiawei on 2018/3/31
 * controller 返回对象
 */
public class ReqResult<T> {

    private boolean success;

    private T data;

    private PageVO pageVO;

    public static <Q> ReqResult<Q> success(Q data) {
        ReqResult<Q> reqResult = new ReqResult<>();
        reqResult.setData(data);
        reqResult.setSuccess(true);
        return reqResult;
    }

    //分页返回
    public static <Q> ReqResult<Q> success(Q data, PageVO pageVO) {
        ReqResult<Q> reqResult = new ReqResult<>();
        reqResult.setData(data);
        reqResult.setSuccess(true);
        reqResult.setPageVO(pageVO);
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

    public PageVO getPageVO() {
        return pageVO;
    }

    public void setPageVO(PageVO pageVO) {
        this.pageVO = pageVO;
    }
}
