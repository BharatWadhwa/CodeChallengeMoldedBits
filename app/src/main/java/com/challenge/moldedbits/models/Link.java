package com.challenge.moldedbits.models;

/**
 * Created by bharat on 03/01/16.
 */
public class Link {
    int pos;
    int len;
    String url;
    String text;
    int amended_len;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAmended_len() {
        return amended_len;
    }

    public void setAmended_len(int amended_len) {
        this.amended_len = amended_len;
    }
}
