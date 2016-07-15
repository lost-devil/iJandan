package com.wyy.ijandan.data.model;

import android.text.TextUtils;

import com.wyy.ijandan.utils.ImageUtil;

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

    /**
     * 图片是否是Gif
     * @return
     */
    public boolean isGif() {
        return ImageUtil.isGif(srcUrl);
    }

    /**
     * 是否含图片
     * @return
     */
    public boolean hasImage() {
        return !TextUtils.isEmpty(srcUrl);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (content != null) {
            sb.append("content = ").append(content).append("\n");
        }
        sb.append("coverUrl = ").append(coverUrl).append("\n");
        sb.append("srcUrl = ").append(srcUrl).append("\n");
        sb.append("largeUrl = ").append(largeUrl);
        return sb.toString();
    }
}
