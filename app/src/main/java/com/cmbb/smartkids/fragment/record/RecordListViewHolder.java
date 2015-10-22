package com.cmbb.smartkids.fragment.record;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.ImagePreviewActivity;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.fragment.replay.ReplayModel;
import com.cmbb.smartkids.mengbottomsheets.BottomSheet;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.picasso.PicassoTool;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;


/**
 * 项目名称：MengBao
 * 类描述：绑定PostModel
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class RecordListViewHolder extends RecyclerView.ViewHolder {

    private CardView mCardview;
    private TextView mTvTitle;
    private TextView mTvTime;
    private TextView mTvCount;


    private RecordListViewHolder(View view) {
        super(view);
        mCardview = (CardView) view.findViewById(R.id.cardview);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvTime = (TextView) view.findViewById(R.id.tv_time);
        mTvCount = (TextView) view.findViewById(R.id.tv_count);
    }

    public static RecordListViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_record_list_item, parent, false);
        return new RecordListViewHolder(v);
    }

    public void onBindViewHolder(final Context context, final RecordModel.ContextEntity contextEntity) {
        mTvTitle.setText(contextEntity.getContext());
        mTvTime.setText(contextEntity.getDate());
        mTvCount.setText("+" + contextEntity.getGoldCount());
    }

}
