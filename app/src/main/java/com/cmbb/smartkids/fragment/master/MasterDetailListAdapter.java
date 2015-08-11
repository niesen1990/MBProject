package com.cmbb.smartkids.fragment.master;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.mengrecyclerview.adapter.ContentAdapterBase;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:59
 */
public class MasterDetailListAdapter extends ContentAdapterBase<MasterDetailModel> {
    private Context mContext;
    private boolean isCity;

    public MasterDetailListAdapter(Context mContext, DataController<MasterDetailModel> mDataController, boolean need) {
        super(mContext, mDataController);
        this.mContext = mContext;
        moreFlag = true;
        // 设置HeaderView
        setNeedHeadView(need);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType) {
        return MasterDetailListViewHolder.create(mContext, parent);
    }


    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MasterDetailListViewHolder) holder).onBindViewHolder(mContext, mDataController.getData(position));
    }

}
