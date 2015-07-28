package com.cmbb.smartkids.fragment.test;

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
public class EntryListAdapter extends ContentAdapterBase<HomeSameAge> {
    private Context mContext;

    public EntryListAdapter(Context mContext, DataController<HomeSameAge> mDataController) {
        super(mContext, mDataController);
        this.mContext = mContext;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType) {
        return EntryListViewHolder.create(mContext, parent);
    }


    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((EntryListViewHolder) holder).onBindViewHolder(mContext, mDataController.getData(position));
    }
}
