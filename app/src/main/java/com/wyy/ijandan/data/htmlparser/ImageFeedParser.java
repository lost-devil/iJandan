package com.wyy.ijandan.data.htmlparser;

import com.wyy.ijandan.data.model.ImageFeed;

import java.util.List;

/**
 * Created by yayun.wei on 2016/7/14.
 */
public abstract class ImageFeedParser<T> extends Parser{

    public ImageFeedParser(T parent) {
        super(parent);
    }

    public abstract List<ImageFeed> parseFeeds();

    public abstract ImageFeed parseFeed(T feedNode);
}
