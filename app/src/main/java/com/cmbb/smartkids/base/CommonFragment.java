package com.cmbb.smartkids.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cmbb.smartkids.mengrecyclerview.R;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

import java.util.List;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/6/30 11:33
 */
public abstract class CommonFragment<T> extends ContentFragmentBase {

    protected RecyclerView mRecyclerView;
    protected ViewGroup mRoot, mPagerPage, mLoadingPage;
    protected ContentLoadingProgressBar mLoadingBar;

    protected TextView mLoadingTips;
    protected Button mLoadingRetry;


    protected SwipeRefreshLayout mSwipeRefresh;
    protected RecyclerView.Adapter mAdapter;
    protected DataController<T> mDataController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_content_mengrecycler, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_content);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        //mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        // mRecyclerView.addItemDecoration(new Div);
        mPagerPage = (ViewGroup) rootView.findViewById(R.id.pager_page);
        mLoadingPage = (ViewGroup) rootView.findViewById(R.id.loading_page);
        mLoadingBar = (ContentLoadingProgressBar) mLoadingPage.findViewById(R.id.loading_bar);
        mLoadingRetry = (Button) mLoadingPage.findViewById(R.id.loading_refresh);
        mLoadingTips = (TextView) mLoadingPage.findViewById(R.id.loading_tips);
        mSwipeRefresh = (SwipeRefreshLayout) mPagerPage;
        mSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mRoot = rootView;
        if (enableRefresh()) {
            mSwipeRefresh.setOnRefreshListener(this);
        } else {
            mSwipeRefresh.setEnabled(false);
        }
        return rootView;
    }

    public void setHorizontalLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mDataController = onGenerateDataController();
        mAdapter = onGenerateAdapter(mDataController);
        mDataController.addUIRespondent(this);
        mDataController.addAdapterRespondent(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected abstract DataController<T> onGenerateDataController();

    protected abstract RecyclerView.Adapter onGenerateAdapter(DataController<T> controller);

    protected abstract boolean enableRefresh();

    public SwipeRefreshLayout getmSwipeRefresh() {
        return mSwipeRefresh;
    }

    public void setmSwipeRefresh(SwipeRefreshLayout mSwipeRefresh) {
        this.mSwipeRefresh = mSwipeRefresh;
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDataInsert(int position) {
        super.onDataInsert(position);
        mAdapter.notifyItemInserted(position);
    }

    @Override
    public void onDataRemove(int position) {
        super.onDataRemove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onRefreshingStart() {
        super.onRefreshingStart();
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void onRefreshDone(Exception e, List data) {
        if (e == null) {
            if (data == null || data.size() == 0) {
                Toast.makeText(getActivity(), "无数据加载", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "已加载" + data.size() + " 数据", Toast.LENGTH_SHORT).show();
            }
        } else {
            e.printStackTrace();
            Toast.makeText(getActivity(), "网络出现问题", Toast.LENGTH_SHORT).show();
        }
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onInitializeDone(Exception e, List data) {
        super.onInitializeDone(e, data);
        mLoadingBar.hide();
        if (e == null) {
            mRoot.bringChildToFront(mPagerPage);
        } else {
            mLoadingRetry.setVisibility(View.VISIBLE);
            mLoadingTips.setVisibility(View.VISIBLE);
            mLoadingRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLoadingRetry.setVisibility(View.GONE);
                    mLoadingTips.setVisibility(View.GONE);
                    mDataController.initialize();
                }
            });
            mLoadingTips.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mDataController.refresh();
    }
}
