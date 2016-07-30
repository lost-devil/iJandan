package com.wyy.ijandan.ui.image;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wyy.ijandan.R;
import com.wyy.ijandan.data.model.ImagePage;

/**
 * Created by yayun.wei on 2016/7/18.
 */
public class ImagePageFragment extends Fragment implements ImagePageContract.ImagePageView,
        View.OnClickListener{

    private static final String TAG = "ImagePageFragment";

    private ImagePageContract.ImagePagePresenter mPresenter;

    private RecyclerView mImageRecyView;
    private ImageFeedAdapter mImageAdapter;

    private TextView mPageNumView;

    public ImagePageFragment() {
        mPresenter = new ImagePagePresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.image_page_fragment, null);
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        mImageRecyView = (RecyclerView)rootView.findViewById(R.id.image_list);
        mImageRecyView.setHasFixedSize(true);
        mImageRecyView.setLayoutManager(new LinearLayoutManager(mImageRecyView.getContext()));
        mImageAdapter = new ImageFeedAdapter();
        mImageRecyView.setAdapter(mImageAdapter);
        mPageNumView = (TextView)rootView.findViewById(R.id.page_num);
        rootView.findViewById(R.id.newer_page).setOnClickListener(this);
        rootView.findViewById(R.id.older_page).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.loadImagePage(-1);
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
                mImageRecyView.scrollToPosition(0);
                mPageNumView.setText(imagePage.pageNum + "");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newer_page:
                mPresenter.loadNewPage();
                break;
            case R.id.older_page:
                mPresenter.loadOldPage();
                break;
        }
    }
}
