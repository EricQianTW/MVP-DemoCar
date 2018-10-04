package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by eric_clown on 2017/5/30.
 */

public class MsgInGoodsTaxDesc extends Message {
    @Expose
    private int Id;
    @Expose
    private String keyName;
    @Expose
    private String title;
    @Expose
    private String contents;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
