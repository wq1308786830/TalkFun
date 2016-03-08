package com.russell.talkfun;

/**
 * Created by qi on 2015/9/19.
 */
public class ListData {
    private String content;
    public static final int SEND = 1;
    public static final int RECEIVE = 2;
    private int flag;

    public ListData(String content, int flag){
        setContent(content);
        setFlag(flag);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
