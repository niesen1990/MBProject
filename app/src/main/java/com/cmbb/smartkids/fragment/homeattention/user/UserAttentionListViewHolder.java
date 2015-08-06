package com.cmbb.smartkids.fragment.homeattention.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
public class UserAttentionListViewHolder extends RecyclerView.ViewHolder {

    private FrameLayout ivHeadAll;
    private ImageView ivHead;
    private ImageView ivMaster;
    private TextView tvConstellation;
    private ImageView ivRanktag;
    private ImageView ivLv;
    private TextView tvContent;

    private UserAttentionListViewHolder(View view) {
        super(view);
        ivHeadAll = (FrameLayout) view.findViewById(R.id.iv_head_all);
        ivHead = (ImageView) view.findViewById(R.id.iv_head);
        ivMaster = (ImageView) view.findViewById(R.id.iv_master);
        tvConstellation = (TextView) view.findViewById(R.id.tv_constellation);
        ivRanktag = (ImageView) view.findViewById(R.id.iv_ranktag);
        ivLv = (ImageView) view.findViewById(R.id.iv_lv);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
    }

    public static UserAttentionListViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_home_user_attention_list_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return new UserAttentionListViewHolder(v);
    }

    public void onBindViewHolder(Context context, final UserAttentionModel entry) {
        GlideTool.loadImage(context, entry.getUserSmallHeadImg(), ivHead, true);
        tvContent.setText(entry.getEredarName() + "达人");
        tvConstellation.setText(entry.getNike());
    }

}
