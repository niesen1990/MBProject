package com.cmbb.smartkids.fragment.master;

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
public class MasterTypeListViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvLeft;

    OnTypeClickListener mOnTypeClickListener;

    private MasterTypeListViewHolder(View view, OnTypeClickListener onTypeClickListener) {
        super(view);
        mOnTypeClickListener = onTypeClickListener;
        mTvLeft = (TextView) view.findViewById(R.id.tv_left);
    }

    public static MasterTypeListViewHolder create(final Context context, ViewGroup parent, OnTypeClickListener onTypeClickListener) {

        View v = LayoutInflater.from(context).inflate(R.layout.layout_text_one, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return new MasterTypeListViewHolder(v, onTypeClickListener);
    }

    public void onBindViewHolder(Context context, final MasterTypeModel entry) {
        mTvLeft.setTag(entry);
        mTvLeft.setText(entry.getName());
        mTvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnTypeClickListener.onTypeClick(v);
            }
        });
    }

    public interface OnTypeClickListener {
        public void onTypeClick(View view);
    }

}
