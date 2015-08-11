package com.cmbb.smartkids.fragment.homeattention.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
public class UserAttentionListViewHolder extends RecyclerView.ViewHolder {

    private FrameLayout ivHeadAll;
    private ImageView ivHead;
    private ImageView ivMaster;
    private TextView tvConstellation;
    private ImageView ivRanktag;
    private ImageView ivLv;
    private CardView mCardView;
    private TextView tvContent;

    private UserAttentionListViewHolder(View view) {
        super(view);
        mCardView = (CardView) view.findViewById(R.id.cardview);
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

    public void onBindViewHolder(final Context context, final UserAttentionModel entry) {
        mCardView.setTag(entry);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAttentionModel userAttentionModel = (UserAttentionModel) v.getTag();
                HomeEredarModel homeEredarModel = new HomeEredarModel();
                homeEredarModel.setEredar(userAttentionModel.getEredar());
                homeEredarModel.setEredarType(userAttentionModel.getEredarType());
                homeEredarModel.setEredarName(userAttentionModel.getEredarName());
                homeEredarModel.setEredarRank(userAttentionModel.getEredarRank());
                homeEredarModel.setNike(userAttentionModel.getNike());
                homeEredarModel.setUserSmallHeadImg(userAttentionModel.getUserSmallHeadImg());
                homeEredarModel.setAttention(1);
                homeEredarModel.setAuthority(userAttentionModel.getAuthority());
                homeEredarModel.setId(userAttentionModel.getUserId());
                homeEredarModel.setLoginTimes(userAttentionModel.getLoginTimes());
                homeEredarModel.setUserStatus(userAttentionModel.getUserStatus());
                Intent intent = new Intent(context, UserActivity.class);
                intent.putExtra("user", homeEredarModel);
                context.startActivity(intent);
            }
        });
        GlideTool.loadImage(context, entry.getUserSmallHeadImg(), ivHead, true);
        tvConstellation.setText(entry.getNike());

        if (!TextUtils.isEmpty(entry.getEredarName())) {
            tvContent.setText(entry.getEredarName() + "达人");
        } else {
            try {
                switch (entry.getAuthority()) {
                    case 1:
                        tvContent.setText("系统管理员");
                        break;
                    case 2:
                        tvContent.setText("小编");
                        break;
                    case 3:
                        tvContent.setText("普通用户");
                        break;
                    case 4:
                        tvContent.setText("专家");
                        break;
                    case 5:
                        tvContent.setText("在线小编");
                        break;
                    default:
                        tvContent.setText("普通用户");
                        break;
                }

            } catch (Exception e) {

            }
        }
    }

}
