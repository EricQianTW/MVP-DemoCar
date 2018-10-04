package com.clown.wyxc.bean.realm;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by eric on 2016/8/4.
 */
public class RSearch extends RealmObject {

    private String searchWord;
    private Date searchTime;

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public Date getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Date searchTime) {
        this.searchTime = searchTime;
    }
}
