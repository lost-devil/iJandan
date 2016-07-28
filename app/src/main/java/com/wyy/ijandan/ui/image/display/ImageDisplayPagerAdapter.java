package com.wyy.ijandan.ui.image.display;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wyy.ijandan.R;
import com.wyy.ijandan.data.model.ImageFeedPic;
import com.wyy.ijandan.view.ImageDisplayView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yayun.wei on 2016/7/28.
 */
public class ImageDisplayPagerAdapter extends PagerAdapter implements View.OnClickListener{

    private Activity mActivity;

    private List<ImageFeedPic> mImagePicList = new ArrayList<>();

    public ImageDisplayPagerAdapter(Activity activity) {
        this.mActivity = activity;
    }

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
                .inflate(R.layout.image_display_pager_item, null);
        ImageDisplayView imageDisplayView = (ImageDisplayView)view.findViewById(R.id.image_display_view);
        container.addView(view);
        loadImage(imageDisplayView, mImagePicList.get(position).srcUrl);
        imageDisplayView.setOnClickListener(this);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    private void loadImage(final ImageDisplayView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File file, GlideAnimation<? super File> glideAnimation) {
                        imageView.setImageUri(Uri.fromFile(file));
                    }
                });
    }

    @Override
    public void onClick(View v) {
        mActivity.finish();
    }
}
