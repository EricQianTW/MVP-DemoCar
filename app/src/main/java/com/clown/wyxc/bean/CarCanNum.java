package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by eric_clown on 2017/5/23.
 */

public class CarCanNum extends Message {

    @Expose
    private String Pcode;

    @Expose
    private String code;

    @Expose
    private List<CarCanNum> list;

    public String getPcode() {
        return Pcode;
    }

    public void setPcode(String pcode) {
        Pcode = pcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CarCanNum> getList() {
        return list;
    }

    public void setList(List<CarCanNum> list) {
        this.list = list;
    }
}
