package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by JokerEric on 2016/7/3.
 */
public class Memberaccount_Set extends Message{
    //
    @Expose
    public int Id;

    //
    @Expose
    public int SplitDur;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSplitDur() {
        return SplitDur;
    }

    public void setSplitDur(int splitDur) {
        SplitDur = splitDur;
    }
}
