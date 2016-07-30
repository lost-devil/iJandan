package com.wyy.ijandan.ui.image.display;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;

import com.wyy.ijandan.R;
import com.wyy.ijandan.data.model.ImageFeedPic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yayun.wei on 2016/7/28.
 */
public class ImageDisplayActivity extends AppCompatActivity
        implements OnPageChangeListener{

    private static final String TAG = "ImageDisplayActivity";

    public static final String EXTRA_IMAGE_LIST = "image_list";
    public static final String EXTRA_DISPLAY_POSITION = "display_position";

    private List<ImageFeedPic> mImagePicList;

    private int mCurPosition;

    private ViewPager mViewPager;
    private ImageDisplayPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_display_activity);
        getExtras();
        mViewPager = (ViewPager)findViewById(R.id.image_display_pager);
        mAdapter = new ImageDisplayPagerAdapter(this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
        mAdapter.setDataAndNotify(mImagePicList);
        mViewPager.setCurrentItem(mCurPosition);
    }

    private void getExtras() {
        Intent intent = getIntent();
        if (intent != null) {
            mImagePicList = intent.getParcelableArrayListExtra(EXTRA_IMAGE_LIST);
            mCurPosition = intent.getIntExtra(EXTRA_DISPLAY_POSITION, 0);
        }
        if (mImagePicList == null) {
            mImagePicList = new ArrayList<>();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        mViewPager.removeOnPageChangeListener(this);
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    public static void show(Context context, ArrayList<ImageFeedPic> imageList, int displayPosition) {
        Intent intent = new Intent(context, ImageDisplayActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_IMAGE_LIST, imageList);
        intent.putExtra(EXTRA_DISPLAY_POSITION, displayPosition);
        context.startActivity(intent);
    }
}
