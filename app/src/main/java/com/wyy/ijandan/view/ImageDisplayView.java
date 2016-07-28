package com.wyy.ijandan.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.net.Uri;
import android.util.AttributeSet;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/**
 * Created by yayun.wei on 2016/7/28.
 */
public class ImageDisplayView extends SubsamplingScaleImageView{

    public static final float RATIO_THRESHOLD = 9f / 16f;

    public ImageDisplayView(Context context, AttributeSet attr) {
        super(context, attr);
        init();
    }

    public ImageDisplayView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setMaxScale(3f);
        setDoubleTapZoomScale(3f);
        setMinScale(1f);
    }

    public void setImageBitmap(Bitmap bitmap) {
        setImage(ImageSource.bitmap(bitmap));
    }

    public void setImageUri(Uri uri) {
        setImage(ImageSource.uri(uri));
    }

    @Override
    protected void onReady() {
        float ratio = (float)getSWidth() / (float)getSHeight();
        if (ratio < RATIO_THRESHOLD) {
            setMinimumScaleType(SCALE_TYPE_CENTER_CROP);
            setScaleAndCenter(1f, new PointF(0f, 0f));
        }
    }
}
