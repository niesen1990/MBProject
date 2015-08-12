package com.cmbb.smartkids.fragment.master;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.UserActivity;
import com.cmbb.smartkids.fragment.homeplate.HomeEredarModel;
import com.cmbb.smartkids.tools.glide.GlideTool;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class MasterDetailListViewHolder extends RecyclerView.ViewHolder {

    private final ImageView civHead;
    private final TextView tvAttention;
    private final TextView tvName;
    private final RelativeLayout rl_root;

    private MasterDetailListViewHolder(View view) {
        super(view);
        civHead = (ImageView) view.findViewById(R.id.civ_head);
        tvAttention = (TextView) view.findViewById(R.id.tv_attention);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        rl_root = (RelativeLayout) view.findViewById(R.id.rl_root);
    }

    public static MasterDetailListViewHolder create(final Context context, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.activity_master_right, parent, false);

        return new MasterDetailListViewHolder(v);
    }

    public void onBindViewHolder(final Context context, final MasterDetailModel entry) {
        rl_root.setTag(entry);
        rl_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MasterDetailModel masterDetailModel = (MasterDetailModel) v.getTag();
                HomeEredarModel homeEredarModel = new HomeEredarModel();
                homeEredarModel.setEredar(masterDetailModel.getEredar());
                homeEredarModel.setEredarType(masterDetailModel.getEredarType());
                homeEredarModel.setEredarName(masterDetailModel.getEredarName());
                homeEredarModel.setEredarRank(masterDetailModel.getEredarRank());
                homeEredarModel.setNike(masterDetailModel.getNike());
                homeEredarModel.setUserSmallHeadImg(masterDetailModel.getUserSmallHeadImg());
                homeEredarModel.setAttention(1);
                homeEredarModel.setAuthority(masterDetailModel.getAuthority());
                homeEredarModel.setId(masterDetailModel.getId());
                homeEredarModel.setLoginTimes(masterDetailModel.getLoginTimes());
                homeEredarModel.setUserStatus(masterDetailModel.getUserStatus());
                Intent intent = new Intent(context, UserActivity.class);
                intent.putExtra("user", homeEredarModel);
                context.startActivity(intent);
            }
        });
        GlideTool.loadImage(context, entry.getUserSmallHeadImg(), civHead, true);
        if (entry.getAttention() == 1) {
            tvAttention.setBackgroundResource(R.drawable.ic_master_attention);
        } else if (entry.getAttention() == 0) {
            tvAttention.setBackgroundResource(R.drawable.ic_master_not_attention);
        }
        tvName.setText(entry.getNike());
    }

}
