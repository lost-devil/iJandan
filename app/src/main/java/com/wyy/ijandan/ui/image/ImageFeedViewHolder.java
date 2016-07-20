package com.wyy.ijandan.ui.image;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyy.ijandan.R;
import com.wyy.ijandan.data.model.ImageFeed;
import com.wyy.ijandan.data.model.ImageFeedPic;

/**
 * Created by yayun.wei on 2016/7/19.
 */
public class ImageFeedViewHolder extends RecyclerView.ViewHolder{

    public ImageView mImageView;

    public ImageFeedViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView)itemView.findViewById(R.id.feed_image);
    }

    public void bindImageFeed(ImageFeed imageFeed) {
        String imageUrl = "";
        for (ImageFeedPic pic : imageFeed.pics) {
            if (!TextUtils.isEmpty(pic.srcUrl)) {
                imageUrl = pic.srcUrl;
                break;
            }
        }
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(itemView.getContext())
                    .load(imageUrl)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_launcher)
                    .fitCenter()
                    .into(mImageView);
        }
    }
}
