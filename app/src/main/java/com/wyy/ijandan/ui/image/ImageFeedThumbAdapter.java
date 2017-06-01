package com.wyy.ijandan.ui.image;

import android.support.v7.widget.RecyclerView;
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
public class ImageFeedThumbAdapter extends RecyclerView.Adapter<ImageFeedThumbAdapter.ViewHolder>{

    private List<ImageFeedPic> mImagePicList = new ArrayList<>();

    private OnThumbClickListener mOnThumbClickListener;

    private int mSelectedPosition;

    public void setDataAndNotify(List<ImageFeedPic> pics) {
        if (pics != null) {
            mImagePicList.clear();
            mImagePicList.addAll(pics);
            notifyDataSetChanged();
        }
    }

    public void setOnThumbClickListener(OnThumbClickListener onThumbClickListener) {
        this.mOnThumbClickListener = onThumbClickListener;
    }

    public void setSelected(int position) {
        this.mSelectedPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_image_thumb_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ImageFeedPic pic = mImagePicList.get(position);
        Glide.with(holder.mThumbView.getContext())
                .load(pic.coverUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(holder.mThumbView);
        holder.mThumbView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnThumbClickListener != null) {
                    mOnThumbClickListener.onThumbClick(pic, position);
                }
            }
        });
        if (position == mSelectedPosition) {
            holder.mCover.setVisibility(View.VISIBLE);
        } else {
            holder.mCover.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mImagePicList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mThumbView;
        View mCover;
        
        public ViewHolder(View itemView) {
            super(itemView);
            mThumbView = (ImageView)itemView.findViewById(R.id.thumb_image);
            mCover = itemView.findViewById(R.id.thumb_select_cover);
        }
    }

    public interface OnThumbClickListener{
        void onThumbClick(ImageFeedPic pic, int index);
    }
}
