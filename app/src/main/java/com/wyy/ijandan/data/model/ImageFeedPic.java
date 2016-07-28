package com.wyy.ijandan.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.wyy.ijandan.utils.ImageUtil;

/**
 * Created by yayun.wei on 2016/7/13.
 */
public class ImageFeedPic implements Parcelable{

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

    public ImageFeedPic() {};

    protected ImageFeedPic(Parcel in) {
        content = in.readString();
        coverUrl = in.readString();
        srcUrl = in.readString();
        largeUrl = in.readString();
    }

    public static final Parcelable.Creator<ImageFeedPic> CREATOR = new Parcelable.Creator<ImageFeedPic>() {
        @Override
        public ImageFeedPic createFromParcel(Parcel in) {
            return new ImageFeedPic(in);
        }

        @Override
        public ImageFeedPic[] newArray(int size) {
            return new ImageFeedPic[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(coverUrl);
        dest.writeString(srcUrl);
        dest.writeString(largeUrl);
    }
}
