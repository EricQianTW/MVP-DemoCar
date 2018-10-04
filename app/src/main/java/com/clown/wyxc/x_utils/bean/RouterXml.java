package com.clown.wyxc.x_utils.bean;

/**
 * Created by eric_qiantw on 2016/10/26.
 */

public class RouterXml {

    private String host;
    private String path;
    private String activity;

    public RouterXml(){

    }

    public RouterXml(String host, String path, String activity) {
        this.host = host;
        this.path = path;
        this.activity = activity;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
