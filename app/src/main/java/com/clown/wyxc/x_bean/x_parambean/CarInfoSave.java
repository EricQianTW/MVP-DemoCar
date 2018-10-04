package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;
public class CarInfoSave extends Message{ 
    
    @Expose
    private Integer mycarsid;

    public Integer getMycarsid(){
        return mycarsid;
    }

    public void setMycarsid(Integer mycarsid){
        this. mycarsid = mycarsid;
    }
    
    @Expose
    private Integer userid;

    public Integer getUserid(){
        return userid;
    }

    public void setUserid(Integer userid){
        this. userid = userid;
    }
    
    @Expose
    private Integer cxid;

    public Integer getCxid(){
        return cxid;
    }

    public void setCxid(Integer cxid){
        this. cxid = cxid;
    }
    
    @Expose
    private Integer carid;

    public Integer getCarid(){
        return carid;
    }

    public void setCarid(Integer carid){
        this. carid = carid;
    }
    
    @Expose
    private Integer km;

    public Integer getKm(){
        return km;
    }

    public void setKm(Integer km){
        this. km = km;
    }
    
    @Expose
    private String carno;

    public String getCarno(){
        return carno;
    }

    public void setCarno(String carno){
        this. carno = carno;
    }
    
    @Expose
    private String caryear;

    public String getCaryear(){
        return caryear;
    }

    public void setCaryear(String caryear){
        this. caryear = caryear;
    }
    
    @Expose
    private String engineno;

    public String getEngineno(){
        return engineno;
    }

    public void setEngineno(String engineno){
        this. engineno = engineno;
    }
    
    public CarInfoSave(){
    }

    public CarInfoSave(Integer mycarsid,Integer userid,Integer cxid,Integer carid,Integer km,String carno,String caryear,String engineno){
        this.mycarsid=mycarsid;
        this.userid=userid;
        this.cxid=cxid;
        this.carid=carid;
        this.km=km;
        this.carno=carno;
        this.caryear=caryear;
        this.engineno=engineno;
    }
}