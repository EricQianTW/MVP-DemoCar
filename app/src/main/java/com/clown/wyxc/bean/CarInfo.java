package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by eric_shenn on 2017/4/10.
 */

public class CarInfo extends Message {
    @Expose
    private String carPic;
    @Expose
    private String carName;
    @Expose
    private String carInfo;
    @Expose
    private String carTime;
    @Expose
    private String carDistance;

    public CarInfo(String carPic, String carName, String carInfo, String carTime, String carDistance) {
        this.carPic = carPic;
        this.carName = carName;
        this.carInfo = carInfo;
        this.carTime = carTime;
        this.carDistance = carDistance;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    public String getCarTime() {
        return carTime;
    }

    public void setCarTime(String carTime) {
        this.carTime = carTime;
    }

    public String getCarDistance() {
        return carDistance;
    }

    public void setCarDistance(String carDistance) {
        this.carDistance = carDistance;
    }

    public String getCarPic() {
        return carPic;
    }

    public void setCarPic(String carPic) {
        this.carPic = carPic;
    }
}
