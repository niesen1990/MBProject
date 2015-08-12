package com.cmbb.smartkids.fragment.platelist;

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
public class PlateWonderListAdapter extends ContentAdapterBase<PlateModel> {
    private Context mContext;
    private boolean isCity;


    public PlateWonderListAdapter(Context mContext, DataController<PlateModel> mDataController, boolean need, boolean isCity) {
        super(mContext, mDataController);
        moreFlag = true;
        this.mContext = mContext;
        this.isCity = isCity;
        // 设置HeaderView
        setNeedHeadView(need);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType) {
        return PlateListViewHolder.create(mContext, parent, isCity);
    }


    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PlateListViewHolder) holder).onBindViewHolder(mContext, mDataController.getData(position));
    }

}
