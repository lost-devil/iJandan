package com.wyy.ijandan.data.model;

import java.util.List;

/**
 * Created by yayun.wei on 2016/7/8.
 */
public class ImageFeed {

    public int id;

    public String author;

    public String time;

    public List<ImageFeedPic> pics;

    public int ooCount;

    public int xxCount;

    public int tucaoCount;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(id).append("\n");
        sb.append("author = ").append(author).append("\n");
        sb.append("time = ").append(time).append("\n");
        sb.append("ooCount = ").append(ooCount).append("\n");
        sb.append("xxCount = ").append(xxCount).append("\n");
        sb.append("tucaoCount = ").append(tucaoCount).append("\n");
        if (pics != null && pics.size() > 0) {
            sb.append("pics size = ").append(pics.size()).append("\n");
            for (ImageFeedPic pic : pics) {
                sb.append(pic.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
