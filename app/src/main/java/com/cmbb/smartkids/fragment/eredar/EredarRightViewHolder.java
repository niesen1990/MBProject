package com.cmbb.smartkids.fragment.eredar;

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
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.homeplate.HomeEredarModel;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.picasso.PicassoTool;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class EredarRightViewHolder extends RecyclerView.ViewHolder {

    private final ImageView civHead;
    private final TextView tvAttention;
    private final TextView tvName;
    private final RelativeLayout rl_root;

    private EredarRightViewHolder(View view) {
        super(view);
        civHead = (ImageView) view.findViewById(R.id.civ_head);
        tvAttention = (TextView) view.findViewById(R.id.tv_attention);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        rl_root = (RelativeLayout) view.findViewById(R.id.rl_root);
    }

    public static EredarRightViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_master_right, parent, false);
        return new EredarRightViewHolder(v);
    }

    public void onBindViewHolder(final Context context, final EredarRightModel entry) {
        rl_root.setTag(entry);
        rl_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EredarRightModel masterDetailModel = (EredarRightModel) v.getTag();
                HomeEredarModel homeEredarModel = new HomeEredarModel();
                homeEredarModel.setEredar(masterDetailModel.getEredar());
                homeEredarModel.setEredarType(masterDetailModel.getEredarType());
                homeEredarModel.setEredarName(masterDetailModel.getEredarName());
                homeEredarModel.setEredarRank(masterDetailModel.getEredarRank());
                homeEredarModel.setNike(masterDetailModel.getNike());
                homeEredarModel.setUserSmallHeadImg(masterDetailModel.getUserSmallHeadImg());
                homeEredarModel.setAttention(masterDetailModel.getAttention());
                homeEredarModel.setAuthority(masterDetailModel.getAuthority());
                homeEredarModel.setId(masterDetailModel.getId());
                homeEredarModel.setLoginTimes(masterDetailModel.getLoginTimes());
                homeEredarModel.setUserStatus(masterDetailModel.getUserStatus());
                Intent intent = new Intent(context, UserActivity.class);
                intent.putExtra("user", homeEredarModel);
                context.startActivity(intent);
            }
        });
        tvAttention.setTag(entry);
        PicassoTool.loadImage(context, entry.getUserSmallHeadImg(), civHead, true);
        if (entry.getAttention() == 1) {
            tvAttention.setBackgroundResource(R.drawable.ic_master_attention);
        } else if (entry.getAttention() == 0) {
            tvAttention.setBackgroundResource(R.drawable.ic_master_not_attention);
        }
        tvAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EredarRightModel masterDetailModel = (EredarRightModel) v.getTag();
                if (masterDetailModel.getAttention() == 1) {
                    ApiNetwork.CancelUserAttention(MApplication.token, masterDetailModel.getId() + "", new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            ((MActivity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ((MActivity) context).showToast("请检查网络");
                                }
                            });
                        }

                        @Override
                        public void onResponse(final Response response) throws IOException {
                            ((MActivity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        if (response.body().string().contains("1")) {
                                            ((MActivity) context).showToast("取消成功");
                                            tvAttention.setBackgroundResource(R.drawable.ic_master_not_attention);
                                            masterDetailModel.setAttention(0);

                                        } else {
                                            ((MActivity) context).showToast("取消失败");
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });

                } else {
                    ApiNetwork.addUserAttention(MApplication.token, masterDetailModel.getId() + "", new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            ((MActivity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ((MActivity) context).showToast("请检查网络");
                                }
                            });
                        }

                        @Override
                        public void onResponse(final Response response) throws IOException {
                            final String result = response.body().string();
                            ((MActivity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        if (result.contains("1")) {
                                            ((MActivity) context).showToast("关注成功");
                                            tvAttention.setBackgroundResource(R.drawable.ic_master_attention);
                                            masterDetailModel.setAttention(1);
                                        } else {
                                            ((MActivity) context).showToast("关注失败");
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });

                }
            }
        });
        tvName.setText(entry.getNike());
    }
}
