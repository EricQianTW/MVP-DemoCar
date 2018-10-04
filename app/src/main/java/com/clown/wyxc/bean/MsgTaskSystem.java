package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by JokerEric on 2016/7/13.
 */
public class MsgTaskSystem extends Message {

    //
    @Expose
    private int Id;

    //身份ID
    @Expose
    private int PassportId;

    //标题
    @Expose
    private String TaskTitle;

    //任务地址(appurl)
    @Expose
    private String TaskUrl;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPassportId() {
        return PassportId;
    }

    public void setPassportId(int passportId) {
        PassportId = passportId;
    }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        TaskTitle = taskTitle;
    }

    public String getTaskUrl() {
        return TaskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        TaskUrl = taskUrl;
    }
}
