package com.cmbb.smartkids.fragment.caselist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class CaseTypeListViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvLeft;

    private OnCaseTypeClickListener mOnCaseTypeClickListener;


    private CaseTypeListViewHolder(View view, OnCaseTypeClickListener mOnCaseTypeClickListener) {
        super(view);
        mTvLeft = (TextView) view.findViewById(R.id.tv_left);
        this.mOnCaseTypeClickListener = mOnCaseTypeClickListener;
    }

    public static CaseTypeListViewHolder create(final Context context, ViewGroup parent, final OnCaseTypeClickListener mOnCaseTypeClickListener) {

        View v = LayoutInflater.from(context).inflate(R.layout.layout_text_one, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCaseTypeClickListener.onCaseTypeClick(v);
            }
        });
        return new CaseTypeListViewHolder(v, mOnCaseTypeClickListener);
    }

    public void onBindViewHolder(Context context, final CaseTypeModel entry) {
        mTvLeft.setTag(entry);
        mTvLeft.setText(entry.getName());
    }

    public interface OnCaseTypeClickListener {
        public void onCaseTypeClick(View view);
    }

}
