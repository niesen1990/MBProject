package com.cmbb.smartkids.activity.baby;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.cmbb.smartkids.widget.coordinator.MengCoordinatorLayout;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MBabyActivity extends MActivity {

    private MengCoordinatorLayout coordinatorlayout;
    private AppBarLayout appbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private RelativeLayout autoRl;
    private ImageView ivHeadBac;
    private TextView title;
    private TextView subtitle;
    private TextView btnAttention;
    private FloatingActionButton fabPublish;

    private PlateModel mPlateModel;

    private void assignViews() {
        coordinatorlayout = (MengCoordinatorLayout) findViewById(R.id.coordinatorlayout);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        autoRl = (RelativeLayout) findViewById(R.id.auto_rl);
        ivHeadBac = (ImageView) findViewById(R.id.iv_head_bac);
        GlideTool.loadImage(this, mPlateModel.getSmallImg(), ivHeadBac, true);
        title = (TextView) findViewById(R.id.title);
        title.setText(mPlateModel.getTitle());
        subtitle = (TextView) findViewById(R.id.subtitle);
        subtitle.setText(mPlateModel.getContext());
        btnAttention = (TextView) findViewById(R.id.btn_attention);
        btnAttention.setClickable(false);
        btnAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnAttention.getText().equals("关注")) {
                    ApiNetwork.addAttentionPlate(mPlateModel.getId() + "", new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast("请检测网络");
                                }
                            });
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            if (response.isSuccessful()) {
                                final String result = response.body().string();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (result.contains("1")) {
                                            btnAttention.setText("取消");
                                            showToast("关注成功");
                                        } else {
                                            showToast("关注失败");
                                        }
                                    }
                                });

                            }
                        }
                    });
                } else {
                    ApiNetwork.cancelAttentionPlate(mPlateModel.getId() + "", new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast("请检测网络");
                                }
                            });
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            if (response.isSuccessful()) {
                                final String result = response.body().string();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (result.contains("1")) {
                                            btnAttention.setText("关注");
                                            showToast("取消成功");
                                        } else {
                                            showToast("取消失败");
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
        fabPublish = (FloatingActionButton) findViewById(R.id.fab_publish);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mbaby;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mPlateModel = getIntent().getParcelableExtra("model");
        assignViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_mbaby, menu);
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
