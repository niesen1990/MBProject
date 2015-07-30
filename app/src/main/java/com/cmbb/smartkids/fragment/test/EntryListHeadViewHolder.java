package com.cmbb.smartkids.fragment.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cmbb.smartkids.R;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/29 下午2:37
 */
public class EntryListHeadViewHolder extends RecyclerView.ViewHolder {

    public final LinearLayout linearlayout_master;

    private EntryListHeadViewHolder(View view) {
        super(view);
        linearlayout_master = (LinearLayout) view.findViewById(R.id.linearlayout_master);
    }

    public static EntryListHeadViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_home_list_headview, parent, false);
        return new EntryListHeadViewHolder(v);
    }

    public void onBindViewHolder(Context context) {
        linearlayout_master.removeAllViews();
        for (int i = 0; i < 4; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_home_list_headview_item, null, false);
            linearlayout_master.addView(view);
        }
    }


}
