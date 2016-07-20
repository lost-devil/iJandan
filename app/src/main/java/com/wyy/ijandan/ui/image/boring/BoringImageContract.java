package com.wyy.ijandan.ui.image.boring;

import com.wyy.ijandan.data.model.ImagePage;

/**
 * Created by yayun.wei on 2016/7/18.
 */
public interface BoringImageContract {

    public interface BoringImageView {

        void onDataLoadPre();

        void onDataLoaded(ImagePage imagePage);

        void onDataLoadError(String errMsg);
    }

    public interface BoringImagePresenter {

        void loadImagePage(int pageNum);
    }
}
