package com.cmbb.smartkids.activity.post;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.fragment.postlist.wonder.AgePostListFragment;
import com.cmbb.smartkids.fragment.postlist.wonder.WonderPublicCountModel;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.cmbb.smartkids.tools.log.Log;
import com.cmbb.smartkids.widget.coordinator.MengCoordinatorLayout;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class PostAgeListActivity extends MActivity implements AppBarLayout.OnOffsetChangedListener {

    private MengCoordinatorLayout coordinatorlayout;
    private AppBarLayout appbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView ivHeadBac;
    private TextView title;
    private TextView subtitle;
    private TextView btnAttention;
    private Toolbar tlMainCustom;
    private FloatingActionButton fabPublish;


    private FrameLayout container;

    CommonFragment mCommonFragment;
    //Model
    private PlateModel mPlateModel;

    BroadcastReceiver attentionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            WonderPublicCountModel wonderPublicCountModel = intent.getParcelableExtra(Constants.Post.PLATE_DATA);
            btnAttention.setClickable(true);
            Log.i("attention", "attention = " + wonderPublicCountModel.getAttention());
            if (wonderPublicCountModel.getAttention().equals("1")) {
                btnAttention.setText("取消");
            } else {
                btnAttention.setText("关注");
            }
        }
    };

    private void assignViews() {
        coordinatorlayout = (MengCoordinatorLayout) findViewById(R.id.coordinatorlayout);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ivHeadBac = (ImageView) findViewById(R.id.iv_head_bac);
        title = (TextView) findViewById(R.id.title);
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
        subtitle = (TextView) findViewById(R.id.subtitle);
        tlMainCustom = (Toolbar) findViewById(R.id.tl_main_custom);
        container = (FrameLayout) findViewById(R.id.container);

        GlideTool.loadImage(this, mPlateModel.getSmallImg(), ivHeadBac, true);
        title.setText(mPlateModel.getTitle());
        subtitle.setText(mPlateModel.getContext());
        fabPublish = (FloatingActionButton) findViewById(R.id.fab_publish);
        if (mPlateModel.getType().equals("BABYACTIVITY")) {
            fabPublish.setVisibility(View.GONE);
        } else {
            if (mPlateModel.getType().equals("MENGPO") || mPlateModel.getType().equals("TALENT")) {

                if (mPlateModel.getType().equals("MENGPO") && (MApplication.authority == 1 || MApplication.authority == 2)) {
                    fabPublish.setVisibility(View.VISIBLE);
                } else {
                    fabPublish.setVisibility(View.GONE);
                }
                if (mPlateModel.getType().equals("TALENT") && (MApplication.authority == 1 || MApplication.authority == 2 || MApplication.eredar == 1)) {
                    fabPublish.setVisibility(View.VISIBLE);
                } else {
                    fabPublish.setVisibility(View.GONE);
                }
            } else {
                fabPublish.setVisibility(View.VISIBLE);
            }
        }
        fabPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostAgeListActivity.this, PostAddWonderActivity.class);
                intent.putExtra("model", mPlateModel);
                intent.putExtra("flag", false);
                intent.putExtra("areaType", "AGEBREAKET");
                startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_post_list;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mPlateModel = getIntent().getParcelableExtra("model");
        assignViews();
        collapsingToolbar.setTitle(mPlateModel.getTitle());
        collapsingToolbar.setExpandedTitleColor(android.R.color.transparent);

        mCommonFragment = new AgePostListFragment(this, false, mPlateModel);
        getSupportFragmentManager().beginTransaction().add(R.id.container, mCommonFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_post_list, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(Constants.Post.PLATE_DATA_INTENT);
        registerReceiver(attentionReceiver, intentFilter);
        appbar.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(attentionReceiver);
        appbar.removeOnOffsetChangedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            mCommonFragment.getmSwipeRefresh().setEnabled(true);
        } else {
            mCommonFragment.getmSwipeRefresh().setEnabled(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new AgePostListFragment(this, false, mPlateModel)).commit();
        }
    }
}
