package com.clown.wyxc.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by JokerEric on 2016/6/22.
 */
public class Page<E> {
    /**
     *
     */
    @Expose
    private Integer PageNo;
    @Expose
    private Integer RecNum;
    @Expose
    private Integer PageCnt;
    @Expose
    private List<E> List;

    public Integer getPageNo() {
        return PageNo;
    }

    public void setPageNo(Integer pageNo) {
        PageNo = pageNo;
    }

    public Integer getRecNum() {
        return RecNum;
    }

    public void setRecNum(Integer recNum) {
        RecNum = recNum;
    }

    public Integer getPageCnt() {
        return PageCnt;
    }

    public void setPageCnt(Integer pageCnt) {
        PageCnt = pageCnt;
    }

    public java.util.List<E> getList() {
        return List;
    }

    public void setList(java.util.List<E> list) {
        List = list;
    }
}
