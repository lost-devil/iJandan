package com.wyy.ijandan.ui.image;

import com.wyy.ijandan.data.model.ImagePage;

/**
 * Created by yayun.wei on 2016/7/18.
 */
public interface ImagePageContract {

    public interface ImagePageView {

        void onDataLoadPre();

        void onDataLoaded(ImagePage imagePage);

        void onDataLoadError(String errMsg);
    }

    public interface ImagePagePresenter {

        void loadImagePage(int pageNum);

        void loadNewPage();

        void loadOldPage();
    }
}
