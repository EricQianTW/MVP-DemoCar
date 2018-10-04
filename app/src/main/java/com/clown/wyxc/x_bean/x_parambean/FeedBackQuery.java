package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class FeedBackQuery extends Message{
    
    @Expose
    private List<String> feedBackPicList;

    public List<String> getFeedBackPicList(){
        return feedBackPicList;
    }

    public void setFeedBackPicList(List<String> feedBackPicList){
        this. feedBackPicList = feedBackPicList;
    }
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private Integer uId;

    public Integer getUId(){
        return uId;
    }

    public void setUId(Integer uId){
        this. uId = uId;
    }
    
    @Expose
    private String contents;

    public String getContents(){
        return contents;
    }

    public void setContents(String contents){
        this. contents = contents;
    }
    
    @Expose
    private String insDateTime;

    public String getInsDateTime(){
        return insDateTime;
    }

    public void setInsDateTime(String insDateTime){
        this. insDateTime = insDateTime;
    }
    
    public FeedBackQuery(){
    }

    public FeedBackQuery(List<String> feedBackPicList,Integer id,Integer uId,String contents,String insDateTime){
        this.feedBackPicList=feedBackPicList;
        this.id=id;
        this.uId=uId;
        this.contents=contents;
        this.insDateTime=insDateTime;
    }
}