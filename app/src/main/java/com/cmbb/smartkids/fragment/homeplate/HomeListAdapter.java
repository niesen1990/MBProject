package com.cmbb.smartkids.fragment.homeplate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.mengrecyclerview.adapter.ContentAdapterBase;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:59
 */
public class HomeListAdapter extends ContentAdapterBase<PlateModel> {
    private Context mContext;


    public HomeListAdapter(Context mContext, DataController<PlateModel> mDataController, boolean need) {
        super(mContext, mDataController);
        this.mContext = mContext;
        moreFlag = true;
        // 设置HeaderView
        setNeedHeadView(need);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType) {
        return HomeListViewHolder.create(mContext, parent);
    }


    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HomeListViewHolder) holder).onBindViewHolder(mContext, mDataController.getData(position));
    }

    // 创建HeaderView
    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderHolder(ViewGroup parent) {
        return HomeListHeadViewHolder.create(mContext, parent);
    }

    @Override
    protected void onBindCustomHeaderHolder(RecyclerView.ViewHolder holder) {
        ((HomeListHeadViewHolder) holder).onBindViewHolder(mContext);
    }

    @Override
    protected void onLoadFinishedHeadBind(RecyclerView.ViewHolder holder) {
        super.onLoadFinishedHeadBind(holder);
        ((HomeListHeadViewHolder) holder).onLoadFinishedHeadBindViewHolder(mContext, mDataController);
    }
}
