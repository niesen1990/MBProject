package com.cmbb.smartkids.activity.user;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.doctor.DoctorRightModel;
import com.cmbb.smartkids.model.expert.ExpertSchedulingBaseModel;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.RankTools;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserExpertActivity extends MActivity {

    // TabLayout
    // content
    // appbar
    AppBarLayout appbar;


    // 用户数据
    DoctorRightModel mDoctorRightModel;


    private ImageView ivUserBac;
    private TextView tvNick;
    private ImageView ivRanktag;
    private ImageView ivLv;
    private TextView btnRoad;
    private RelativeLayout rvBac;

    private FloatingActionButton fab;

    boolean attention_flag = false;


    private TextView expertSpecialty;
    private TextView expertHospital;
    private TextView expertContent;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_case;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        mDoctorRightModel = getIntent().getParcelableExtra("user");
        // user
        ivUserBac = (ImageView) findViewById(R.id.iv_user_bac);
        GlideTool.loadImage(this, mDoctorRightModel.getUserSmallHeadImg(), ivUserBac, true);
        tvNick = (TextView) findViewById(R.id.tv_nick);
        tvNick.setText(mDoctorRightModel.getRealName());
        ivRanktag = (ImageView) findViewById(R.id.iv_ranktag);
        ivLv = (ImageView) findViewById(R.id.iv_lv);
        expertSpecialty = (TextView) findViewById(R.id.expert_specialty);
        expertSpecialty.setText(mDoctorRightModel.getBeGoodAt());
        expertHospital = (TextView) findViewById(R.id.expert_hospital);
        expertHospital.setText(mDoctorRightModel.getAddress());
        expertContent = (TextView) findViewById(R.id.expert_content);
        expertContent.setText(mDoctorRightModel.getAbstracting());
        btnRoad = (TextView) findViewById(R.id.btn_road);
        btnRoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getExpertScheduling(mDoctorRightModel.getUserId());
            }
        });
        rvBac = (RelativeLayout) findViewById(R.id.rv_bac);
        // test
        rvBac.setBackgroundResource(RankTools.getAuthBackground(4, 1));
        fab = (FloatingActionButton) findViewById(R.id.fab);
        switch (mDoctorRightModel.getAttention()) {
            case 1:
                attention_flag = true;
                fab.setImageResource(android.R.drawable.ic_menu_delete);
                break;
            case 0:
                attention_flag = false;
                fab.setImageResource(android.R.drawable.ic_menu_add);
                break;
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消关注
                showWaitDialog();
                if (attention_flag) {
                    ApiNetwork.CancelUserAttention(MApplication.token, mDoctorRightModel.getUserId() + "", new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideWaitDialog();
                                    showToast("请检查网络");
                                }
                            });
                        }

                        @Override
                        public void onResponse(final Response response) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        hideWaitDialog();
                                        if (response.body().string().contains("1")) {
                                            showToast("取消成功");
                                            fab.setImageResource(android.R.drawable.ic_menu_add);
                                        } else {
                                            showToast("取消失败");
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });

                } else {
                    ApiNetwork.addUserAttention(MApplication.token, mDoctorRightModel.getUserId() + "", new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideWaitDialog();
                                    showToast("请检查网络");
                                }
                            });
                        }

                        @Override
                        public void onResponse(final Response response) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        hideWaitDialog();
                                        if (response.body().string().contains("1")) {
                                            showToast("关注成功");
                                            fab.setImageResource(android.R.drawable.ic_menu_delete);
                                        } else {
                                            showToast("关注失败");
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    //获取专家行程
    private void getExpertScheduling(int userId) {
        showWaitDialog();
        Map<String, String> map = new HashMap<>();
        map.put("token", MApplication.token);
        map.put("userId", userId + "");
        OkHttp.asyncPost(Constants.Home.EXPERT_SCHEDULING, map, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideWaitDialog();
                        showToast(getResources().getString(R.string.rc_network_error));
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            try {
                                String result = response.body().string();
                                Gson gson = new Gson();
                                ExpertSchedulingBaseModel expertSchedulingBaseModel = gson.fromJson(result, ExpertSchedulingBaseModel.class);
                                if (expertSchedulingBaseModel.getCode().equals("1")) {
                                    String xingcheng;
                                    if (TextUtils.isEmpty(expertSchedulingBaseModel.getContext().getContext())) {
                                        xingcheng = "专家未更新行程";
                                    } else {
                                        xingcheng = expertSchedulingBaseModel.getContext().getContext();
                                    }
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserExpertActivity.this);
                                    alertDialogBuilder.setTitle("专家行程");
                                    alertDialogBuilder.setMessage(xingcheng).setCancelable(true);
                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            showToast(getResources().getString(R.string.rc_network_error));
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
