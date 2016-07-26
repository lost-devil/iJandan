package com.wyy.ijandan.ui.image;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyy.ijandan.R;
import com.wyy.ijandan.data.model.ImageFeed;
import com.wyy.ijandan.data.model.ImageFeedPic;

/**
 * Created by yayun.wei on 2016/7/19.
 */
public class ImageFeedViewHolder extends RecyclerView.ViewHolder
        implements ImageFeedThumbAdapter.OnThumbClickListener, ViewPager.OnPageChangeListener{

    public TextView mAuthorView;
    public TextView mTimeView;
    public TextView mIdView;
    public TextView mOOCountView;
    public TextView mXXCountView;

    public ViewPager mImagePager;

    public RecyclerView mThumbListView;
    public ImageFeedThumbAdapter mThumbAdapter;

    public ImageFeedViewHolder(View itemView) {
        super(itemView);
        mAuthorView = (TextView)itemView.findViewById(R.id.feed_author);
        mTimeView = (TextView)itemView.findViewById(R.id.feed_time);
        mIdView = (TextView)itemView.findViewById(R.id.feed_id);
        mOOCountView = (TextView)itemView.findViewById(R.id.feed_oo_count);
        mXXCountView = (TextView)itemView.findViewById(R.id.feed_xx_count);
        mThumbListView = (RecyclerView)itemView.findViewById(R.id.thumb_list);
        mThumbListView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mThumbListView.setLayoutManager(layoutManager);
        mThumbAdapter = new ImageFeedThumbAdapter();
        mThumbAdapter.setOnThumbClickListener(this);
        mThumbListView.setAdapter(mThumbAdapter);
        mImagePager = (ViewPager)itemView.findViewById(R.id.image_pager);
        mImagePager.addOnPageChangeListener(this);
    }

    public void bindImageFeed(ImageFeed imageFeed) {
        if (!TextUtils.isEmpty(imageFeed.author)) {
            mAuthorView.setText(imageFeed.author);
        }
        if (!TextUtils.isEmpty(imageFeed.time)) {
            mTimeView.setText(imageFeed.time);
        }
        mIdView.setText("#" + imageFeed.id);
        mOOCountView.setText("OO[" + imageFeed.ooCount + "]");
        mXXCountView.setText("XX[" + imageFeed.xxCount + "]");
        if (imageFeed.pics.size() > 1) {
            mThumbListView.setVisibility(View.VISIBLE);
            mThumbAdapter.setDataAndNotify(imageFeed.pics);
        } else {
            mThumbListView.setVisibility(View.GONE);
        }
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter();
        imagePagerAdapter.setDataAndNotify(imageFeed.pics);
        mImagePager.setAdapter(imagePagerAdapter);
        mThumbAdapter.setSelected(0);
    }

    @Override
    public void onThumbClick(ImageFeedPic pic, int index) {
        mImagePager.setCurrentItem(index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mThumbAdapter.setSelected(position);
        mThumbListView.scrollToPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
