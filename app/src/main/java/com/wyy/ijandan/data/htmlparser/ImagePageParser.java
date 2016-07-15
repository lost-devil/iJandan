package com.wyy.ijandan.data.htmlparser;

import com.wyy.ijandan.data.model.ImagePage;

/**
 * Created by yayun.wei on 2016/7/14.
 */
public abstract class ImagePageParser<T> extends Parser {

    public ImagePageParser(T parent) {
        super(parent);
    }

    public abstract ImagePage parse();
}
