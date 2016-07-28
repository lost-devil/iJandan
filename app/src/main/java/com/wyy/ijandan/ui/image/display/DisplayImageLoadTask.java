package com.wyy.ijandan.ui.image.display;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.wyy.ijandan.view.ImageDisplayView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * Created by yayun.wei on 2016/7/28.
 */
public class DisplayImageLoadTask extends AsyncTask<Void, Void, Uri>{

    private static final String TAG = "DisplayImageLoadTask";

    private WeakReference<Context> mContextRef;
    private WeakReference<ImageDisplayView> mDisplayViewRef;
    private String mUrl;

    public DisplayImageLoadTask(ImageDisplayView displayView, String url) {
        mContextRef = new WeakReference<Context>(displayView.getContext());
        mDisplayViewRef = new WeakReference<ImageDisplayView>(displayView);
        mUrl = url;
    }

    @Override
    protected Uri doInBackground(Void... params) {
        Context context = mContextRef.get();
        if (context != null) {
            Glide.with(context)
                    .load(mUrl)
                    .downloadOnly(new SimpleTarget<File>() {
                        @Override
                        public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {

                        }
                    });
        }
        return null;
    }

    @Override
    protected void onPostExecute(Uri uri) {
        super.onPostExecute(uri);
    }

    public void compatExecute(AsyncTask<Void, Void, ?> asyncTask) {
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                Field executorField = AsyncTask.class.getField("THREAD_POOL_EXECUTOR");
                Executor executor = (Executor)executorField.get(null);
                Method executeMethod = AsyncTask.class.getMethod("executeOnExecutor", Executor.class, Object[].class);
                executeMethod.invoke(asyncTask, executor, null);
                return;
            } catch (Exception e) {
                Log.i(TAG, "Failed to execute AsyncTask on thread pool executor, falling back to single threaded executor", e);
            }
        }
        execute();
    }
}
