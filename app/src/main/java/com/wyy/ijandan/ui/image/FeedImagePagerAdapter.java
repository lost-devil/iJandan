package com.wyy.ijandan.ui.image;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyy.ijandan.R;
import com.wyy.ijandan.data.model.ImageFeedPic;
import com.wyy.ijandan.ui.image.display.ImageDisplayActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yayun.wei on 2016/7/26.
 */
public class FeedImagePagerAdapter extends PagerAdapter{

    private ArrayList<ImageFeedPic> mImagePicList = new ArrayList<>();

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
        final ImageView imageView = (ImageView)view.findViewById(R.id.feed_image);
        Button playBtn = (Button)view.findViewById(R.id.gif_play_btn);
        container.addView(view);
        final ImageFeedPic pic = mImagePicList.get(position);
        loadImage(imageView, pic.srcUrl);
        view.setTag(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int)v.getTag();
                ImageDisplayActivity.show(v.getContext(), mImagePicList, position);
            }
        });
        if (pic.isGif()) {
            playBtn.setVisibility(View.VISIBLE);
            playBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setVisibility(View.GONE);
                    loadGif(imageView, pic.srcUrl);
                }
            });
        } else {
            playBtn.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ImageView)(((View)object).findViewById(R.id.feed_image))).setImageDrawable(null);
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

    private void loadGif(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .into(imageView);
    }
}
