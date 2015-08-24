package com.cmbb.smartkids.fragment.caselist;

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
public class CaseDetailListAdapter extends ContentAdapterBase<CaseDetailListModel> {
    private Context mContext;


    public CaseDetailListAdapter(Context mContext, DataController<CaseDetailListModel> mDataController, boolean need) {
        super(mContext, mDataController);
        moreFlag = false;
        this.mContext = mContext;
        // 设置HeaderView
        setNeedHeadView(need);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType) {
        return CaseDetailListViewHolder.create(mContext, parent);
    }


    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CaseDetailListViewHolder) holder).onBindViewHolder(mContext, mDataController.getData(position));
    }

}
