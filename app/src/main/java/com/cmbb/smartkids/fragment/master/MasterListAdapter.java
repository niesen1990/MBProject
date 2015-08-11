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
public class MasterListAdapter extends ContentAdapterBase<MasterTypeModel> {
    private Context mContext;
    private boolean isCity;

    MasterTypeListViewHolder.OnTypeClickListener mOnTypeClickListener;


    public MasterListAdapter(Context mContext, DataController<MasterTypeModel> mDataController, boolean need, MasterTypeListViewHolder.OnTypeClickListener mOnTypeClickListener) {
        super(mContext, mDataController);
        this.mContext = mContext;
        this.mOnTypeClickListener = mOnTypeClickListener;
        moreFlag = true;
        // 设置HeaderView
        setNeedHeadView(need);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType) {
        return MasterTypeListViewHolder.create(mContext, parent, mOnTypeClickListener);
    }


    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MasterTypeListViewHolder) holder).onBindViewHolder(mContext, mDataController.getData(position));
    }

}
