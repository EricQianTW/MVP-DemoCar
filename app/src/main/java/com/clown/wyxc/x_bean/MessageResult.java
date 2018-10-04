package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class MessageResult extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String title;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this. title = title;
    }
    
    @Expose
    private String content;

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this. content = content;
    }
    
    @Expose
    private String iconUrl;

    public String getIconUrl(){
        return iconUrl;
    }

    public void setIconUrl(String iconUrl){
        this. iconUrl = iconUrl;
    }
    
    @Expose
    private String sendTime;

    public String getSendTime(){
        return sendTime;
    }

    public void setSendTime(String sendTime){
        this. sendTime = sendTime;
    }
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private String clickUrl;

    public String getClickUrl(){
        return clickUrl;
    }

    public void setClickUrl(String clickUrl){
        this. clickUrl = clickUrl;
    }
    
    public MessageResult(){
    }

    public MessageResult(Integer id,String title,String content,String iconUrl,String sendTime,Integer userId,String clickUrl){
        this.id=id;
        this.title=title;
        this.content=content;
        this.iconUrl=iconUrl;
        this.sendTime=sendTime;
        this.userId=userId;
        this.clickUrl=clickUrl;
    }
}