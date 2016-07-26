package com.wyy.ijandan.ui.image.boring;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wyy.ijandan.R;
import com.wyy.ijandan.data.model.ImagePage;
import com.wyy.ijandan.ui.image.ImageFeedAdapter;

/**
 * Created by yayun.wei on 2016/7/18.
 */
public class BoringImageFragment extends Fragment implements BoringImageContract.BoringImageView{

    private static final String TAG = "BoringImageFragment";

    private BoringImageContract.BoringImagePresenter mPresenter;

    private RecyclerView mImageRecyView;
    private ImageFeedAdapter mImageAdapter;

    public BoringImageFragment() {
        mPresenter = new BoringImagePresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.boring_image_fragment, null);
        mImageRecyView = (RecyclerView)rootView.findViewById(R.id.image_list);
        mImageRecyView.setHasFixedSize(true);
        mImageRecyView.setLayoutManager(new LinearLayoutManager(mImageRecyView.getContext()));
        mImageAdapter = new ImageFeedAdapter();
        mImageRecyView.setAdapter(mImageAdapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.loadImagePage(9405);
    }

    @Override
    public void onDataLoadPre() {
        //TODO
    }

    @Override
    public void onDataLoaded(final ImagePage imagePage) {
        Log.d(TAG, "onDataLoaded pageNum = " + imagePage.pageNum);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mImageAdapter.setDataAndNotify(imagePage.feeds);
            }
        });
    }

    @Override
    public void onDataLoadError(final String errMsg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), errMsg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
