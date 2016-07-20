package com.wyy.ijandan.data;

import android.util.Log;

import com.wyy.ijandan.data.loader.JsoupImagePageLoader;
import com.wyy.ijandan.data.model.ImagePage;

/**
 * Created by yayun.wei on 2016/7/18.
 */
public class ImagePageLoadTask extends Thread{

    private String mUrl;

    private ImagePageLoadCallback mCallback;

    public ImagePageLoadTask(String url) {
        this.mUrl = url;
    }

    public ImagePageLoadTask(String url, ImagePageLoadCallback callback) {
        this(url);
        this.mCallback = callback;
    }

    @Override
    public void run() {
        try {
            ImagePage imagePage = new JsoupImagePageLoader().load(mUrl);
            if (imagePage != null) {
                notifyCallbackSuccess(imagePage);
            } else {
                notifyCallbackFailed(0, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            notifyCallbackFailed(-1, "Exception");
        }
    }

    public void excute() {
        this.start();
    }

    private void notifyCallbackSuccess(ImagePage imagePage) {
        if (mCallback != null) {
            mCallback.onLoadSuccess(imagePage);
        }
    }

    private void notifyCallbackFailed(int errCode, String errMsg) {
        if (mCallback != null) {
            mCallback.onLoadFailed(errCode, errMsg);
        }
    }

    public interface ImagePageLoadCallback{

        void onLoadSuccess(ImagePage imagePage);

        void onLoadFailed(int errCode, String errMsg);
    }
}
