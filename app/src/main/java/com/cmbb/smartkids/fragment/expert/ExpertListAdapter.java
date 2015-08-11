package com.cmbb.smartkids.fragment.expert;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cmbb.smartkids.fragment.master.MasterTypeModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.mengrecyclerview.adapter.ContentAdapterBase;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:59
 */
public class ExpertListAdapter extends ContentAdapterBase<MasterTypeModel> {
    private Context mContext;
    private boolean isCity;

    ExpertTypeListViewHolder.OnTypeClickListener mOnTypeClickListener;


    public ExpertListAdapter(Context mContext, DataController<MasterTypeModel> mDataController, boolean need, ExpertTypeListViewHolder.OnTypeClickListener mOnTypeClickListener) {
        super(mContext, mDataController);
        this.mContext = mContext;
        this.mOnTypeClickListener = mOnTypeClickListener;
        moreFlag = true;
        // 设置HeaderView
        setNeedHeadView(need);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType) {
        return ExpertTypeListViewHolder.create(mContext, parent, mOnTypeClickListener);
    }


    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ExpertTypeListViewHolder) holder).onBindViewHolder(mContext, mDataController.getData(position));
    }

}
