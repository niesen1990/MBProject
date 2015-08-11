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
public class CaseTpyeListAdapter extends ContentAdapterBase<CaseTypeModel> {
    private Context mContext;
    CaseTypeListViewHolder.OnCaseTypeClickListener mOnCaseTypeClickListener;


    public CaseTpyeListAdapter(Context mContext, DataController<CaseTypeModel> mDataController, boolean need, CaseTypeListViewHolder.OnCaseTypeClickListener mOnCaseTypeClickListener) {
        super(mContext, mDataController);
        this.mContext = mContext;
        moreFlag = true;
        this.mOnCaseTypeClickListener = mOnCaseTypeClickListener;
        // 设置HeaderView
        setNeedHeadView(need);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType) {
        return CaseTypeListViewHolder.create(mContext, parent, mOnCaseTypeClickListener);
    }


    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CaseTypeListViewHolder) holder).onBindViewHolder(mContext, mDataController.getData(position));
    }

}
