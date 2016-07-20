package com.wyy.ijandan.ui.image;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wyy.ijandan.R;
import com.wyy.ijandan.data.model.ImageFeed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yayun.wei on 2016/7/18.
 */
public class ImageFeedAdapter extends RecyclerView.Adapter<ImageFeedViewHolder>{

    private List<ImageFeed> mImageFeedList = new ArrayList<>();

    public void setDataAndNotify(List<ImageFeed> imageFeedList) {
        if (imageFeedList != null) {
            mImageFeedList.clear();
            mImageFeedList.addAll(imageFeedList);
            notifyDataSetChanged();
        }
    }

    @Override
    public ImageFeedViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.image_feed_item, viewGroup, false);
        ImageFeedViewHolder viewHolder = new ImageFeedViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageFeedViewHolder imageFeedViewHolder, int i) {
        imageFeedViewHolder.bindImageFeed(mImageFeedList.get(i));
    }

    @Override
    public int getItemCount() {
        return mImageFeedList.size();
    }

    @Override
    public void onViewRecycled(ImageFeedViewHolder holder) {
        super.onViewRecycled(holder);
    }
}
