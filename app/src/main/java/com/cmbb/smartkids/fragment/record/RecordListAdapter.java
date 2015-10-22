package com.cmbb.smartkids.fragment.record;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.fragment.replay.ReplayHeadViewHolder;
import com.cmbb.smartkids.fragment.replay.ReplayListViewHolder;
import com.cmbb.smartkids.fragment.replay.ReplayModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.mengrecyclerview.adapter.ContentAdapterBase;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:59
 */
public class RecordListAdapter extends ContentAdapterBase<RecordModel.ContextEntity> {
    private Context mContext;


    public RecordListAdapter(Context mContext, DataController<RecordModel.ContextEntity> mDataController, boolean need) {
        super(mContext, mDataController);
        this.mContext = mContext;
        moreFlag = true;
        // 设置HeaderView
        setNeedHeadView(need);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType) {
        return RecordListViewHolder.create(mContext, parent);
    }


    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RecordListViewHolder) holder).onBindViewHolder(mContext, mDataController.getData(position));
    }


}
