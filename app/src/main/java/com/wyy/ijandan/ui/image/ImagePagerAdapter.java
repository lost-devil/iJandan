package com.wyy.ijandan.ui.image;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyy.ijandan.R;
import com.wyy.ijandan.data.model.ImageFeedPic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yayun.wei on 2016/7/26.
 */
public class ImagePagerAdapter extends PagerAdapter{

    private List<ImageFeedPic> mImagePicList = new ArrayList<>();

    public void setDataAndNotify(List<ImageFeedPic> pics) {
        if (pics != null) {
            mImagePicList.clear();
            mImagePicList.addAll(pics);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mImagePicList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.feed_image_pager_item, null);
        ImageView imageView = (ImageView)view.findViewById(R.id.feed_image);
        container.addView(view);
        loadImage(imageView, mImagePicList.get(position).srcUrl);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    private void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(imageView);
    }
}
