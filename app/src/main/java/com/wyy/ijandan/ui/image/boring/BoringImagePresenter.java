package com.wyy.ijandan.ui.image.boring;

import android.util.Log;

import com.wyy.ijandan.data.ImagePageLoadTask;
import com.wyy.ijandan.data.JandanUrl;
import com.wyy.ijandan.data.model.ImagePage;

/**
 * Created by yayun.wei on 2016/7/19.
 */
public class BoringImagePresenter implements BoringImageContract.BoringImagePresenter,
        ImagePageLoadTask.ImagePageLoadCallback{

    private static final String TAG = "BoringImagePresenter";

    private BoringImageContract.BoringImageView mView;

    public BoringImagePresenter(BoringImageContract.BoringImageView view) {
        this.mView = view;
    }

    @Override
    public void loadImagePage(int pageNum) {
        mView.onDataLoadPre();
        String url = JandanUrl.BORING_IMAGE;
        if (pageNum >= 0) {
            url = JandanUrl.boringImage(pageNum);
        }
        new ImagePageLoadTask(url, this).excute();
    }

    @Override
    public void onLoadSuccess(ImagePage imagePage) {
        Log.d(TAG, "onLoadSuccess");
        mView.onDataLoaded(imagePage);
    }

    @Override
    public void onLoadFailed(int errCode, String errMsg) {
        Log.d(TAG, "onLoadFailed errCode = " + errCode + " errMsg = " + errMsg);
        mView.onDataLoadError(errMsg);
    }
}
