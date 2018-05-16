package com.ch.pojo;

/**
 * Created by Cxy on 2018/4/17.
 */
public class Notice {
    private int type;
    private String title;
    private String content;
    private String pubUserId;
    private String recUserId;

    public Notice() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubUserId() {
        return pubUserId;
    }
    public void setPubUserId(String pubUserId) {
        this.pubUserId = pubUserId;
    }

    public String getRecUserId() {
        return recUserId;
    }

    public void setRecUserId(String recUserId) {
        this.recUserId = recUserId;
    }
}
