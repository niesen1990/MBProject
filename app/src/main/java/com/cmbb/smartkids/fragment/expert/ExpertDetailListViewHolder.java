package com.cmbb.smartkids.fragment.expert;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.tools.glide.GlideTool;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class ExpertDetailListViewHolder extends RecyclerView.ViewHolder {

    private final ImageView civHead;
    private final TextView tvAttention;
    private final TextView tvName;


    private ExpertDetailListViewHolder(View view) {
        super(view);
        civHead = (ImageView) view.findViewById(R.id.civ_head);
        tvAttention = (TextView) view.findViewById(R.id.tv_attention);
        tvName = (TextView) view.findViewById(R.id.tv_name);
    }

    public static ExpertDetailListViewHolder create(final Context context, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.activity_master_right, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return new ExpertDetailListViewHolder(v);
    }

    public void onBindViewHolder(Context context, final ExpertDetailModel entry) {
        GlideTool.loadImage(context, entry.getUserSmallHeadImg(), civHead, true);
        if (entry.getAttention() == 1) {
            tvAttention.setBackgroundResource(R.drawable.ic_master_attention);
        } else if (entry.getAttention() == 0) {
            tvAttention.setBackgroundResource(R.drawable.ic_master_not_attention);
        }
        tvName.setText(entry.getRealName());
    }

    public interface OnTypeClickListener {
        public void onTypeClick(View view);
    }

}
