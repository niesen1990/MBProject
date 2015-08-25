package com.cmbb.smartkids.fragment.search.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.UserActivity;
import com.cmbb.smartkids.fragment.homeplate.HomeEredarModel;
import com.cmbb.smartkids.model.search.SearchModel;
import com.cmbb.smartkids.tools.glide.GlideTool;


/**
 * 项目名称：MengBao
 * 类描述：绑定PostModel
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class SearchUserListViewHolder extends RecyclerView.ViewHolder {

    private CardView cardview;
    private ImageView ivHead;
    private TextView tvConstellation;
    private ImageView ivRanktag;
    private ImageView ivLv;
    private TextView tvContent;

    private SearchUserListViewHolder(View view) {
        super(view);
        cardview = (CardView) view.findViewById(R.id.cardview);
        ivHead = (ImageView) view.findViewById(R.id.iv_head);
        tvConstellation = (TextView) view.findViewById(R.id.tv_constellation);
        ivRanktag = (ImageView) view.findViewById(R.id.iv_ranktag);
        ivLv = (ImageView) view.findViewById(R.id.iv_lv);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
    }

    public static SearchUserListViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_search_usr_list_item, parent, false);
        return new SearchUserListViewHolder(v);
    }

    public void onBindViewHolder(final Context context, final SearchModel entry) {
        cardview.setTag(entry);
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchModel userAttentionModel = (SearchModel) v.getTag();
                HomeEredarModel homeEredarModel = new HomeEredarModel();
                homeEredarModel.setEredar(userAttentionModel.getEredar());
                homeEredarModel.setEredarType(userAttentionModel.getEredarType());
                homeEredarModel.setEredarName(userAttentionModel.getEredarName());
                homeEredarModel.setEredarRank(userAttentionModel.getEredarRank());
                homeEredarModel.setNike(userAttentionModel.getNike());
                homeEredarModel.setUserSmallHeadImg(userAttentionModel.getUserSmallHeadImg());
                homeEredarModel.setAttention(userAttentionModel.getAttention());
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
                        tvContent.setText("萌宝用户");
                        break;
                    case 4:
                        tvContent.setText("专家");
                        break;
                    case 5:
                        tvContent.setText("在线小编");
                        break;
                    case 6:
                        tvContent.setText("萌主");
                        break;
                    case 7:
                        tvContent.setText("实习萌主");
                        break;
                    default:
                        tvContent.setText("萌宝用户");
                        break;
                }

            } catch (Exception e) {

            }
        }
        tvConstellation.setText(entry.getNike());
    }

}
