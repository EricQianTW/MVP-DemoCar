package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class Users extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String phone;

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this. phone = phone;
    }
    
    @Expose
    private String headPic;

    public String getHeadPic(){
        return headPic;
    }

    public void setHeadPic(String headPic){
        this. headPic = headPic;
    }
    
    @Expose
    private String password;

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this. password = password;
    }
    
    @Expose
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this. name = name;
    }
    
    @Expose
    private String email;

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this. email = email;
    }
    
    @Expose
    private String address;

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this. address = address;
    }
    
    @Expose
    private String job;

    public String getJob(){
        return job;
    }

    public void setJob(String job){
        this. job = job;
    }
    
    @Expose
    private String adcode;

    public String getAdcode(){
        return adcode;
    }

    public void setAdcode(String adcode){
        this. adcode = adcode;
    }
    
    @Expose
    private String code;

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this. code = code;
    }
    
    @Expose
    private String codeTime;

    public String getCodeTime(){
        return codeTime;
    }

    public void setCodeTime(String codeTime){
        this. codeTime = codeTime;
    }
    
    @Expose
    private Integer state;

    public Integer getState(){
        return state;
    }

    public void setState(Integer state){
        this. state = state;
    }
    
    @Expose
    private Integer isReceiveNotice;

    public Integer getIsReceiveNotice(){
        return isReceiveNotice;
    }

    public void setIsReceiveNotice(Integer isReceiveNotice){
        this. isReceiveNotice = isReceiveNotice;
    }
    
    @Expose
    private String payPassword;

    public String getPayPassword(){
        return payPassword;
    }

    public void setPayPassword(String payPassword){
        this. payPassword = payPassword;
    }
    
    public Users(){
    }

    public Users(Integer id,String phone,String headPic,String password,String name,String email,String address,String job,String adcode,String code,String codeTime,Integer state,Integer isReceiveNotice,String payPassword){
        this.id=id;
        this.phone=phone;
        this.headPic=headPic;
        this.password=password;
        this.name=name;
        this.email=email;
        this.address=address;
        this.job=job;
        this.adcode=adcode;
        this.code=code;
        this.codeTime=codeTime;
        this.state=state;
        this.isReceiveNotice=isReceiveNotice;
        this.payPassword=payPassword;
    }
}