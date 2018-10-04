package com.clown.wyxc.bean.realm;

import io.realm.RealmObject;

/**
 * Created by JokerEric on 2016/6/29.
 */
public class RAreaList extends RealmObject {

    // 坐标
    private String center;

    // 后缀
    private String prefix;

    // 地址码
    private String adcode;

    // 城市码
    private String citycode;

    // 地区等级
    private String level;

    // 地区名
    private String name;

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
