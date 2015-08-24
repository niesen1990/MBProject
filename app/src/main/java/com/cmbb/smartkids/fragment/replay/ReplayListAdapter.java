package com.cmbb.smartkids.fragment.replay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.mengrecyclerview.adapter.ContentAdapterBase;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:59
 */
public class ReplayListAdapter extends ContentAdapterBase<ReplayModel> {
    private Context mContext;
    private LinearLayout mHeadView;
    private PostModel mPostModel;
    DataController<ReplayModel> mDataController;

    private ReplayListViewHolder.OnReplayItemClickListener mOnReplayItemClickListener;


    public ReplayListAdapter(Context mContext, PostModel postDetail, DataController<ReplayModel> mDataController, boolean need, LinearLayout headView, ReplayListViewHolder.OnReplayItemClickListener onReplayItemClickListener) {
        super(mContext, mDataController);
        this.mPostModel = postDetail;
        this.mDataController = mDataController;
        this.mOnReplayItemClickListener = onReplayItemClickListener;
        this.mContext = mContext;
        this.mHeadView = headView;
        // 设置HeaderView
        setNeedHeadView(need);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType) {
        return ReplayListViewHolder.create(mContext, parent, mOnReplayItemClickListener);
    }


    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ReplayListViewHolder) holder).onBindViewHolder(mContext, mPostModel, mDataController, mDataController.getData(position), position);
    }

    // 创建HeaderView
    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderHolder(ViewGroup parent) {
        return ReplayHeadViewHolder.create(mContext, parent, mHeadView);
    }

    @Override
    protected void onBindCustomHeaderHolder(RecyclerView.ViewHolder holder) {
    }

    @Override
    protected void onLoadFinishedHeadBind(RecyclerView.ViewHolder holder) {
        super.onLoadFinishedHeadBind(holder);
    }

}
