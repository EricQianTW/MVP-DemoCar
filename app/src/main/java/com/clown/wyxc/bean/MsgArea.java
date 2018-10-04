package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by JokerEric on 2016/6/29.
 */
public class MsgArea extends Message {
    //版本
    @Expose
    private String version;

    //列表
    @Expose
    private List<AreaList> mal;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<AreaList> getMal() {
        return mal;
    }

    public void setMal(List<AreaList> mal) {
        this.mal = mal;
    }
}
