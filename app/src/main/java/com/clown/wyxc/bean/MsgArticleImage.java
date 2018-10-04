package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by CZP on 2016/7/27.
 */
public class MsgArticleImage extends Message {

    @Expose
    private int ImageId;
    @Expose
    private String PathFile;
    @Expose
    private String OrgFileName;

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getPathFile() {
        return PathFile;
    }

    public void setPathFile(String pathFile) {
        PathFile = pathFile;
    }

    public String getOrgFileName() {
        return OrgFileName;
    }

    public void setOrgFileName(String orgFileName) {
        OrgFileName = orgFileName;
    }
}
