package com.cmbb.smartkids.fragment.replay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/29 下午2:37
 */
public class ReplayHeadViewHolder extends RecyclerView.ViewHolder {


    private ReplayHeadViewHolder(View view) {
        super(view);
    }

    public static ReplayHeadViewHolder create(final Context context, ViewGroup parent, LinearLayout headView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headView.setLayoutParams(params);
        return new ReplayHeadViewHolder(headView);
    }

    public void onBindViewHolder(Context context) {
        // 静态数据bind
    }

    // 动态数据bind
    public void onLoadFinishedHeadBindViewHolder(final Context context, DataController<PlateModel> mDataController) {
    }


}
