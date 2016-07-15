package com.wyy.ijandan.data.htmlparser;

/**
 * Created by yayun.wei on 2016/7/14.
 */
public abstract class Parser<T> {

    protected T mParent;

    public Parser(T parent) {
        this.mParent = parent;
    }

    public abstract T getParent();
}
