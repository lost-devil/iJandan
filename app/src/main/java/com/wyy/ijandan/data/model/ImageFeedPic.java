package com.wyy.ijandan.data.model;

/**
 * Created by yayun.wei on 2016/7/13.
 */
public class ImageFeedPic {

    /**
     * 图片文字内容
     */
    public String content;

    /**
     * 图片封面url（列表默认展示的）
     */
    public String coverUrl;

    /**
     * 图片定宽大图url（静态图片的话与coverUrl一样）
     */
    public String srcUrl;

    /**
     * 图片大图url
     */
    public String largeUrl;

    public boolean isGif() {
        return false;
    }
}
