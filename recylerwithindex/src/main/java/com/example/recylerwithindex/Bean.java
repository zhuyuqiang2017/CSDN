package com.example.recylerwithindex;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class Bean {
    private String mTag;

    private String mContent;

    public String getTag() {
        return mTag;
    }

    public void setTag(String mTag) {
        this.mTag = mTag;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public Bean(String mTag, String mContent) {

        this.mTag = mTag;
        this.mContent = mContent;
    }
}
